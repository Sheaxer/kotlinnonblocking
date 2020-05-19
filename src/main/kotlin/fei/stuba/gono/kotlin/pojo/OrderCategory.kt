package fei.stuba.gono.kotlin.pojo

/***
 * Class representing order category determines whether reported overlimit
 * transaction is withdraw in EUR or foreign currency
 * Trieda reprezentujúca kategóriu výberu určuje čí, dopredu náhlásená nadlimitová
 * transakcia je vybraná v EUR alebo v cudzej mene.
 */
enum class OrderCategory {
    DOMESTIC,
    FX
}