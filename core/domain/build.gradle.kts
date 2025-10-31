plugins {
    alias(libs.plugins.runique.jvm.library)
    alias(libs.plugins.runique.jvm.ktor)
}
/**
 * This (core:domain) is a pure kotlin/jvm module, and
 * therefore shouldn't depend on Android libraries.
 */
dependencies {
    implementation(libs.timber)
    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
