package xyz.cssxsh.mirai.spring.data

import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import net.mamoe.mirai.console.util.*
import org.springframework.boot.jackson.*

@JsonComponent
public class SemVersionJacksonSerializer : JsonSerializer<SemVersion>() {
    override fun serialize(value: SemVersion, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.toString())
    }
}