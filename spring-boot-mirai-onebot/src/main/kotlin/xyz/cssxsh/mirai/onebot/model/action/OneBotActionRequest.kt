package xyz.cssxsh.mirai.onebot.model.action

import xyz.cssxsh.mirai.onebot.model.*

/**
 * [action-request data](https://12.onebot.dev/connect/data-protocol/action-request/)
 */
public interface OneBotActionRequest : OneBotSelf {
    public val action: String
    public val params: Any
}