package com.umgmi.crono

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umgmi.crono.Model.TimeEntry
import com.umgmi.crono.Viewmodel.TimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CronometroApp()
        }
    }
}

@Composable
fun CronometroApp() {
    val timeViewModel: TimeViewModel = hiltViewModel()
    val timeEntries by timeViewModel.timeEntries.observeAsState(listOf())

    Column(modifier = Modifier.padding(16.dp)) {
        // Título de la pantalla
        Text(text = "Cronómetros", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar los cronómetros
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(timeEntries) { timeEntry ->
                CronometroCard(timeEntry = timeEntry, onDelete = { timeViewModel.deleteTimeEntry(it) })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para agregar un nuevo cronómetro
        Button(onClick = { timeViewModel.addTimeEntry(TimeEntry(name = "Nuevo Cronómetro", time = 0)) }) {
            Text("Agregar Cronómetro")
        }
    }
}

@Composable
fun CronometroCard(timeEntry: TimeEntry, onDelete: (TimeEntry) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = 4.dp // Usando el valor de elevación directamente
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = timeEntry.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = "Tiempo: ${timeEntry.time} ms", style = MaterialTheme.typography.bodyMedium)
            }

            IconButton(onClick = { onDelete(timeEntry) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}

fun Card(modifier: Modifier, elevation: Dp, content: @Composable() (ColumnScope.() -> Unit)) {
    TODO("Not yet implemented")
}
