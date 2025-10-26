import com.ryanphillips.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * This Convention Plugin is for configuring Jvm Library Modules.
 */
class JvmKtorConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("org.jetbrains.kotlin.plugin.serialization")
            }
            dependencies {
                "implementation"(libs.findBundle("ktor").get())
            }
        }
    }
}