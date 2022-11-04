package xyz.cssxsh.mirai.onebot.model.event

import xyz.cssxsh.mirai.onebot.model.*
import java.time.*
import java.util.*

/**
 * [event](https://12.onebot.dev/glossary/#event)
 * [event data](https://12.onebot.dev/connect/data-protocol/event/)
 */
public sealed interface OneBotEvent {
    public val id: UUID
    public val time: OffsetTime
    public val detail: String
    public val sub: String

    public interface Meta
    public interface Message : OneBotSelf
    public interface Notice : OneBotSelf
    public interface Request : OneBotSelf
}