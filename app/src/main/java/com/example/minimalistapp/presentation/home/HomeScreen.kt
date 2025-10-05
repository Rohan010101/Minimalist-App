package com.example.minimalistapp.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.minimalistapp.presentation.components.AppRow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen (
    modifier: Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val time = viewModel.timeString.collectAsState()
    val date = viewModel.dateString.collectAsState()
    val apps = viewModel.apps.collectAsState()


    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = time.value, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = date.value, style = MaterialTheme.typography.bodyMedium)
        }


        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Apps", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(apps.value) { app ->
                AppRow(label = app.label, packageName = app.packageName, onClick = { viewModel.launchApp(app.packageName) })
            }
        }
    }

}