plugins {
    alias(libs.plugins.runique.jvm.library)
}
/**
 * This (core:domain) is a pure kotlin/jvm module, and
 * therefore shouldn't depend on Android libraries.
 */
dependencies {
    implementation(libs.kotlinx.coroutines.core)
}