package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.meta.*
import java.util.*

/**
 * [group actions](https://12.onebot.dev/interface/group/actions/)
 */
@RestController
public class GroupActionController {
    @RequestMapping("/get_group_info")
    public fun getGroupInfo(groupId: String): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_group_list")
    public fun getGroupList(): List<JsonObject> = emptyList()

    @RequestMapping("/get_group_member_info")
    public fun getGroupMemberInfo(groupId: String, userId: String): JsonObject = JsonObject(emptyMap())

    @RequestMapping("/get_group_member_list")
    public fun getGroupMemberList(groupId: String): List<JsonObject> = emptyList()

    @RequestMapping("/set_group_name")
    public fun setGroupName(groupId: String, groupName: String): JsonElement = JsonNull

    @RequestMapping("/leave_group")
    public fun leaveGroup(groupId: String): JsonElement = JsonNull
}