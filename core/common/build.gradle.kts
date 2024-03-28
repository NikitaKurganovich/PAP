@file:Suppress("PropertyName", "VariableNaming")

plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap.core.common"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(project(":core:model"))
    implementation(libs.androidx.material3.android)
}