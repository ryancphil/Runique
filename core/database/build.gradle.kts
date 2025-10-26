plugins {
    alias(libs.plugins.runique.android.library)
    alias(libs.plugins.runique.android.room)
}

android {
    namespace = "com.ryanphillips.core.database"
}

dependencies {
    implementation(projects.core.domain)
}