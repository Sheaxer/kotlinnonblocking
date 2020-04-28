package fei.stuba.gono.kotlin.pojo

import javax.validation.constraints.NotNull

/***
 * Class representing structure for vault. Detail information about withdraw amount.
 */
class Vault {
    @get:NotNull
    var type: VaultType? = null
    var number: Int = 0
    var nominalValue: Int = 0
}