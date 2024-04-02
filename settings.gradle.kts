@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
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
include(":feature:navigation")
include(":core:common")
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
