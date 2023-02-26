package xyz.cssxsh.mirai.spring.controller

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.description.*
import net.mamoe.mirai.console.plugin.jvm.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.boot.system.*
import org.springframework.web.bind.annotation.*
import xyz.cssxsh.mirai.spring.*
import xyz.cssxsh.mirai.spring.data.*
import java.util.*

@RestController
@RequestMapping("/mirai")
public class MiraiController {

    @GetMapping("/get_console_status")
    public fun getConsoleStatus(): MiraiConsoleStatus {
        return MiraiConsoleStatus(
            console = MiraiConsole.version,
            java = JavaVersion.getJavaVersion().toString(),
            kotlin = KotlinVersion.CURRENT.toString(),
            spring = SpringBootVersion.getVersion(),
            extension = "1.0.0",
            plugins = PluginManager.plugins.filterIsInstance<JvmPlugin>().associate { plugin ->
                plugin.description.id to plugin.description
            }
        )
    }

    @GetMapping("/get_spring_plugins")
    public fun getSpringPlugins(): Map<String, JvmPluginDescription> {
        val plugins = HashMap<String, JvmPluginDescription>()
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            if (plugin::class.java.packageName !in SpringBootMiraiStartupExtension.classLoaders) continue

            plugins[plugin::class.java.packageName] = plugin.description
        }

        return plugins
    }
}