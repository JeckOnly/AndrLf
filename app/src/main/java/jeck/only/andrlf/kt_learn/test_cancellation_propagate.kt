package jeck.only.andrlf.kt_learn

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

//suspend fun main() {
//    coroutineScope {
//        launch {
//            delay(2000)
//            throw CancellationException("just cancell")
//        }
//
//        launch {
//            delay(3000)
//            println("not be affected")
//        }
//    }
//}

suspend fun main() {
    coroutineScope {
        launch {
            delay(2000)
            throw IllegalStateException("just cancell")
        }

        launch {
            delay(3000)
            println("be affected")
        }
    }
}