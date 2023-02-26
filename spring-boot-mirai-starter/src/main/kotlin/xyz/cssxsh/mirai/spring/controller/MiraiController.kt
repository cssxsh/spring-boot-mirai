package xyz.cssxsh.mirai.spring.controller

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.description.*
import net.mamoe.mirai.console.plugin.jvm.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.*
import org.springframework.web.bind.annotation.*
import xyz.cssxsh.mirai.spring.*
import java.util.*

@RestController
@RequestMapping("/mirai")
public class MiraiController {

    @GetMapping("/get_console_version")
    public fun getConsoleVersion(): String {
        return MiraiConsole.version.toString()
    }

    @GetMapping("/get_spring_boot_version")
    public fun getPluginVersion(): String {
        return SpringBootVersion.getVersion()
    }

    @GetMapping("/get_all_plugins")
    public fun getAllPlugins(): Map<String, Boolean> {
        val plugins = HashMap<String, Boolean>()
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            plugins[plugin.description.id] = plugin.isEnabled
        }

        return plugins
    }

    @GetMapping("/get_supported_plugins")
    public fun getSupportedPlugins(): Map<String, String> {
        val plugins = HashMap<String, String>()
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            if (plugin::class.java.packageName !in SpringBootMiraiStartupExtension.classLoaders) continue

            plugins[plugin.description.id] = plugin::class.java.packageName
        }

        return plugins
    }
}