plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"

    id("org.springframework.boot") version "3.0.3"
    id("me.him188.maven-central-publish") version "1.0.0-dev-3"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

mavenCentralPublish {
    useCentralS01()
    singleDevGithubProject("cssxsh", "spring-boot-mirai")
    licenseFromGitHubProject("AGPL-3.0")
    workingDir = System.getenv("PUBLICATION_TEMP")?.let { file(it).resolve(projectName) }
        ?: buildDir.resolve("publishing-tmp")
}

dependencies {
    testImplementation(kotlin("test"))
    //
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.3"))
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")
    api("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //
    implementation(platform("net.mamoe:mirai-bom:2.14.0"))
    compileOnly("net.mamoe:mirai-core-api")
    compileOnly("net.mamoe:mirai-console")
    compileOnly("net.mamoe:mirai-console-compiler-common")
    testImplementation("net.mamoe:mirai-core-mock")
    testImplementation("net.mamoe:mirai-logging-slf4j")
}

kotlin {
    explicitApi()
    target.compilations {
        all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks {
    test {
        useJUnitPlatform()
    }
    shadowJar {
        mergeServiceFiles()
        mergeServiceFiles("META-INF/spring")
        append("META-INF/spring.factories")
        append("jndi.properties")
        append("META-INF/spring.components")
        append("META-INF/spring-autoconfigure-metadata.properties")
        dependencies {
            exclude(dependency("org.jetbrains.kotlin::"))
            exclude(dependency("ch.qos.logback::"))
//            jar:file:/D:/Users/CSSXSH/.gradle/caches/modules-2/files-2.1/org.springdoc/springdoc-openapi-starter-webmvc-ui/2.0.2/264da79c9d4a53feab8c5c6d0e0cb143bd099d4/springdoc-openapi-starter-webmvc-ui-2.0.2.jar!/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
//            jar:file:/D:/Users/CSSXSH/.gradle/caches/modules-2/files-2.1/org.springdoc/springdoc-openapi-starter-webmvc-api/2.0.2/479a5cd03969a22d9f8b819ef92c54433e08034a/springdoc-openapi-starter-webmvc-api-2.0.2.jar!/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
//            jar:file:/D:/Users/CSSXSH/.gradle/caches/modules-2/files-2.1/org.springdoc/springdoc-openapi-starter-common/2.0.2/5f668e69d620c56db3ba9689d48d6b64e42235b9/springdoc-openapi-starter-common-2.0.2.jar!/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
//            jar:file:/D:/Users/CSSXSH/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/3.0.3/17a64795795ecf1f1f6f0cc1c2794e2bed23ceda/spring-boot-autoconfigure-3.0.3.jar!/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
        }

        isZip64 = true
        archiveClassifier.set("all")
    }
}
