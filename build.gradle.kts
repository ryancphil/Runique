// Top-level build file where you can add configuration options common to all sub-projects/modules.
// Adding plugins here adds them to classpath for gradle to use, which allows our Convention plugins to reference them.
plugins {
    alias(libs.plugins.android.application) apply false // AndroidApplicationConventionPlugin
    alias(libs.plugins.kotlin.android) apply false // AndroidApplicationConventionPlugin
    alias(libs.plugins.kotlin.compose) apply false // AndroidApplicationComposeConventionPlugin & AndroidLibraryComposeConventionPlugin
    alias(libs.plugins.android.library) apply false // AndroidLibraryConventionPlugin
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false // JvmLibraryConventionPlugin
    alias(libs.plugins.ksp) apply false // AndroidRoomConventionPlugin
    alias(libs.plugins.room) apply false // AndroidRoomConventionPlugin
    alias(libs.plugins.kotlin.serialization) apply false // JvmKtorConventionPlugin
}