package xyz.cssxsh.mirai.spring.demo

import org.slf4j.*
import org.springframework.context.annotation.*
import java.util.UUID

@Configuration
@ComponentScan
public open class SpringBootMiraiDemoConfiguration {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    public val uuid: UUID = UUID.randomUUID()

    init {
        logger.info("init... uuid: $uuid")
    }
}