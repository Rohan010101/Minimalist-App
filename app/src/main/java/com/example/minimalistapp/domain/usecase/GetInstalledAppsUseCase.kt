package com.example.minimalistapp.domain.usecase

import com.example.minimalistapp.domain.model.AppInfo
import com.example.minimalistapp.domain.repository.AppRepository
import javax.inject.Inject

class GetInstalledAppsUseCase @Inject constructor(
    private val repository: AppRepository
) {
    suspend operator fun invoke(): List<AppInfo> =
        repository.getInstalledApps()
}