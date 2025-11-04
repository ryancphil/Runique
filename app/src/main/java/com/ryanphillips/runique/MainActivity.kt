package com.ryanphillips.runique

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.ryanphillips.core.presentation.designsystem.RuniqueTheme
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : ComponentActivity() {

    /**
     * Our Screen level viewmodels are able to be injected directly into
     * Composables with `koinViewModel()` but at the activity level,
     * we use koin's `by viewModel<T>` api
     */
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        /**
         * androidx.core provides a splashscreen api.
         * Here we are showing the splashscreen for as long as
         * we are checking the auth state of the user.
         *
         * This splashscreen api required some XML work in our :app:values folder.
         * * themes.xml
         */
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.state.isCheckingAuth
            }
        }

        super.onCreate(savedInstanceState)
         enableEdgeToEdge()
        setContent {
            RuniqueTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background),
                ) {
                    /**
                     * Not showing our NavigationRoot composable until done checking auth.
                     */
                    if (!viewModel.state.isCheckingAuth) {
                        Timber.e("isLoggedIn: ${viewModel.state.isLoggedIn}")
                        val navController = rememberNavController()
                        NavigationRoot(
                            navController = navController,
                            isLoggedIn = viewModel.state.isLoggedIn
                        )
                    }
                }
            }
        }
    }
}