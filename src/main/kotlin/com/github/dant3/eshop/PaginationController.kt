package com.github.dant3.eshop

import kotlinx.coroutines.experimental.Deferred

interface PaginationController {
    fun isNextPageAvailable(): Boolean
    fun isNextPageLoading(): Boolean
    fun nextPageIndex(): Int
    fun monitorPageLoading(loading: () -> Deferred<Unit>)
}