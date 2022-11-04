package xyz.cssxsh.mirai.onebot.controller.meta

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.autoconfigure.web.servlet.*
import org.springframework.http.*
import org.springframework.test.web.servlet.*

@WebMvcTest
internal class MetaActionControllerTest(@Autowired private val mvc: MockMvc) {
    @Test
    fun latest() {
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
    fun supported() {
        mvc.get("/get_supported_actions") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun status() {
        mvc.get("/get_status") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }

    @Test
    fun version() {
        mvc.get("/get_version") {
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
        }
    }
}