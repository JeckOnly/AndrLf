package jeck.only.andrlf.kt_learn

/**
 * Created by JeckOnly on 2025/3/4
 * Describe:
 */

// type erase limit
fun <T: Any> printClassName(obj: T) {
    println(obj::class.java.name)
}

//fun main() {
//    val listOfStrings: List<String> = listOf("Kotlin", "Java")
//    printClassName(listOfStrings) // The runtime only sees it as a List, not List<String>
//
//    printClassName(Person())
//}

class Person

// test crossInline
// example1和example3结果一样，因为return在lambda中，进行了非local返回，退出了外层的函数
// crossInline可以把local的返回限制在lambda中。

//Use crossinline when:
//
//You want the return in a lambda to behave like a normal return (exiting only the lambda).
//You are making a library, and you want to make sure users of your inline function don't accidentally use non-local returns, which could change the flow of their program in unexpected ways.

// Example 1: Non-Local Return (without crossinline)
inline fun doSomething(action: () -> Unit) {
    println("Start doSomething")
    action()
    println("End doSomething") // This might NOT be printed
}

fun test1() {
    doSomething {
        println("Inside lambda")
        return // This exits test1(), NOT just the lambda!
    }
    println("This will NOT be printed")
}

// Example 2: Local Return (with crossinline)
inline fun doSomethingSafely(crossinline action: () -> Unit) {
    println("Start doSomethingSafely")
    action()
    println("End doSomethingSafely") // This WILL be printed
}

fun test2() {
    doSomethingSafely {
        println("Inside lambda")
        return@doSomethingSafely // This exits ONLY the lambda, 这里直接使用return会报错
    }
    println("This WILL be printed")
}

// Example 3: Non-Local Return normal function
 fun doSomething3(action: () -> Unit) {
    println("Start doSomething")
    action()
    println("End doSomething")
}

fun test3() {
    doSomething {
        println("Inside lambda")
        return
    }
    println("This will NOT be printed")
}

fun main() {
    println("Running test1:")
    test1() // Output: Start doSomething, Inside lambda
    println("\nRunning test2:")
    test2() // Output: Start doSomethingSafely, Inside lambda, End doSomethingSafely, This WILL be printed
    println("\nRunning test3:")
    test3()
}