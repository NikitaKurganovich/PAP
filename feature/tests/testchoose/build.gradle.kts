plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)

}

android {
    namespace = "dev.babananick.pap.feature.tests.testchoose"
}

dependencies {

    implementation(project(":feature:tests:test"))
}