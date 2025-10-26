package com.ryanphillips.convention

/**
 * This enum allows us to handle the generic extension types
 * in our BuildType gradle extensions.
 *
 * eg. `extensions.configure<T>{}` where T is ApplicationExtension or LibraryExtension
 */
enum class ExtensionType {
    APPLICATION,
    LIBRARY
}