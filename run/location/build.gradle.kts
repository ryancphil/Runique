plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.ryanphillips.run.location"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.run.domain)
}