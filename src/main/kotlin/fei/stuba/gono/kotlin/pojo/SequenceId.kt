package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/***
 * Class holding data about custom sequences in MongoDB, used to store maximal used id for auto id generation.
 */
@Document(collection = "customSequences")
data class SequenceId (

    /***
     * Name of the sequence
     */
    @Id
    var id: String? = null,

    /***
     * Maximal value of id used to save entities.
     */
    var seq: Long = 0
)