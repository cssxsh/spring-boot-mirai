# [Spring Boot Mirai](https://github.com/cssxsh/spring-boot-mirai)

> Spring Boot 3.0 前置插件

**注意 本插件具有一定的娱乐性质，不喜勿喷**

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
    id("org.springframework.boot") version "3.0.0"
    id("io.spring.dependency-management") version "1.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("xyz.cssxsh.mirai:spring-boot-mirai-starter:${version}")
}

// Spring Boot 3.0 需要 jdk-17
mirai {
    jvmTarget = JavaVersion.VERSION_17
}
```

**Spring Boot 3.0 中需要以 `AutoConfiguration.imports` 的形式添加 EnableAutoConfiguration 配置**   
例如 [imports](spring-boot-mirai-onebot/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports)

## 示例代码

* [spring-boot-mirai-onebot](spring-boot-mirai-onebot)

## 安装

### MCL 指令安装

**请确认 mcl.jar 的版本是 2.1.0+**  
`./mcl --update-package xyz.cssxsh.mirai:spring-boot-mirai-starter --channel maven-stable --type plugin`

### 手动安装

1. 从 [Releases](https://github.com/cssxsh/spring-boot-mirai/releases) 或者 [Maven](https://repo1.maven.org/maven2/xyz/cssxsh/mirai/spring-boot-mirai/) 下载 `mirai2.jar`
2. 将其放入 `plugins` 文件夹中