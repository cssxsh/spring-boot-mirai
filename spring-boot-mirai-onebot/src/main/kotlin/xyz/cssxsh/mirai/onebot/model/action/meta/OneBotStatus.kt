package xyz.cssxsh.mirai.onebot.model.action.meta

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
public class OneBotStatus(
    @SerialName("good")
    public val good: Boolean,
    @SerialName("bots")
    public val bots: List<JsonObject>,
)