package xyz.cssxsh.mirai.onebot.controller

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.http.*
import org.springframework.test.web.servlet.*

@WebMvcTest
internal class MetaActionControllerTest(@Autowired private val mvc: MockMvc) {
    @Test
    fun getLatestEvents() {
        mvc.get("/get_latest_events", 100, 0) {
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
    fun getSupportedActions() {
        mvc.get("/get_supported_actions") {
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
    fun getStatus() {
        mvc.get("/get_status") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun getVersion() {
        mvc.get("/get_version") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }
}