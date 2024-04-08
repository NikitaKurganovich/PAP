import plugins.util.coil
import plugins.util.getLibsVersionCatalog

plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
}

android {
    namespace = "dev.babananick.pap.ui.components"
}

dependencies {
    val versionCatalog = getLibsVersionCatalog()
    coil(versionCatalog)

    implementation(project(":core:model"))
    implementation(libs.voyager.navigator)
    implementation(libs.tabler.icons)

}