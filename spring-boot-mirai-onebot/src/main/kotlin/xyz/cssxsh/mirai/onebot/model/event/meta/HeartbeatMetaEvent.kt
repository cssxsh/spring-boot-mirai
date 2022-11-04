package xyz.cssxsh.mirai.onebot.model.event.meta

import kotlinx.serialization.*
import xyz.cssxsh.mirai.onebot.model.*
import xyz.cssxsh.mirai.onebot.model.event.*
import java.time.*

@Serializable
public class HeartbeatMetaEvent(
    @SerialName("id")
    override val id: String,
    @Contextual
    @SerialName("interval")
    public val interval: Duration,
    @Contextual
    @SerialName("time")
    override val time: Instant = Instant.now(),
) : OneBotEvent.Meta, OneBotModel.Standard {
    @SerialName("type")
    public override val type: String = "meta"
    @SerialName("detail_type")
    public override val detail: String = "heartbeat"
    @Transient
    public override val sub: String = ""
}