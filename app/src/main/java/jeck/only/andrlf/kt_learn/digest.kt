package jeck.only.andrlf.kt_learn

import java.security.MessageDigest

enum class SHA2Algorithm(val algorithmName: String) {
    SHA224("SHA-224"),
    SHA256("SHA-256"),
    SHA384("SHA-384"),
    SHA512("SHA-512")
}

fun hashWithSHA2(input: String, algorithm: SHA2Algorithm): String {
    val bytes = MessageDigest
        .getInstance(algorithm.algorithmName)
        .digest(input.toByteArray())
    return bytes.toHex()
}

// 扩展函数：将ByteArray转为十六进制字符串
fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

// 使用示例
fun main() {
    val key = "mySecretKey"

    println("SHA-224: ${hashWithSHA2(key, SHA2Algorithm.SHA224)}")
    println("SHA-256: ${hashWithSHA2(key, SHA2Algorithm.SHA256)}")
    println("SHA-384: ${hashWithSHA2(key, SHA2Algorithm.SHA384)}")
    println("SHA-512: ${hashWithSHA2(key, SHA2Algorithm.SHA512)}")

    val str = "hello世界"
    val bytesDefault = str.toByteArray() // 使用默认编码（通常UTF-8）
    val bytesUtf8 = str.toByteArray(Charsets.UTF_8)
    val bytesUtf16 = str.toByteArray(Charsets.UTF_16)

// 不同编码会产生不同的字节数组
    println(bytesDefault.contentToString() == bytesUtf8.contentToString()) // true
    println(bytesUtf8.contentToString() == bytesUtf16.contentToString()) // false

// 因此会产生不同的哈希值
    // 实际上哈希算法为了平台无关性，只认字节数组
    val md = MessageDigest.getInstance("SHA-256")
    println(md.digest(bytesUtf8).toHex() == md.digest(bytesUtf16).toHex()) // false
}