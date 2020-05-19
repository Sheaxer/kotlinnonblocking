package fei.stuba.gono.kotlin.nonblocking.validation

import fei.stuba.gono.kotlin.nonblocking.pojo.ReportedOverlimitTransaction
import fei.stuba.gono.kotlin.pojo.Currency
import fei.stuba.gono.kotlin.pojo.OrderCategory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
/***
 * Class implementing validaiton of ReportedOverlimitTransaction according to
 * FENiX - New FrontEnd solution API definition (version 0.1)
 * Trieda implementuje Validator rozhranie a validáciu inštancií tried
 * ReportedOverlimitTransaction podľa definície  FENiX - New FrontEnd solution API definition (version 0.1)
 *
 * @see ReportedOverlimitTransaction
 */
@Component
class ReportedOverlimitTransactionValidator @Autowired constructor(
        private val accountValidator: AccountValidator,
        private val transferDateValidator: TransferDateValidator,
        private val moneyValidator: MoneyValidator,
        private val bankingDayValidator: BankingDayValidator
): Validator {
    override fun validate(p0: Any, p1: Errors) {
        val transaction = p0 as ReportedOverlimitTransaction
        ValidationUtils.rejectIfEmpty(p1,"clientId","CLIENTID_INVALID")
        ValidationUtils.rejectIfEmpty(p1, "orderCategory", "ORDERCATEGORY_INVALID")
        ValidationUtils.rejectIfEmpty(p1, "sourceAccount", "SOURCEACCOUNT_INVALID")
        if (transaction.sourceAccount != null)
            ValidationUtils.invokeValidator(accountValidator, transaction.sourceAccount!!, p1)
        ValidationUtils.rejectIfEmptyOrWhitespace(p1, "identificationId", "IDENTIFICATIONID_INVALID")
        ValidationUtils.rejectIfEmpty(p1, "amount", "FIELD_INVALID")
        ValidationUtils.rejectIfEmpty(p1, "vault", "VAULT_INVALID")
        if (transaction.vault != null) {
            transaction.vault!!.stream().forEach { v ->
                if (v.type == null) p1.reject("VAULTTYPE_INVALID")
                if (v.number <= 0) p1.reject("VAULTNUMBER_INVALID")
                if (v.nominalValue <= 0) p1.reject("VAULTNOMINALVALUE_INVALID")
            }
        }
        ValidationUtils.rejectIfEmpty(p1, "transferDate", "TRANSFERDATE_INVALID")
        if (transaction.transferDate != null)
        {
            ValidationUtils.invokeValidator(bankingDayValidator, transaction.transferDate!!, p1)
            ValidationUtils.invokeValidator(transferDateValidator, transaction.transferDate!!, p1)
        }
        ValidationUtils.rejectIfEmpty(p1, "createdBy", "CREATEDBY_INVALID")
        ValidationUtils.rejectIfEmpty(p1, "organisationUnitID", "ORGANISATIONUNITID_INVALID")
        if (transaction.amount != null)
            ValidationUtils.invokeValidator(moneyValidator, transaction.amount!!, p1)

        if (transaction.orderCategory != null && transaction.amount != null && transaction.amount!!.currency != null)
        {
            if ((transaction.orderCategory == OrderCategory.FX
                            && transaction.amount!!.currency == Currency.EUR) ||
                    (transaction.orderCategory == OrderCategory.DOMESTIC
                            && transaction.amount!!.currency != Currency.EUR))
                p1.reject("CATEGORY_INVALID")
        }
    }

    override fun supports(p0: Class<*>): Boolean {
        return ReportedOverlimitTransaction::class.java.isAssignableFrom(p0)
    }
}