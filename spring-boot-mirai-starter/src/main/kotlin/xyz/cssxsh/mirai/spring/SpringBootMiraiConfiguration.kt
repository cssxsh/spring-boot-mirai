package xyz.cssxsh.mirai.spring

import org.slf4j.*
import org.springframework.context.annotation.*

@Configuration
public open class SpringBootMiraiConfiguration {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    init {
        logger.info("init...")
    }
}