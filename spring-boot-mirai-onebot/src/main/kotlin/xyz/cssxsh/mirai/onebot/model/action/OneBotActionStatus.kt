package xyz.cssxsh.mirai.onebot.model.action

import kotlinx.serialization.*

@Serializable
public enum class OneBotActionStatus {
    @SerialName("failed")
    FAILED,
    @SerialName("ok")
    OK,
}