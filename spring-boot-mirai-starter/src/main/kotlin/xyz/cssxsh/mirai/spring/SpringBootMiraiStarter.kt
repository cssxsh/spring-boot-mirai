package xyz.cssxsh.mirai.spring

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.data.*
import net.mamoe.mirai.console.internal.data.builtins.*
import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.console.util.*
import org.springframework.boot.*
import org.springframework.core.io.*

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

    override fun onEnable() {
        SpringBootMiraApplication.context = runApplication<SpringBootMiraApplication> {
            resourceLoader = DefaultResourceLoader(SpringBootMiraiClassLoader(this@SpringBootMiraiStarter))
        }
    }

    override fun onDisable() {
        SpringBootMiraApplication.context.close()
    }
}