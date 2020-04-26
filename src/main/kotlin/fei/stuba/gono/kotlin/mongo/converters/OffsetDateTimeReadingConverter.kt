package fei.stuba.gono.kotlin.mongo.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
/***
 * Custom converter for transforming object of Date class to OffsetDateTime object with offset UTC.
 * Needed because MongoDB cannot serialize objects of OffsetDateTime class.
 * @see OffsetDateTime
 * @see Date
 * @see ReadingConverter
 */
@ReadingConverter
class OffsetDateTimeReadingConverter : Converter<Date,OffsetDateTime> {
    override fun convert(p0: Date): OffsetDateTime? {
        return p0.toInstant().atOffset(ZoneOffset.UTC)
    }
}