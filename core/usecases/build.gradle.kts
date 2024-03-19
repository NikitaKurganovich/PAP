plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.GOOGLE_SERVICES)
}

android {
    namespace = "dev.babananick.pap.core.usecases"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:data:repository"))
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
}