package xyz.cssxsh.mirai.onebot

import net.mamoe.mirai.console.plugin.jvm.*

@PublishedApi
internal object SpringBootMiraiOneBot : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.spring-boot-mirai-onebot",
        name = "spring-onebot",
        version = "1.0.0",
    ) {
        author("cssxsh")

        dependsOn("xyz.cssxsh.spring-boot-mirai-starter")
    }
) {

    override fun onEnable() {
        //
    }

    override fun onDisable() {
        //
    }
}