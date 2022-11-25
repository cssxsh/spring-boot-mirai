allprojects {
    group = "xyz.cssxsh.mirai"
    version = "1.0.0-M1"
}

subprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven(url = "https://repo.spring.io/milestone")
    }
}