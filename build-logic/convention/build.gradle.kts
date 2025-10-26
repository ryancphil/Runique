/**
 * This build-logic module is an "included build" (see root `settings.gradle.kts`)
 * And is used to simplify gradle configuration and management for multi-module projects.
 */
plugins {
    `kotlin-dsl`
}
group = "com.ryanphillips.runique.buildlogic"

dependencies {
    // These are only needed at compile time for gradle building
    // and allow access to the apis we use in our convention plugins.
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

/**
 * This is where we register our convention plugins with gradle.
 * Ensure that:
 * * ID matches in our [plugins] section of libs.versions.toml &
 * * Implementation Class matches the name of the Convention Plugin class.
 */
gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "runique.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose") {
            id = "runique.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "runique.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "runique.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeatureUi") {
            id = "runique.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }
        register("androidRoom") {
            id = "runique.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmLibrary") {
            id = "runique.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("jvmKtor") {
            id = "runique.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
    }
}