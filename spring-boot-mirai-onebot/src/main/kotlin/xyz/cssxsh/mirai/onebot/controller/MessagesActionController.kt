package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.meta.*
import java.util.*

/**
 * [message actions](https://12.onebot.dev/interface/message/actions/)
 */
@RestController
public class MessagesActionController {
    @RequestMapping("/send_message")
    public fun sendMessage(): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/delete_message")
    public fun deleteMessage(messageId: String): JsonElement = JsonNull
}