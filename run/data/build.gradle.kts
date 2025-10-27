plugins {
    alias(libs.plugins.runique.android.library)
}

android {
    namespace = "com.ryanphillips.run.data"
}

dependencies {
    implementation(projects.core.data)
}