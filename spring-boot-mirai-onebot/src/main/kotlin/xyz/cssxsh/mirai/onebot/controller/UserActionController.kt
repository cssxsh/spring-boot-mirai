package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.meta.*
import java.util.*

/**
 * [user actions](https://12.onebot.dev/interface/user/actions/)
 */
@RestController
public class UserActionController {
    @RequestMapping("/get_self_info")
    public fun getSelfInfo(): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_user_info")
    public fun getUserInfo(userId: String): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_friend_list")
    public fun getFriendList(): List<JsonObject> = emptyList()
}