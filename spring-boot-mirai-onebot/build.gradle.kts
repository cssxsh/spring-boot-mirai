plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"

    id("net.mamoe.mirai-console") version "2.14.0"
    id("org.springframework.boot") version "3.0.3"
    id("me.him188.maven-central-publish") version "1.0.0-dev-3"
}

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
    testImplementation(kotlin("test"))
    testImplementation(project(":spring-boot-mirai-starter"))
    //
    implementation(platform("net.mamoe:mirai-bom:2.14.0"))
    compileOnly("net.mamoe:mirai-console-compiler-common")
    testImplementation("net.mamoe:mirai-logging-slf4j")
    testImplementation("net.mamoe:mirai-core-mock")
    //
    compileOnly(project(":spring-boot-mirai-starter"))
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.3"))
    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:3.0.3")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot {
    mainClass.set("xyz.cssxsh.mirai.spring.SpringBootMiraiApplication")
}

mirai {
    jvmTarget = JavaVersion.VERSION_17
    setupConsoleTestRuntime {
        workingDir = rootProject.projectDir.resolve("debug-sandbox")
        val libs = rootProject.childProjects["spring-boot-mirai-starter"]!!
            .fileTree("build/libs/")

        classpath += libs
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
