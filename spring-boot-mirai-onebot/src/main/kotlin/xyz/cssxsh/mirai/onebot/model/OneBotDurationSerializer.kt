package xyz.cssxsh.mirai.onebot.model

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import java.time.*

public object OneBotDurationSerializer : KSerializer<Duration> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor(Duration::class.qualifiedName!!, PrimitiveKind.LONG)

    override fun deserialize(decoder: Decoder): Duration {
        return Duration.ofMinutes(decoder.decodeLong())
    }

    override fun serialize(encoder: Encoder, value: Duration) {
        encoder.encodeLong(value.toMillis())
    }
}