package jeck.only.andrlf.feature

import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager

/**
 * Created by JeckOnly on 2025/4/1
 * Describe:
 */

// Kotlin 示例
fun getInstalledApps(context: Context): List<ApplicationInfo> {
    val pm = context.packageManager
    val intent = Intent(Intent.ACTION_MAIN).apply {
        addCategory(Intent.CATEGORY_LAUNCHER)
    }

    // 获取所有响应 MAIN+LAUNCHER 的应用
    val resolveInfos = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL)

    // 提取应用信息
    return resolveInfos.map { it.activityInfo.applicationInfo }
}