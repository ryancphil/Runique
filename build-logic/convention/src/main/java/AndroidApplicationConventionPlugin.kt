import com.android.build.api.dsl.ApplicationExtension
import com.ryanphillips.convention.ExtensionType
import com.ryanphillips.convention.configureBuildTypes
import com.ryanphillips.convention.configureKotlinAndroid
import com.ryanphillips.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Our first convention plugin where we are configuring
 * the critical things needed for an Android application that was
 * previously configured directly in `build.gradle.kts(:app)`
 */
class AndroidApplicationConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            /**
             * Our `compileOnly` gradle dependencies in
             * build.gradle.kts(:build-logic) give us access to
             * this.
             * This block is basically the equivalent
             * of the `android` block in `build.gradle.kts(:app)`
             */
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }
                configureKotlinAndroid(this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}