package xyz.cssxsh.mirai.onebot.model.event.meta

import kotlinx.serialization.*
import xyz.cssxsh.mirai.onebot.model.*
import xyz.cssxsh.mirai.onebot.model.event.*
import java.time.*

public class StatusUpdateMetaEvent(
    @SerialName("id")
    override val id: String,
    @SerialName("status")
    public val status: String,
    @Contextual
    @SerialName("time")
    override val time: Instant = Instant.now(),
) : OneBotEvent.Meta, OneBotModel.Standard {
    @SerialName("type")
    public override val type: String = "meta"
    @SerialName("detail_type")
    public override val detail: String = "status_update"
    @Transient
    public override val sub: String = ""
}