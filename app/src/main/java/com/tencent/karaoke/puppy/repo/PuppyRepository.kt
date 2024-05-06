package com.tencent.karaoke.puppy.repo

import com.tencent.karaoke.puppy.api.PuppyApi
import com.tencent.karaoke.puppy.base.BaseData
import com.tencent.karaoke.puppy.util.RetrofitUtil
import kotlinx.coroutines.delay

class PuppyRepository  {

    companion object {

        private val _repository: PuppyRepository by lazy {
            PuppyRepository()
        }

        val repository: PuppyRepository
            get() = _repository
    }

    private val service by lazy {
        RetrofitUtil.getService(PuppyApi::class.java)
    }

    /**
     * 获取列表
     */
    suspend fun getPuppyList(): BaseData<Map<String, List<String>>> {
        delay(2000)
        return service.getPuppyList()
    }

    /**
     * 获取图片
     */
    suspend fun getPuppyUrl(puppyName: String): BaseData<String> {
        return  service.getPuppyUrl(puppyName)
    }

}