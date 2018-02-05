package com.github.dant3.eshop.ui.util

sealed class Try<out T> {
    companion object {
        operator fun <T> invoke(operation: () -> T): Try<T> = try {
            Success(operation())
        } catch (failure: Throwable) {
            Failure(failure)
        }

        suspend fun <T> coroutine(operation: suspend () -> T): Try<T> = try {
            Success(operation())
        } catch (failure: Throwable) {
            Failure(failure)
        }
    }
}

data class Success<out T>(val value: T) : Try<T>()
data class Failure(val error: Throwable): Try<Nothing>()