package com.ryanphillips.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * This extension function allows us to reference our
 * version catalog within our Convention Plugins apply functions.
 */
val Project.libs
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")