package fei.stuba.gono.kotlin.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/***
 * Class holding data about custom sequences in MongoDB,
 * used to store maximal used id for auto id generation.
 * Trieda uchovávajúca dáta o vlastný sekvenciách
 * v MongoDB použitých na uloženie maximálnej hodnoty id použitého na automatickú
 * id generáciu.
 */
@Document(collection = "customSequences")
data class SequenceId (

        /***
         * Name of the sequence
         * Názov sekvencie
         */
    @Id
    var id: String? = null,

        /***
         *  Maximal value of id used to save an entity.
         * Maximálne hodnota id použitá na uloženie entity.
         */
    var seq: Long = 0
)