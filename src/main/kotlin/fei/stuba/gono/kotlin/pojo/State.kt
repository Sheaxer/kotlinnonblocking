package fei.stuba.gono.kotlin.pojo

/***
 * State of order presented to user on
 * FE based on provided BE technical states.
 * Stav výberu predložený používateľovi na FE
 * poskytnutý na základe technického stavu BE.
 */
enum class State {
    CREATED,
    CANCELLED,
    CLOSED
}