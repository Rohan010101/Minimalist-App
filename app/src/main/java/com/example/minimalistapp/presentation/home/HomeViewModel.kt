package com.example.minimalistapp.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minimalistapp.domain.model.AppInfo
import com.example.minimalistapp.domain.usecase.GetInstalledAppsUseCase
import com.example.minimalistapp.util.TimeTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getInstalledAppsUseCase: GetInstalledAppsUseCase,
    @ApplicationContext private val context: Context
): ViewModel() {

    private val _time = MutableStateFlow(TimeTicker.timeFlow())

    private val _timeString = MutableStateFlow("")
    val timeString: StateFlow<String> = _timeString.asStateFlow()

    private val _dateString = MutableStateFlow("")
    val dateString: StateFlow<String> = _dateString.asStateFlow()

    private val _apps = MutableStateFlow<List<AppInfo>>(emptyList())
    val apps: StateFlow<List<AppInfo>> = _apps.asStateFlow()


    init {
        // start ticker
        TimeTicker.timeFlow().onEach { t ->
            _timeString.value = t
        }.launchIn(viewModelScope)
        _dateString.value = TimeTicker.dateString()

        loadApps()
    }


    private fun loadApps() {
        viewModelScope.launch {
            val dtoList = getInstalledAppsUseCase()
            _apps.value = dtoList
        }
    }


    fun launchApp(pkg: String) {
        val pm = context.packageManager
        val intent = pm.getLaunchIntentForPackage(pkg)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        if (intent != null) context.startActivity(intent)
    }

}