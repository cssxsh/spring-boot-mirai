package xyz.cssxsh.demo.spring

import org.slf4j.*
import org.springframework.context.annotation.*
import java.util.UUID

@ComponentScan
public open class SpringBootMiraiDemoConfiguration {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    public val uuid: UUID = UUID.randomUUID()

    init {
        logger.info("init... uuid: $uuid")
    }
}