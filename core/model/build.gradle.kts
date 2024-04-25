plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
}

android {
    namespace = "dev.babananick.pap.core.model"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.database.ktx)
}