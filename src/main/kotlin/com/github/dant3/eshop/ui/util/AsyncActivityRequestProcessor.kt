package com.github.dant3.eshop.ui.util

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.CancellableContinuation
import kotlinx.coroutines.experimental.CancellationException
import kotlinx.coroutines.experimental.suspendCancellableCoroutine
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info

class AsyncActivityRequestProcessor(private val activity: AppCompatActivity) {
    private val log = AnkoLogger<AsyncActivityRequestProcessor>()
    private var expectingContinuations: MutableMap<Int, CancellableContinuation<ActivityResult>> = mutableMapOf()

    init {
        activity.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) fun onDestroyed() {
                destroy()
                activity.lifecycle.removeObserver(this)
            }
        })
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val continuation = expectingContinuations.remove(requestCode)
        log.debug { "Handling onActivityResult with requestCode = $requestCode, continuation found = $continuation" }
        if (continuation != null) {
            if (resultCode == Activity.RESULT_CANCELED) {
                continuation.cancel(CancellationException("Activity finished with code Activity.RESULT_CANCELED"))
            } else {
                continuation.resume(ActivityResult(resultCode, data))
            }
        }
    }

    fun destroy() {
        log.info("Activity destroyed, cancelling all activity callbacks, count of callbacks: ${expectingContinuations.size}")
        expectingContinuations.values.forEach { it.cancel(CancellationException("Activity destroyed, result will never be delivered")) }
        expectingContinuations.clear()
    }

    suspend fun startActivityForResult(intent: Intent, requestCode: Int): ActivityResult =
            suspendCancellableCoroutine { cancellableContinuation ->
                val previousContinuation = expectingContinuations.put(requestCode, cancellableContinuation)
                previousContinuation?.cancel(CancellationException("New job with same id was started"))
                activity.startActivityForResult(intent, requestCode)
            }
}