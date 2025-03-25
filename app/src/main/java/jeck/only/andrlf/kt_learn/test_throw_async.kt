package jeck.only.andrlf.kt_learn

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by JeckOnly on 2025/3/25
 * Describe:
 */

fun main() {
    CoroutineScope(Dispatchers.IO).launch {
        launch {
            delay(2000)
            println("2")
        }

        try {
            async {
                delay(200)
                throw IllegalStateException()
            }
        } catch (e: Exception) {
            println("catch: $e")
        }




    }

    Thread.sleep(99999)
}