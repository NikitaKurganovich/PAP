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
include(":core:navigation")
include(":core:common")
include(":core:coordinators:authorization")
include(":core:coordinators:mainscreenspace")

include(":feature:home:lecture")
include(":feature:home:lecturechoose")

include(":feature:login")
include(":feature:registration")

include(":feature:tests:test")
include(":feature:tests:testchoose")
include(":feature:tests:testresult")

include(":feature:profile")

include(":ui:components")
