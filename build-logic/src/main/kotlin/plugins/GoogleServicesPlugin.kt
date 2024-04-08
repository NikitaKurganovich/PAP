package plugins

import ProjectPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class GoogleServicesPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            apply(ProjectPlugins.GOOGLE_SERVICES)
        }
    }
}