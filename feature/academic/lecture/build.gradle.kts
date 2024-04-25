plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)

}

android {
    namespace = "dev.babananick.pap.feature.academic.lecture"
}

dependencies {
    implementation(project(":feature:tests:test"))

}