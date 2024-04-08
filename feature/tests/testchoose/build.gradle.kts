import plugins.util.coil
import plugins.util.getLibsVersionCatalog

plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)

}

android {
    namespace = "dev.babananick.pap.feature.tests.testchoose"
}

dependencies {
    val versionCatalog = getLibsVersionCatalog()
    coil(versionCatalog)
    implementation(project(":feature:tests:test"))
}