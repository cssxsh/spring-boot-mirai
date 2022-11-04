package xyz.cssxsh.mirai.onebot.model.action

import kotlinx.serialization.*


/**
 * [action-response data](https://12.onebot.dev/connect/data-protocol/action-response/)
*/
@Serializable
public class OneBotActionResponse<T>(
    @SerialName("status")
    public val status: String,
    /**
     * [retcode](https://12.onebot.dev/connect/data-protocol/action-response/#_3)
     */
    @SerialName("retcode")
    public val retcode: Long,
    @SerialName("message")
    public val message: String,
    @SerialName("data")
    public val data: T
)