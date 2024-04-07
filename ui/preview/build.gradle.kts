plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
}

android {
    namespace = "dev.babananick.pap.ui.preview"
}

dependencies {
    implementation(project(":ui:components"))
}