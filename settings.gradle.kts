@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Psychology at Pocket"
include(":app")

include(":core:data:firebase")
include(":core:data:repository")
include(":core:domain")
include(":core:model")
include(":core:common")
include(":core:util")

include(":feature:navigation")
include(":feature:coordinators:authorization")
include(":feature:coordinators:mainscreenspace")

include(":feature:academic:lecture")
include(":feature:academic:lecturechoose")

include(":feature:login")
include(":feature:registration")

include(":feature:tests:test")
include(":feature:tests:testchoose")

include(":feature:profile")

include(":ui:components")
include(":ui:preview")
include(":ui:theme")
