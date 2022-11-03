package xyz.cssxsh.mirai.spring.demo

import org.slf4j.*
import org.springframework.context.annotation.*

@Configuration
public open class SpringBootMiraiDemoConfiguration {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    init {
        logger.info("init...")
    }
}