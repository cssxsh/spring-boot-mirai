package xyz.cssxsh.mirai.spring

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.*

@SpringBootApplication
public class SpringBootMiraiApplication {

    public companion object {
        @JvmStatic
        public lateinit var context: ConfigurableApplicationContext
            internal set

        @JvmStatic
        public fun main(vararg args: String) {
            context = runApplication<SpringBootMiraiApplication>(args = args)
        }
    }
}