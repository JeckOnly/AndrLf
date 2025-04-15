package jeck.only.andrlf.kt_learn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.Executors
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.system.measureTimeMillis

fun main() {
    val threadPool = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    CoroutineScope(EmptyCoroutineContext).launch {
        val timeUsed = measureTimeMillis {
            withContext(threadPool) {
                repeat(4) {
                    launch {
                        println("launch $it")
                        Thread.sleep(1000)
                    }
                }
            }

        }
        threadPool.close()

        println("time used: $timeUsed")
    }

    Thread.sleep(999999999)
}