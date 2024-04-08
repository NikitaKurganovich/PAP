package dev.babananick.pap

/**
 * Sealed class with basic screen states
 */
sealed class ScreenStates {
    data object Loading : ScreenStates()
    data object Empty : ScreenStates()
    data class Error(val error: Throwable) : ScreenStates()
}