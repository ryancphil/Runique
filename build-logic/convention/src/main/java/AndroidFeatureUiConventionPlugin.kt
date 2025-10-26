import com.ryanphillips.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * This Convention Plugin is for configuring Android Feature Ui Modules.
 * THIS TEMPLATE PROJECT DOESN'T CONTAIN ANY FEATURE MODULES!
 * But Feature Ui Modules will depend on more than just Compose because they will
 * likely deal with ViewModels and therefore will require dependency injection.
 * This convention plugin gives us everything our AndroidLibraryComposeConventionPlugin
 * does, plus any other dependencies we specify in the `addUiLayerDependencies` function.
 */
class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("runique.android.library.compose")
            }
            dependencies {
                addUiLayerDependencies(target)
            }
        }
    }
}