plugins {
    alias(libs.plugins.runique.android.feature.ui)
}

android {
    namespace = "com.ryanphillips.auth.presentation"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.presentation.ui)
    implementation(projects.auth.domain)
}