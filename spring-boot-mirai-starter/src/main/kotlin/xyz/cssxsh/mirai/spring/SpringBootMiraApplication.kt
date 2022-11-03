package xyz.cssxsh.mirai.spring

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.*

@SpringBootApplication
public open class SpringBootMiraApplication {

    public companion object {
        @JvmStatic
        public lateinit var context: ConfigurableApplicationContext
            internal set

        @JvmStatic
        public fun main(vararg args: String) {
            context = runApplication<SpringBootMiraApplication>(args = args)
        }
    }
}