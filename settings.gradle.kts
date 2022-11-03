rootProject.name = "spring-boot-mirai"

pluginManagement {
    repositories {
        maven(url = "https://repo.spring.io/milestone")
        gradlePluginPortal()
    }
}

include("spring-boot-mirai-starter")
include("spring-boot-mirai-demo")