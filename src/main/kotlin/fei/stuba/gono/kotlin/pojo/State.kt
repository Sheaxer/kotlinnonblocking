package fei.stuba.gono.kotlin.pojo

/***
 * State of order presented to user on FE based on provided BE technical states.
 */
enum class State {
    CREATED,
    CANCELLED,
    CLOSED
}