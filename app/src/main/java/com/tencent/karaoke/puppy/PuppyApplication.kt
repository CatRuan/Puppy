package com.tencent.karaoke.puppy

import android.app.Application
import com.tencent.karaoke.puppy.util.RetrofitUtil

/**
 * Created by passyruan on 2024/4/29
 */
internal class PuppyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
       RetrofitUtil.initRetrofit(this)
    }
}