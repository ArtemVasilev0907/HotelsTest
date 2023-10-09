package com.skydivers.hotelstest.core.domain



import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


import kotlin.coroutines.CoroutineContext

abstract  class UseCase<T> {
    private var parentJob: Job = Job()
    private val backgroundContext: CoroutineContext get() = parentJob + IO
    private val foregroundContext: CoroutineContext  get() = parentJob +  Main
    private val scope = CoroutineScope(foregroundContext)

    protected abstract suspend fun executeOnBackground():T
    fun execute(onComplete:(T) ->Unit, onError:(Throwable)->Unit){
        parentJob.cancel()
        parentJob = Job()

        scope.launch{
            try {
                val result = withContext(backgroundContext){
                    executeOnBackground()
                }
                onComplete.invoke(result)
            }catch (e: java.lang.Exception){
                onError(e)
            }catch (e:CancellationException){
                println("canceled by user")
            }
        }
    }

    protected suspend fun <X>backgroundAsync(context:CoroutineContext = backgroundContext,
                                             block: suspend ()->X): Deferred<X> {
        parentJob.cancelChildren()
        return CoroutineScope(context).async{
            block.invoke()
        }
    }
}