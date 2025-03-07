package jeck.only.andrlf.kt_learn

// test throw in finally
fun test_throw_in_finally() {
    try {
        println("try")
        throw Exception("try")
    } catch (e: Exception) {
        println("catch")
        throw Exception("catch")
    } finally {
        println("finally")
        throw Exception("finally")
    }

}

fun main() {
    try {
        test_throw_in_finally()
    } catch (e: Exception) {
        println("main catch: $e")
    }
}