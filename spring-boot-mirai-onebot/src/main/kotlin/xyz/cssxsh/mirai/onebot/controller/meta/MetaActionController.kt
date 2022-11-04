package xyz.cssxsh.mirai.onebot.controller.meta

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.meta.*
import java.util.*

@RestController
public class MetaActionController(private val mapping: RequestMappingHandlerMapping) {
    @RequestMapping("/get_latest_events")
    public fun latest(limit: Long?, timeout: Long?): List<JsonObject> = emptyList()

    @RequestMapping("/get_supported_actions")
    public fun supported(): Set<String> = mapping.handlerMethods.keys.flatMapTo(TreeSet()) { it.directPaths }

    @RequestMapping("/get_status")
    public fun status(): OneBotStatus = OneBotStatus(good = true, bots = emptyList())

    @RequestMapping("/get_version")
    public fun version(): OneBotVersion = OneBotVersion(impl = "spring-boot-mirai-onebot", standard = "12", version = "114514")
}