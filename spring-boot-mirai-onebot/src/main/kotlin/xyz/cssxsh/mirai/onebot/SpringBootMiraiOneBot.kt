package xyz.cssxsh.mirai.onebot

import net.mamoe.mirai.console.extension.*
import net.mamoe.mirai.console.plugin.jvm.*

@PublishedApi
internal object SpringBootMiraiOneBot : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.spring-boot-mirai-onebot",
        name = "spring-onebot",
        version = "1.0.0",
    ) {
        author("cssxsh")
    }
) {

    override fun PluginComponentStorage.onLoad() {
        xyz.cssxsh.mirai.spring.SpringBootMiraiStartupExtension.version
    }

    override fun onEnable() {
        //
    }

    override fun onDisable() {
        //
    }
}