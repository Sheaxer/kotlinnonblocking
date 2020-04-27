package fei.stuba.gono.kotlin.nonblocking.pojo

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import fei.stuba.gono.kotlin.json.OffsetDateTimeDeserializer
import fei.stuba.gono.kotlin.json.OffsetDateTimeSerializer
import fei.stuba.gono.kotlin.pojo.*
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*

@Document(collection = "reportedOverlimitTransactions")
@JsonInclude(JsonInclude.Include.NON_NULL)
class ReportedOverlimitTransaction {
    /***
     * Internal identifier of order (provided as response after creation from BE)
     */
    @Id
     var id: String? = null

    /***
     * Order category determines whether reported overlimit transaction is withdraw in EUR or foreign currency.
     */
     var orderCategory: OrderCategory? = null

    /***
     * State of order presented to user on FE, value is mapped based on provided BE technical states.
     */
     var state: State? = null

    /***
     * Account number of the client (type: IBAN with optional BIC or local account number) where
     * withdraw will be realised.
     */
     var sourceAccount: AccountNO? = null

    /***
     * Id of client who will realize withdraw. On frontend we have to show client name and dato of birth.
     */
     var clientId: String? = null

    /***
     * Id of client identification with which will realize withdraw. On frontend we have to show number of
     * identification.
     */
     var identificationId: String? = null

    /***
     * Structure for vault. Detail information about withdrow amount.
     */
     var vault: List<Vault>? = null

    /***
     * Withdraw amount in defined currency (only EUR for DOMESTIC) and with precision (embedded AMOUNT type).
     */
     var amount: Money? = null

    /***
     * Requested due date entered by client (have to be in near future, minimal D+3),
     * date when withdraw order should be realized from user account.
     * Default value could be current business day +3 ISO date format: YYYY-MM-DD.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
     var transferDate: Date? = null

    /***
     * Client/teller note to related withdraw
     */
     var note: String? = null

    /***
     * Modification date indicates the last update of order done by user or BE system (read-only field provided by BE).
     * ISO dateTime format: YYYY-MM-DDThh:mm:ssZ
     */
    @JsonDeserialize(using = OffsetDateTimeDeserializer::class)
    @JsonSerialize(using = OffsetDateTimeSerializer::class)
    var modificationDate: OffsetDateTime = OffsetDateTime.now()
        get() = field.toInstant().atOffset(ZoneOffset.of(zoneOffset))
        set(modDate) {
            field = modDate
            zoneOffset = modDate.offset.id
        }
    /***
     * Id of organisation unit where client want to realize withdraw.
     */
     var organisationUnitID: String? = null

    /***
     * Id of employer who entered an transaction. In this case report over limit withdraw.
     */
     var createdBy: String? = null

    /***
     * Offset id of time zone.
     */
    @JsonIgnore
    private var zoneOffset: String? = null
}