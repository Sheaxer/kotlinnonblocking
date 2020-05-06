package fei.stuba.gono.kotlin.json

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import org.springframework.stereotype.Component
import java.time.OffsetDateTime

/***
 * <div class="en">Class that allows deserialization from JSON to an instance of OffsetDateTime.</div>
 * <div class="sk">Trieda ktorá zabezpečuje de-serializáciu z JSON reťazca na inštanciu triedy OffsetDateTime.</div>
 * @see OffsetDateTime
 * @see StdDeserializer
 */
@Component
class OffsetDateTimeDeserializer(vc: Class<*>?) : StdDeserializer<OffsetDateTime>(vc) {
    constructor(): this(null)

    override fun deserialize(p0: JsonParser?, p1: DeserializationContext?): OffsetDateTime {
        return OffsetDateTime.parse(p0?.text)
    }
}