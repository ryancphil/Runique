plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.ryanphillips.core.presentation.designsystem"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    // api (vs. implementation) makes dependency accessible to consumers
    api(libs.androidx.compose.material3)
    implementation(libs.androidx.core)
}