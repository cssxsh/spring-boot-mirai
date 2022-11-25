package xyz.cssxsh.mirai.onebot.config;

import org.springframework.boot.context.properties.*;
import org.springframework.boot.context.properties.bind.*;

/**
 * 心跳配置
 *
 * @param enabled  是否启用心跳
 * @param interval 心跳间隔，单位：毫秒，必须大于 0
 */
@ConfigurationProperties(prefix = "onebot.meta.heartbeat")
public record OneBotMetaHeartbeatConfiguration(
        @DefaultValue(value = "true")
        Boolean enabled,
        @DefaultValue(value = "180000")
        Long interval
) {
    // ...
}
