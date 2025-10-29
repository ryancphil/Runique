plugins {
    alias(libs.plugins.runique.android.application.compose)
    alias(libs.plugins.runique.jvm.ktor)
}

android {
    namespace = "com.ryanphillips.runique"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Compose
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.bundles.koin)
    implementation(libs.timber)

    // Splash screen
    implementation(libs.androidx.core.splashscreen)

    // Crypto
    implementation(libs.androidx.security.crypto.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    /*
    * Any new modules should be added here.
    * When modules are created they are automatically added
    * to the root `settings.gradle.kts` file,
    * in case you need to sanity check.
    */
    implementation(projects.core.data)
    implementation(projects.core.database)
    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
    implementation(projects.core.presentation.ui)

    implementation(projects.auth.data)
    implementation(projects.auth.domain)
    implementation(projects.auth.presentation)

    implementation(projects.run.data)
    implementation(projects.run.domain)
    implementation(projects.run.presentation)
    implementation(projects.run.location)
    implementation(projects.run.network)
}