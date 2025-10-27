plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.ryanphillips.run.presentation"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.run.domain)
}