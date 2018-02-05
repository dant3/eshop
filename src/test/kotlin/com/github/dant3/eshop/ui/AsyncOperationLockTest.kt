package com.github.dant3.eshop.ui

import android.annotation.SuppressLint
import com.github.dant3.eshop.ui.util.AsyncOperationLock
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import org.junit.Test
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

class AsyncOperationLockTest {
    @SuppressLint("NewApi")
    @Test fun it_should_wait_for_operation_completion_before_accepting_next_task() {
        val lock = AsyncOperationLock<Unit>(CommonPool)
        val future = CompletableFuture<Unit>()

        lock.acquire {
            async (CommonPool) {
                future.get()
            }
        }
        lock.acquire {
            throw IllegalStateException("Should not accept new async operation while previous is incompleted")
        }

        future.complete(Unit)
        // TODO: smell of a bad test, should use better mocking/stubbing to avoid such delays
        Thread.sleep(100)

        val newTaskAccepted = CompletableFuture<Unit>()
        lock.acquire {
            async (CommonPool) {
                newTaskAccepted.complete(Unit)
                Unit
            }
        }

        // if failed here, it means task was never accepted
        newTaskAccepted.get(100, TimeUnit.MILLISECONDS)
    }
}