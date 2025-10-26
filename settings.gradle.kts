pluginManagement {
    includeBuild("build-logic") // Our Gradle module for multi-module management!
    repositories {
        /**
         * google() would work fine, but these regexes
         * optimize gradle lookups.
         */
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Runique"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS") // Enables referencing project modules in a type-safe manner (rather than strings) in gradle files
include(":app")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
include(":core:domain")
include(":core:data")
include(":core:database")
