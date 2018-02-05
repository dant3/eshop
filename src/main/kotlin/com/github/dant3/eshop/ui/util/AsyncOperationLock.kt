package com.github.dant3.eshop.ui.util

import kotlinx.coroutines.experimental.CoroutineStart
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.CoroutineContext

class AsyncOperationLock<T>(private val context: CoroutineContext = UI) {
    private var pendingOperation: Deferred<T>? = null

    val isLocked: Boolean
        get() = pendingOperation != null

    fun acquire(asyncOperation: () -> Deferred<T>) {
        if (pendingOperation == null) {
            val asyncOperationResult = asyncOperation()
            pendingOperation = asyncOperationResult
            launch (context, CoroutineStart.UNDISPATCHED) {
                asyncOperationResult.await()
                pendingOperation = null
            }
        }
    }
}