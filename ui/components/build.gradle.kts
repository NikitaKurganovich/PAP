import plugins.util.coil
import plugins.util.getLibsVersionCatalog

plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
    id(ProjectPlugins.PAP_ANDROID_HILT)
}

android {
    namespace = "dev.babananick.pap.ui.components"
}

dependencies {
    val versionCatalog = getLibsVersionCatalog()
    coil(versionCatalog)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.tab.navigator)
    implementation(project(":ui:theme"))
    implementation(project(":core:model"))
    implementation(libs.voyager.navigator)
    implementation(libs.tabler.icons)
}