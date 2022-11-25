package xyz.cssxsh.mirai.onebot.controller

import kotlinx.serialization.json.*
import org.springframework.web.bind.annotation.*
import xyz.cssxsh.mirai.onebot.model.action.meta.*
import java.util.*

/**
 * [guild actions](https://12.onebot.dev/interface/guild/actions/)
 */
@RestController
public class GuildActionController {
    @RequestMapping("/get_guild_info")
    public fun getGuildInfo(guildId: String): JsonObject = throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_guild_list")
    public fun getGuildList(): List<JsonObject> = throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/set_guild_name")
    public fun setGuildName(guildId: String, guildName: String): JsonElement =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_guild_member_info")
    public fun getGuildMemberInfo(guildId: String, userId: String): JsonObject =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_guild_member_list")
    public fun getGuildMemberList(guildId: String): List<JsonObject> =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/leave_guild")
    public fun leaveGuild(guildId: String): List<JsonObject> = throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_channel_info")
    public fun getChannelInfo(guildId: String, channelId: String): JsonObject =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_channel_list")
    public fun getChannelList(guildId: String, joinedOnly: Boolean): List<JsonObject> =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/set_channel_name")
    public fun setChannelName(guildId: String, channelId: String, channelName: String): JsonElement =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_channel_member_info")
    public fun getChannelMemberInfo(guildId: String, channelId: String, userId: String): JsonObject =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/get_channel_member_list")
    public fun getChannelMemberList(guildId: String, channelId: String): List<JsonObject> =
        throw NoSuchMethodException("mirai no unsupported guild")

    @RequestMapping("/leave_channel")
    public fun leaveChannel(guildId: String, channelId: String): JsonElement =
        throw NoSuchMethodException("mirai no unsupported guild")
}