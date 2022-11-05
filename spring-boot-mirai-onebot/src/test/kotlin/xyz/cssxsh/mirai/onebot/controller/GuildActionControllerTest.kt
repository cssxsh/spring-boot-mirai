package xyz.cssxsh.mirai.onebot.controller

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.http.*
import org.springframework.test.web.servlet.*

@WebMvcTest
internal class GuildActionControllerTest(@Autowired private val mvc: MockMvc) {

    @Test
    fun getGuildInfo() {
    }

    @Test
    fun getGuildList() {
        mvc.get("/get_guild_list", 100, 0) {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }.andDo {
            print()
        }
    }

    @Test
    fun setGuildName() {
    }

    @Test
    fun getGuildMemberInfo() {
    }

    @Test
    fun getGuildMemberList() {
    }

    @Test
    fun leaveGuild() {
    }

    @Test
    fun getChannelInfo() {
    }

    @Test
    fun getChannelList() {
    }

    @Test
    fun setChannelName() {
    }

    @Test
    fun getChannelMemberInfo() {
    }

    @Test
    fun getChannelMemberList() {
    }

    @Test
    fun leaveChannel() {
    }
}