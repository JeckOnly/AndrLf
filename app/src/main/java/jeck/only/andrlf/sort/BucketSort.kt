package jeck.only.andrlf.sort

import java.util.Collections
import kotlin.math.floor

/**
 * Created by JeckOnly on 2025/3/6
 * Describe:
 */


object BucketSort {
    fun bucketSort(arr: FloatArray, bucketSize: Int) {
        if (arr.size == 0) {
            return
        }

        // 找到数组中的最小值和最大值
        var minValue = arr[0]
        var maxValue = arr[0]
        for (i in 1..<arr.size) {
            if (arr[i] < minValue) {
                minValue = arr[i]
            } else if (arr[i] > maxValue) {
                maxValue = arr[i]
            }
        }

        // 初始化桶
        val bucketCount = floor(((maxValue - minValue) / bucketSize).toDouble()).toInt() + 1
        val buckets: MutableList<MutableList<Float>> = ArrayList(bucketCount)
        for (i in 0..<bucketCount) {
            buckets.add(ArrayList())
        }

        // 将元素分配到桶中
        for (value in arr) {
            val bucketIndex = floor(((value - minValue) / bucketSize).toDouble()).toInt()
            buckets[bucketIndex].add(value)
        }

        // 对每个桶进行排序
        for (bucket in buckets) {
            Collections.sort(bucket)
        }

        // 将排序后的元素合并到原数组中
        var index = 0
        for (bucket in buckets) {
            for (value in bucket) {
                arr[index++] = value
            }
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = floatArrayOf(0.42f, 0.32f, 0.33f, 0.52f, 0.37f, 0.47f, 0.51f)
        val bucketSize = 5

        println("排序前:")
        for (value in arr) {
            print("$value ")
        }

        bucketSort(arr, bucketSize)

        println("\n排序后:")
        for (value in arr) {
            print("$value ")
        }
    }
}