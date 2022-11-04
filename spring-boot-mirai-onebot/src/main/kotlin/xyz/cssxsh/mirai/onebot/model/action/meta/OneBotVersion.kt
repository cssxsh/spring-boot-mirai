package xyz.cssxsh.mirai.onebot.model.action.meta

import kotlinx.serialization.*

@Serializable
public class OneBotVersion(
    @SerialName("impl")
    public val impl: String,
    @SerialName("onebot_version")
    public val standard: String,
    @SerialName("version")
    public val version: String
)