plugins {
    alias(libs.plugins.runique.android.library.compose)
}

android {
    namespace = "com.ryanphillips.core.presentation.ui"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.designsystem)
}