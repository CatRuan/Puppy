package com.tencent.karaoke.puppy.util

import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitUtil {

    private var mRetrofit: Retrofit? = null

    lateinit var mContext: Application


    private val mOkClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .build()


    fun initRetrofit(context: Application): RetrofitUtil {
        mContext = context
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/")
            .client(mOkClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    /**
     * 获取okhttp client
     */
    fun getOkHttpClient() = mOkClient

    fun <T> getService(serviceClass: Class<T>): T {
        if (mRetrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit必须初始化")
        } else {
            return mRetrofit!!.create(serviceClass)
        }
    }
}