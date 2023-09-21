package com.skydivers.domain.usecases

import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main


import kotlin.coroutines.CoroutineContext

abstract  class UseCase<T> {
    protected var parentJob: Job = Job()
    private var backgroundContext: CoroutineContext = IO
    private var foregroundContext: CoroutineContext = Main

    protected abstract suspend fun executeOnBackground():T
    fun execute(onComplete:(T) ->Unit, onError:(Throwable)->Unit){
        parentJob.cancel()
        parentJob = Job()

        CoroutineScope(foregroundContext).launch{
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
                                             block: suspend ()->X):Deferred<X>{
        return CoroutineScope(context).async{
            block.invoke()
        }
    }
}