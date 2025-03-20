package jeck.only.andrlf.jni

import com.example.nativelib.NativeLib

fun main() {
    val nativeLib = NativeLib()
    println(nativeLib.stringFromJNI());
}