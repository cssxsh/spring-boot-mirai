package xyz.cssxsh.mirai.spring.data

import com.fasterxml.jackson.annotation.*
import com.fasterxml.jackson.databind.annotation.*
import net.mamoe.mirai.console.plugin.jvm.*
import net.mamoe.mirai.console.util.*

@JsonSerialize
public data class MiraiConsoleStatus(
    @JsonProperty("console_version")
    public val console: SemVersion,
    @JsonProperty("java_version")
    public val java: String,
    @JsonProperty("kotlin_version")
    public val kotlin: String,
    @JsonProperty("spring_boot_version")
    public val spring: String,
    @JsonProperty("spring_boot_mirai_version")
    public val extension: String,
    @JsonProperty("plugins")
    public val plugins: Map<String, JvmPluginDescription>,
)