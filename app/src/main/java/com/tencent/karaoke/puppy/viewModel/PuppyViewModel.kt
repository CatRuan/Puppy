package com.tencent.karaoke.puppy.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tencent.karaoke.puppy.base.BaseViewModel
import com.tencent.karaoke.puppy.data.PuppyQue
import com.tencent.karaoke.puppy.repo.PuppyRepository
import com.tencent.karaoke.puppy.util.PuppyUtils
import kotlinx.coroutines.delay

/**
 * Created by passyruan on 2024/4/28
 */
class PuppyViewModel : BaseViewModel() {

    private var puppyList: Map<String, List<String>>? = null
    private val _puppyQue = MutableLiveData<PuppyQue>()
    val puppyQue: LiveData<PuppyQue>
        get() = _puppyQue

    fun getPuppyList() {
        launchRequestWithFlow(
            success = {
                Log.i("PuppyViewModel", "lis")
                puppyList = it
                createNewPuppyQuestion()
            },
            request = { PuppyRepository.repository.getPuppyList() }
        )
    }

    fun createNewPuppyQuestion() {
        getPuppyQuestion()?.apply {
            getPuppyUrl(this)
        }
    }

    private fun getPuppyUrl(que: PuppyQue) {
        launchRequestWithFlow(
            success = {
                Log.i("PuppyViewModel", it.toString())
                que.url = it ?: ""
                _puppyQue.value = que
            },
            fail = { code, e ->
                Log.i("PuppyViewModel", "fail code:$code, e:${e.toString()}")
            },
            request = { PuppyRepository.repository.getPuppyUrl(que.answer) }
        )
    }

    private fun getPuppyQuestion(): PuppyQue? {
        val keysList = puppyList?.keys?.toMutableList()
        keysList?.let {
            val ans = PuppyUtils.getRandomName(it)
            keysList.remove(ans)
            val selects = PuppyUtils.getRandomNameList(it)
            selects.add(ans)
            selects.shuffle()
            return PuppyQue("", ans, selects)
        }
        return null
    }
}
