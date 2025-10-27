plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.ryanphillips.auth.presentation"
}

dependencies {
    implementation(projects.auth.domain)
}