package xyz.cssxsh.mirai.onebot.model.action


/**
 * [action-response data](https://12.onebot.dev/connect/data-protocol/action-response/)
*/
public interface OneBotActionResponse {
    /**
     * [example](https://12.onebot.dev/connect/data-protocol/action-response/#_3)
     */
    public val status: String
    public val retcode: String
    public val message: String
    public val data: Any
}