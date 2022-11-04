plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"

    id("net.mamoe.mirai-console") version "2.13.0-RC2"
    id("org.springframework.boot") version "3.0.0-RC1"
    id("me.him188.maven-central-publish") version "1.0.0-dev-3"
}
apply(plugin = "io.spring.dependency-management")

mavenCentralPublish {
    useCentralS01()
    singleDevGithubProject("cssxsh", "spring-boot-mirai")
    licenseFromGitHubProject("AGPL-3.0")
    workingDir = System.getenv("PUBLICATION_TEMP")?.let { file(it).resolve(projectName) }
        ?: buildDir.resolve("publishing-tmp")
    publication {
        artifact(tasks["buildPlugin"])
    }
}

dependencies {
    // xyz.cssxsh.mirai:spring-boot-mirai-starter
    compileOnly(project(":spring-boot-mirai-starter"))
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation(kotlin("test"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
