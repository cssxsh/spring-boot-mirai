allprojects {
    group = "xyz.cssxsh.mirai"
    version = "0.0.2"
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://repo.spring.io/milestone")
    }
}