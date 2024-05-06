package com.tencent.karaoke.puppy.util

import kotlin.random.Random

/**
 * Created by passyruan on 2024/4/29
 */
object PuppyUtils {

    fun getRandomNameList(list: List<String>): MutableList<String> {
        if (list.isEmpty()) return mutableListOf()
        val random = Random.Default
        val index1 = random.nextInt(0, list.size)
        var index2 = random.nextInt(0, list.size)
        while (index2 == index1) {
            index2 = random.nextInt(0, list.size)
        }
        return mutableListOf(list[index1], list[index2])
    }

    fun getRandomName(list: List<String>): String {
        if (list.isEmpty()) return ""
        val random = Random.Default
        val index = random.nextInt(0, list.size)
        return list[index]
    }
}