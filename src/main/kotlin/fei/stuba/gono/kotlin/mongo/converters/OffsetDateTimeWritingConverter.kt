package fei.stuba.gono.kotlin.mongo.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.WritingConverter
import java.time.OffsetDateTime
import java.util.*

/***
 * <div class="en">Custom converter for MongoDB that converts instance of OffsetDateTime class
 * to instance of Date class.
 * Needed because MongoDB cannot deserialize object of OffsetDateTime class.</div>
 * <div class="sk">Vlastný prevodník pre MongoDB, ktorý prevádza inštanciu
 * triedy OffsetDateTime na inštanciu triedy Date.
 * Potrebné, pretože MongoDB nemôže priamo deserializovať objekt
 * triedy OffsetDateTime iba objekt triedy Date.</div>
 * @see OffsetDateTime
 * @see Date
 * @see WritingConverter
 */
@WritingConverter
class OffsetDateTimeWritingConverter : Converter<OffsetDateTime, Date> {
    override fun convert(p0: OffsetDateTime): Date? {
        return Date.from(p0.toInstant())
    }
}