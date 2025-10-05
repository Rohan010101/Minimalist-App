package com.example.minimalistapp.domain.repository

import com.example.minimalistapp.domain.model.AppInfo

interface AppRepository {
    suspend fun getInstalledApps(): List<AppInfo>
}