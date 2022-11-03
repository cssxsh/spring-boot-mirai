package xyz.cssxsh.mirai.spring

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.data.*
import net.mamoe.mirai.console.extension.*
import net.mamoe.mirai.console.internal.data.builtins.*
import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.console.util.*
import net.mamoe.mirai.utils.*
import org.springframework.boot.*
import org.springframework.core.io.*
import java.io.*

public object SpringBootMiraiStarter : KotlinPlugin(
    JvmPluginDescription(
        id = "xyz.cssxsh.spring-boot-mirai-starter",
        name = "spring-shit-starter",
        version = "1.0.0",
    ) {
        author("cssxsh")
    }
) {

    init {
        @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE", "UNCHECKED_CAST")
        @OptIn(ConsoleExperimentalApi::class, ConsoleFrontEndImplementation::class)
        with(DataScope.get(PluginDependenciesConfig::class)) {
            val field = findBackingFieldValue(::repoLoc) as Value<List<String>>
            field.value += "https://repo.spring.io/milestone"
        }
    }

    override fun PluginComponentStorage.onLoad() {
        val yml = configFolder.parentFile.resolve("application.yml")
        if (yml.exists().not()) {
            val url = jvmPluginClasspath.pluginClassLoader.getResource("application.yml")
                ?: throw FileNotFoundException("application.yml")
            yml.writeBytes(url.readBytes())
        }
        logger.info { "SpringBoot Application Configuration File at:\n ${yml.path}" }
    }

    override fun onEnable() {
        SpringBootMiraApplication.context = runApplication<SpringBootMiraApplication> {
            resourceLoader = DefaultResourceLoader(SpringBootMiraiClassLoader(this@SpringBootMiraiStarter))
        }
    }

    override fun onDisable() {
        SpringBootMiraApplication.context.close()
    }
}