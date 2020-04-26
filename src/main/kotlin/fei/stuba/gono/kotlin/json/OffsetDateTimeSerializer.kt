package fei.stuba.gono.kotlin.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import jdk.nashorn.internal.runtime.regexp.joni.Config.log
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/***
 * Custom serialization of OffsetDateTime to JSON. JSON format is yy-MM-ddThh:mm:ssOffset.
 * @see OffsetDateTime
 * @see StdSerializer
 */
@Slf4j
@Component
class OffsetDateTimeSerializer(t: Class<OffsetDateTime>?) : StdSerializer<OffsetDateTime>(t) {
    constructor() : this(null)

    override fun serialize(p0: OffsetDateTime?, p1: JsonGenerator?, p2: SerializerProvider?) {
        log.print("I AM HERE")
        var tmp = DateTimeFormatter.ISO_DATE_TIME.format(p0)
        if(tmp.lastIndexOf('Z') != -1)
        {
            tmp = tmp.substring(0,tmp.lastIndexOf('Z')) + "+00:00"
        }
        log.println(tmp)
        val index1 = tmp.lastIndexOf('.')
        if(index1!= -1)
        {
            var index2 = tmp.lastIndexOf('+')
            if(index2 == -1)
                index2 = tmp.lastIndexOf('-')
            tmp = tmp.substring(0,index1) + (tmp.substring(index2))
        }
        p1?.writeString(tmp)
    }
}
