package com.github.dant3.eshop

import com.github.dant3.eshop.ui.util.AsyncOperationLock
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async

class SimplePaginationController(val totalPages: Int, currentPageCount: Int = 1) : PaginationController {
    private var pageCount: Int = currentPageCount

    private val loadingLock = AsyncOperationLock<Unit>()

    override fun nextPageIndex(): Int = pageCount + 1

    override fun isNextPageAvailable(): Boolean = totalPages > pageCount
    override fun isNextPageLoading(): Boolean = loadingLock.isLocked

    override fun monitorPageLoading(loading: () -> Deferred<Unit>) {
        loadingLock.acquire {
            async(UI) {
                loading().await().apply {
                    pageCount += 1
                }
            }
        }
    }
}