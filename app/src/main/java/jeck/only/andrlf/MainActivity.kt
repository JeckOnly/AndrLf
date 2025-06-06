package jeck.only.andrlf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.nativelib.NativeLib
import jeck.only.andrlf.feature.getInstalledApps
import jeck.only.andrlf.ui.theme.AndrLfTheme
import kotlinx.coroutines.launch
import kotlin.contracts.contract

class MainActivity : ComponentActivity() {

    private val nativeLib = NativeLib()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndrLfTheme {

                val scope = rememberCoroutineScope()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )

                        Button(
                            onClick = {
                                Log.d("jni", nativeLib.stringFromJNI())
                            }
                        ) {
                            Text("Call NativeLib")
                        }

                        Button(
                            onClick = {
                                Log.d("jni", nativeLib.romanToInt("IX").toString())
                            }
                        ) {
                            Text("Call NativeLib ramonToInt")
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    val appList = getInstalledApps(context = this@MainActivity)
                                    appList.forEach {
                                        Log.d("app", it.packageName.toString())
                                    }
                                }
                            }
                        ) {
                            Text("获取设备所有应用")
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndrLfTheme {
        Greeting("Android")
    }
}