# [Spring Boot Mirai](https://github.com/cssxsh/spring-boot-mirai)

> Spring Boot 3.0 前置扩展

**注意 本扩展具有一定的娱乐性质，不喜勿喷**

[Mirai Console](https://github.com/mamoe/mirai-console) 的前置插件，用于 Spring Boot 的初始化  

[![maven-central](https://img.shields.io/maven-central/v/xyz.cssxsh.mirai/spring-boot-mirai-starter)](https://search.maven.org/artifact/xyz.cssxsh.mirai/spring-boot-mirai-starter)

## 数据库支持

**别急你别急**

## 指令系统

**别急你别急**

## 在 Mirai Console Plugin 项目中引用

**build.gradle.kts**
```kotlin
plugins {
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.22"
    
    id("net.mamoe.mirai-console") version "2.14.0"
    id("org.springframework.boot") version "3.0.3"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("xyz.cssxsh.mirai:spring-boot-mirai-starter:${version}")
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.0.3"))
}

// Spring Boot 3.0 需要 jdk-17
mirai {
    jvmTarget = JavaVersion.VERSION_17
}
```

**插件主类**
由于 mirai-console 没有相关钩子，所以你需要在插件的主类中调用一下 `SpringBootMiraiStartupExtension`
```kotlin
xyz.cssxsh.mirai.spring.SpringBootMiraiStartupExtension
```

**Spring Boot 3.0 中需要以 `AutoConfiguration.imports` 的形式添加 EnableAutoConfiguration 配置**   
例如 [imports](spring-boot-mirai-onebot/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports)

## 示例代码

* [spring-boot-mirai-onebot](spring-boot-mirai-onebot)

## 安装

### MCL 指令安装

**请确认 mcl.jar 的版本是 2.1.0+**  
`./mcl --update-package xyz.cssxsh.mirai:spring-boot-mirai-starter --channel maven-stable --type libs`