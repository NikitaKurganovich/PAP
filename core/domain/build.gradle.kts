plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data:repository"))
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
}