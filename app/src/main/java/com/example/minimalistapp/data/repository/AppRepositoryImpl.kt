package com.example.minimalistapp.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import com.example.minimalistapp.domain.model.AppInfo
import com.example.minimalistapp.domain.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val context: Context
): AppRepository {

    private val pm: PackageManager get()  = context.packageManager

    /*TODO: Check this suppression*/
    @SuppressLint("QueryPermissionsNeeded")
    override suspend fun getInstalledApps(): List<AppInfo> = withContext(Dispatchers.IO) {
        val apps = mutableListOf<AppInfo>()
        val packages = pm.getInstalledApplications(PackageManager.GET_META_DATA)
        for (app in packages) {
            // Filter out system apps if desired; keep only launchable apps
            val intent = pm.getLaunchIntentForPackage(app.packageName)
            if (intent != null && pm.getLaunchIntentForPackage(app.packageName) != null) {
                val label = pm.getApplicationLabel(app).toString()
                apps.add(AppInfo(app.packageName, label))
            }
        }
        apps.sortedBy { it.label.lowercase()}   // <- last expression returned
    }
}