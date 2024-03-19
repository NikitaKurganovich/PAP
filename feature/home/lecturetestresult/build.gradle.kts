plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.GOOGLE_SERVICES)
}

android {
    namespace = "dev.babananick.pap"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database)
    implementation(libs.firebase.common)
    implementation(libs.firebase.database.ktx)
}