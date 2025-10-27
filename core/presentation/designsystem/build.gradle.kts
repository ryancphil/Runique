plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.ryanphillips.core.presentation.designsystem"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    debugImplementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    // api (vs. implementation) makes dependency accessible to consumers
    api(libs.androidx.compose.material3)
}