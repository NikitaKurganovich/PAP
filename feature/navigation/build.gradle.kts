import plugins.util.coil
import plugins.util.getLibsVersionCatalog

plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)
}

android {
    namespace = "dev.babananick.pap.core.navigation"
}

dependencies {
    val versionCatalog = getLibsVersionCatalog()
    coil(versionCatalog)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.firebase.auth.ktx)

    implementation(project(":feature:coordinators:authorization"))
    implementation(project(":feature:coordinators:mainscreenspace"))
}