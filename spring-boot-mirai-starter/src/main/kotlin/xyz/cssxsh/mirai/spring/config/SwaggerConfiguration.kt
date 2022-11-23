package xyz.cssxsh.mirai.spring.config

import io.swagger.v3.oas.models.*
import io.swagger.v3.oas.models.info.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.*

@AutoConfiguration
public class SwaggerConfiguration {

    @Bean
    public fun buildOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(buildApiInfo())
    }

    private fun buildApiInfo(): Info {
        return Info()
            .title("API文档")
            .contact(
                Contact()
                    .name("cssxsh")
                    .url("https://github.com/cssxsh/spring-boot-mirai")
                    .email("cssxsh@gmail.com")
            )
            .license(
                License()
                    .name("AGPL-3.0")
                    .url("https://github.com/cssxsh/spring-boot-mirai/blob/main/LICENSE")
            )
            .version("1.0.0")
    }
}