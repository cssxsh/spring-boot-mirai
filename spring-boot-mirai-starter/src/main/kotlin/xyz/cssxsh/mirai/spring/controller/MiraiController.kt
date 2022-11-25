package xyz.cssxsh.mirai.spring.controller

import net.mamoe.mirai.console.*
import net.mamoe.mirai.console.plugin.*
import net.mamoe.mirai.console.plugin.description.*
import net.mamoe.mirai.console.plugin.jvm.*
import org.springframework.beans.factory.annotation.*
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

    @GetMapping("/get_plugin_version")
    public fun getPluginVersion(): String {
        return SpringBootMiraiStarter.version.toString()
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
    public fun getSupportedPlugins(): Set<String> {
        val plugins = HashSet<String>()
        for (plugin in PluginManager.plugins) {
            if (plugin !is JvmPlugin) continue
            if (plugin.description.dependencies.none { it.id == SpringBootMiraiStarter.description.id }) continue
        }

        return plugins
    }
}