package fei.stuba.gono.kotlin.nonblocking.mongo.services

import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Projections
import fei.stuba.gono.kotlin.nonblocking.mongo.repositories.*
import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.pojo.*
import kotlinx.coroutines.reactive.awaitFirst
import kotlinx.coroutines.reactive.awaitFirstOrElse
import org.bson.Document
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.CollectionCallback
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.switchIfEmpty
import javax.validation.constraints.NotNull

@Service
class NextSequenceService @Autowired constructor(private val mongoOperations: ReactiveMongoOperations,
private val op: MongoOperations)
{
    /***
     * Increments the value of sequence with the given sequence name and return it. Uses kotlin
     * coroutines. Finds the sequence with given name, increments the value of maximal id and returns it as String.
     * @see SequenceId
     * @param seqName - name of the sequence.
     * @return - updated value of the sequence.
     */
    private suspend fun getNextSequence(seqName: String): String
    {
        return  mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").`is`(seqName)),
                Update().inc("seq", 1),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                SequenceId::class.java).switchIfEmpty { setNextSequence(seqName,"1") }.
        awaitFirst().seq.toString()
    }

    /***
     * Sets value of maximal id in a sequence with given name in MongoDB.
     * @see SequenceId
     * @param seqName name of the sequence,
     * @param value value that the sequence will be set to.
     * @return Mono containing SequenceId with modified value.
     */
    private  fun setNextSequence(seqName: String, value: String) : Mono<SequenceId>
    {
        return mongoOperations.findAndModify(
                Query.query(Criteria.where("_id").`is`(seqName)),
                Update().set("seq", java.lang.Long.valueOf(value)),
                FindAndModifyOptions.options().returnNew(true).upsert(true),
                SequenceId::class.java
        )
    }

    /***
     * Checks if the sequence with given name needs to update its maximal id value by the given value.
     * Uses kotlin coroutines.
     * @param seqName - name of the sequence, must not be null.
     * @param value - value to be checked against maximal id value, must not be null.
     */
    suspend fun needsUpdate(seqName: String, value: String)
    {
        try{
            val longVal = value.toLong()
           val seq = mongoOperations.find(
                Query.query(Criteria.where("_id").`is`(seqName)),SequenceId::class.java
            ).awaitFirstOrElse { SequenceId() }

            if(longVal > seq.seq)
                setNextSequence(seqName,value).awaitFirst()

        } catch (ex: NumberFormatException)
        {
            return
        }
    }
    /***
     * Retrieves new value of an id for saving new object in a repository. Updates maximal value
     * of sequence with the given name, checks if an entity with this id already exists in the repository.
     * If it does exist, function finds the actual maximal value of id used to store entities in the repository and
     * updates the sequence.
     * @param rep repository where the object will be saved.
     * @param seqName name of the sequence holding the id of last saved object.
     * @return value of id that should be used to save object in the given repository.
     */
    suspend fun getNewId(rep: ReactiveCrudRepository<*,String>, seqName: String) : String
    {
       var newId = this.getNextSequence(seqName)
        if(rep.existsById(newId).awaitFirstOrElse { false })
        {
            newId   = when (rep) {
                is ReportedOverlimitTransactionRepository -> lastId(ReportedOverlimitTransaction::class.java)
                is ClientRepository -> lastId(Client::class.java)
                is EmployeeRepository -> lastId(Employee::class.java)
                is OrganisationUnitRepository -> lastId(OrganisationUnit::class.java)
                is AccountRepository -> lastId(Account::class.java)
                else -> newId
            }

        }
        return newId
    }

   /* private suspend  fun lastId(rep: Class<*>): String
    {


    }*/
    /***
     * Calculates the maximal id that was used to save an object in the given repository.
     * Transforms ids of all entities of the given class into long and finds the max.
     * @param rep repository, must not be null.
     * @return String value of the maximal id in the given repository.
     */
    private fun lastId(rep:  Class<*>): String {
        return op.execute<String>(rep) { mongoCollection: MongoCollection<Document> ->
            val doc = mongoCollection.find().projection(Projections.include("_id"))
            val s = doc.map { document: Document ->
                try {
                    return@map document.getString("_id").toLong()
                } catch (e: NumberFormatException) {
                    return@map 0L
                }
            }
            var lastVal = 0L
            for (tmp in s) {
                lastVal = if (tmp > lastVal) tmp else lastVal
            }
            lastVal++
            lastVal.toString()
        } ?: "1"
    }
}