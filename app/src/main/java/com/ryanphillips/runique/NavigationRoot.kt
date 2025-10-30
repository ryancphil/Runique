package com.ryanphillips.runique

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.navigation
import com.ryanphillips.auth.presentation.intro.IntroScreenRoot
import com.ryanphillips.auth.presentation.login.LoginScreenRoot
import com.ryanphillips.auth.presentation.register.RegisterScreenRoot
import com.ryanphillips.run.presentation.run_active.ActiveRunScreenRoot
import com.ryanphillips.run.presentation.run_active.service.ActiveRunService
import com.ryanphillips.run.presentation.run_overview.RunOverviewScreenRoot

/**
 * Our entry-point for app navigation. Feature modules shouldn't be aware
 * of each other, so we leverage lambdas in feature screen composables that
 * bubble up to this NavigationHostController that handles app navigation.
 */
@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "run" else "auth"
    ) {
        authGraph(navController)
        runGraph(navController)
    }
}

/**
 * private Extension functions on `NavGraphBuilder` allow us to modularize
 * our app's nav graph by individual features and keeps the `NavigationRoot`
 * clean and readable.
 */
private fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        startDestination = "intro",
        route = "auth"
    ) {
        composable(route = "intro") {
            IntroScreenRoot(
                onSignUpClick = {
                    navController.navigate("register")
                },
                onSignInClick = {
                    navController.navigate("login")
                })
        }
        composable(route = "register") {
            RegisterScreenRoot(
                onSignInClick = {
                    navController.navigate("login") { // Prevents extra items on the backstack, and restores state.
                        popUpTo(route = "register") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                },
                onSuccessfulRegistration = {
                    navController.navigate("login")
                })
        }
        composable(route = "login") {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate("run") {
                        popUpTo("auth") {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = {
                    navController.navigate("register") { // Prevents extra items on the backstack, and restores state.
                        popUpTo(route = "login") {
                            inclusive = true
                            saveState = true
                        }
                        restoreState = true
                    }
                })
        }
    }
}

private fun NavGraphBuilder.runGraph(navController: NavHostController) {
    navigation(
        startDestination = "run_overview",
        route = "run"
    ) {
        composable("run_overview") {
            RunOverviewScreenRoot(
                onStartRunClick = {
                    navController.navigate("active_run")
                })
        }
        composable(
            route = "active_run",
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "runique://active_run"
                })) {
            val context = LocalContext.current
            ActiveRunScreenRoot(
                onServiceToggle = { shouldRunService ->
                    if (shouldRunService) {
                        context.startService(
                            ActiveRunService.createStartIntent(
                                context,
                                MainActivity::class.java
                            )
                        )
                    } else {
                        context.startService(
                            ActiveRunService.createStopIntent(
                                context
                            )
                        )
                    }
                })
        }
    }
}