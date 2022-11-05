package xyz.cssxsh.mirai.onebot.config;

import lombok.*;
import org.springframework.boot.context.properties.*;

@Data
@ConfigurationProperties(prefix = "onebot.meta.heartbeat")
public class OneBotMetaHeartbeatConfiguration {
    /**
     * 是否启用心跳
     */
    private Boolean enabled = true;
    /**
     * 心跳间隔，单位：毫秒，必须大于 0
     */
    private Long interval = 180_000L;
}
