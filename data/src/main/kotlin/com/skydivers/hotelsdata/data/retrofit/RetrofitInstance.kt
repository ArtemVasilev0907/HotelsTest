package com.skydivers.hotelsdata.data.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit


object RetrofitInstance {

    private const val URL = "https://run.mocky.io/v3/"

    private interface OnConnectionTimeOutListener{
        fun onConnectionTimeOut()
    }

    //private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val logging = HttpLoggingInterceptor {
       println("httpOk:$it") }.setLevel(HttpLoggingInterceptor.Level.BODY)

    private val headerInterceptor = Interceptor { chain ->
        var request = chain.request()
        request = request.newBuilder().build()
        val responce = chain.proceed(request)
        responce
    }
    interface TimeoutInterceptor : Interceptor
    open class TimeoutInterceptorImpl : TimeoutInterceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            if (isConnectionTimedOut(chain))
                throw SocketTimeoutException()
            return chain.proceed(chain.request())
        }

        private fun isConnectionTimedOut(chain: Interceptor.Chain): Boolean {
            try {
                val response = chain.proceed(chain.request())
                val content = response.toString()
                response.close()
                println( "isConnectionTimedOut() => $content")
            } catch (e: SocketTimeoutException) {
                return true
            }
            return false
        }
    }

    private val timeoutInterceptor = object : TimeoutInterceptorImpl() {
        override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
        }
    }

    private val okHttp = OkHttpClient.Builder()//.callTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(headerInterceptor)
        .addInterceptor(logging)
        .addInterceptor(timeoutInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        //.readTimeout(30, TimeUnit.SECONDS)


    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())


    private val retrofit by lazy {
        builder.build()

    }
    val api: ServerHotelsStorage by lazy {
        retrofit.create(ServerHotelsStorage::class.java)
    }

}