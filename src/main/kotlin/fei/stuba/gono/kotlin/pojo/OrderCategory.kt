package fei.stuba.gono.kotlin.pojo

/***
 * Order category determines whether reported overlimit transaction is withdraw in EUR or foreing currency
 */
enum class OrderCategory {
    DOMESTIC,
    FX
}