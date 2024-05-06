package com.tencent.karaoke.puppy.api


import com.tencent.karaoke.puppy.base.BaseData
import retrofit2.http.GET
import retrofit2.http.Path

interface PuppyApi {

    @GET("breeds/list/all")
    suspend fun getPuppyList(): BaseData<Map<String, List<String>>>


    @GET("breed/{puppyName}/images/random")
    suspend fun getPuppyUrl(@Path("puppyName") puppyName: String): BaseData<String>

}