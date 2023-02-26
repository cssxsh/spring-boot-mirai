allprojects {
    group = "xyz.cssxsh.mirai"
    version = "1.0.0"
}

subprojects {
    repositories {
        maven("https://repo.huaweicloud.com/repository/maven")
        mavenCentral()
    }
}