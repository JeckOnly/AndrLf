package jeck.only.andrlf.kt_learn

import com.sjy.processor.ksp_learn.GenerateToString

/**
 * Created by JeckOnly on 2025/3/6
 * Describe:
 */
@GenerateToString
class TestGenerateToString(val name: String, val id: Int) {


}

@GenerateToString
class TestGenerateToString2(val wrong: Boolean) {


}

//fun main() {
////    TestGenerateToString("Jeck", 1).toString2()
//}