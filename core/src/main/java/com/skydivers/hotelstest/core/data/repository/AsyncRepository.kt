package com.skydivers.hotelstest.core.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlin.coroutines.CoroutineContext

abstract class AsyncRepository {
    private var parentJob: Job = Job()
    private val backgroundContext: CoroutineContext get() = parentJob + IO
    private val foregroundContext: CoroutineContext get() = parentJob + Default

    protected suspend fun <X> doBackgroundAsync(
        context: CoroutineContext = backgroundContext,
        block: suspend () -> X
    ): Deferred<X> {
        parentJob.cancelChildren()
        return CoroutineScope(context).async {
            block.invoke()
        }
    }
}