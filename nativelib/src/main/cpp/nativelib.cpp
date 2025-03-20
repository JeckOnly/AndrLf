#include <jni.h>
#include <string>
#include "Solution.h"  // 包含 Solution 类的头文件

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_NativeLib_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

// 新增 JNI 函数：调用 romanToInt
extern "C" JNIEXPORT jint JNICALL
Java_com_example_nativelib_NativeLib_romanToInt(  // 注意命名规则
        JNIEnv* env,
        jobject /* this */,
        jstring j_roman) {  // 接收 Java 层的字符串参数

    // 将 Java 字符串转换为 C++ 字符串
    const char* c_roman = env->GetStringUTFChars(j_roman, nullptr);
    std::string roman_str(c_roman);
    env->ReleaseStringUTFChars(j_roman, c_roman);  // 释放资源

    // 调用 Solution 类的功能
    Solution solution;
    int result = solution.romanToInt(roman_str);

    return static_cast<jint>(result);  // 返回结果给 Java 层
}
