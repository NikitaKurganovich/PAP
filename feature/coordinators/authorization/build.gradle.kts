plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)
}

android {
    namespace = "dev.babananick.pap.core.authorization"
}

dependencies {
    implementation(project(":feature:login"))
    implementation(project(":feature:registration"))
}