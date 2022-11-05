package xyz.cssxsh.mirai.onebot.config

import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.*
import org.springframework.http.*
import org.springframework.http.converter.*
import xyz.cssxsh.mirai.onebot.model.*
import xyz.cssxsh.mirai.onebot.model.action.*

public class OneBotJsonHttpMessageConverter : KotlinSerializationStringHttpMessageConverter<Json>(
    Json {
        ignoreUnknownKeys = true
        serializersModule = SerializersModule {
            contextual(OneBotInstantSerializer)
            contextual(OneBotDurationSerializer)
        }
    },
    MediaType("application", "*+json")
) {
    override fun writeInternal(
        `object`: Any,
        serializer: KSerializer<Any>,
        format: Json,
        outputMessage: HttpOutputMessage
    ) {
        @OptIn(ExperimentalSerializationApi::class)
        if (`object` is OneBotActionResponse<*> && serializer.descriptor.serialName != `object`::class.qualifiedName) {
            val target = OneBotActionResponse.serializer(serializer)
            @Suppress("UNCHECKED_CAST")
            super.writeInternal(`object`, target as KSerializer<Any>, format, outputMessage)
        } else {
            super.writeInternal(`object`, serializer, format, outputMessage)
        }
    }
}