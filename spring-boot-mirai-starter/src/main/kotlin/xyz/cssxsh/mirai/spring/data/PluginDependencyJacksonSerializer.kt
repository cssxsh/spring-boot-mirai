package xyz.cssxsh.mirai.spring.data

import com.fasterxml.jackson.core.*
import com.fasterxml.jackson.databind.*
import net.mamoe.mirai.console.plugin.description.*
import org.springframework.boot.jackson.*

@JsonComponent
public class PluginDependencyJacksonSerializer : JsonSerializer<PluginDependency>() {
    override fun serialize(value: PluginDependency, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeString(value.toString())
    }
}