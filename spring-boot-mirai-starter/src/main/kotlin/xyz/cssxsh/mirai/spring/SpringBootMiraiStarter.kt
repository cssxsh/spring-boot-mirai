package xyz.cssxsh.mirai.spring

import net.mamoe.mirai.console.plugin.jvm.*

@PublishedApi
internal object SpringBootMiraiStarter : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.spring-boot-mirai-starter",
        name = "spring-mirai-starter",
        version = "1.0.0",
    ) {
        author("cssxsh")
    }
) {

    private lateinit var spring: Thread

    override fun onEnable() {
        spring = Thread(SpringBootMiraiApplication, "spring-boot-mirai")
        spring.contextClassLoader = SpringBootMiraiClassLoader(SpringBootMiraiStarter)
        spring.uncaughtExceptionHandler = Thread.UncaughtExceptionHandler { _, cause -> logger.error(cause) }
        spring.start()
    }

    override fun onDisable() {
        SpringBootMiraiApplication.context.close()
        spring.interrupt()
    }
}