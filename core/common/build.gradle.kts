@file:Suppress("PropertyName", "VariableNaming")

plugins {
    id(ProjectPlugins.PAP_ANDROID_APPLICATION)
}

android {
    namespace = "dev.babananick.pap.core.common"
}

dependencies {

    implementation(project(":core:model"))
}