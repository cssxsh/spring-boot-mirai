package xyz.cssxsh.mirai.onebot

import org.springframework.boot.*
import org.springframework.boot.context.properties.*
import org.springframework.context.annotation.*
import xyz.cssxsh.mirai.onebot.config.*

@ComponentScan
@SpringBootConfiguration
@EnableConfigurationProperties(OneBotMetaHeartbeatConfiguration::class)
public class SpringBootMiraiOnebotConfiguration