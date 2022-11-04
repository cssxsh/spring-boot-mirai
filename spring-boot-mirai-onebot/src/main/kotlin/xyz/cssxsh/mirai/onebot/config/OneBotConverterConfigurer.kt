package xyz.cssxsh.mirai.onebot.config

import org.springframework.context.annotation.*
import org.springframework.http.converter.*
import org.springframework.http.converter.json.*
import org.springframework.web.servlet.config.annotation.*

@Configuration
public class OneBotConverterConfigurer : WebMvcConfigurer {
    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        super.configureMessageConverters(converters)
        converters.add(
            converters.indexOfFirst { it is KotlinSerializationJsonHttpMessageConverter },
            OneBotJsonHttpMessageConverter()
        )
    }
}