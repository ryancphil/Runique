dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    /**
     * Allows references to our version catalog files
     * within the `build-logic` module!
     */
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
    }
}

rootProject.name = "build-logic"
include(":convention")