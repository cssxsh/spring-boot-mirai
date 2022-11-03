plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"

    id("net.mamoe.mirai-console") version "2.13.0-RC2"
    id("org.springframework.boot") version "3.0.0-RC1"
}

dependencies {
    // xyz.cssxsh.mirai:spring-boot-mirai-starter
    compileOnly(project(":spring-boot-mirai-starter"))
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.0.0-RC1")
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
    setupConsoleTestRuntime {
        workingDir = (parent ?: project).projectDir.resolve("debug-sandbox")
    }
}

kotlin {
    explicitApi()
}

tasks {
    test {
        useJUnitPlatform()
    }
}
