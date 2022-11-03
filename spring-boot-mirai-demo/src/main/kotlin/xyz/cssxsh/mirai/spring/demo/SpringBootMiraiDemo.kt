package xyz.cssxsh.mirai.spring.demo

import net.mamoe.mirai.console.plugin.jvm.*
import xyz.cssxsh.mirai.spring.*

public object SpringBootMiraiDemo : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.spring-boot-mirai-demo",
        name = "spring-shit-demo",
        version = "1.0.0",
    ) {
        author("cssxsh")

        dependsOn("xyz.cssxsh.spring-boot-mirai-starter")
    }
) {

    override fun onEnable() {
        println(SpringBootMiraApplication.context)
    }

    override fun onDisable() {

    }
}