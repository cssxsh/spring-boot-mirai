package xyz.cssxsh.mirai.onebot.model.event

import xyz.cssxsh.mirai.onebot.model.*
import java.time.*

/**
 * [event](https://12.onebot.dev/glossary/#event)
 * [event data](https://12.onebot.dev/connect/data-protocol/event/)
 */
public sealed interface OneBotEvent {
    public val id: String
    public val time: Instant
    public val type: String
    public val detail: String
    public val sub: String

    public interface Meta : OneBotEvent
    public interface Message : OneBotEvent, OneBotSelf
    public interface Notice : OneBotEvent, OneBotSelf
    public interface Request : OneBotEvent, OneBotSelf
}