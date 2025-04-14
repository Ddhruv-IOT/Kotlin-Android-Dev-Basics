package com.example.data_store_demo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "my_datastore")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen(applicationContext)
        }
    }
}

@Composable
fun AppScreen(context: Context) {
    val dataStore = context.dataStore
    val dataKey = stringPreferencesKey("key_data")
    val scope = rememberCoroutineScope()

    var storedData by remember { mutableStateOf("No Data") }
    var inputData by remember { mutableStateOf("") }
    var saveEnabled by remember { mutableStateOf(true) }

    // Load data from DataStore
    LaunchedEffect(true) {
        val prefs = dataStore.data.first()
        storedData = prefs[dataKey] ?: "No Data"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Stored Data:", fontSize = 18.sp)
        Text(text = storedData, fontSize = 16.sp)

        Text(text = "Enter Data:", fontSize = 18.sp)
        BasicTextField(
            value = inputData,
            onValueChange = { inputData = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                storedData = inputData
                if (saveEnabled) {
                    scope.launch {
                        dataStore.edit { prefs ->
                            prefs[dataKey] = inputData
                        }
                    }
                }
            }
        ) {
            Text("Submit")
        }

        Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Checkbox(
                checked = saveEnabled,
                onCheckedChange = { saveEnabled = it }
            )
            Text("Enable Save")
        }
    }
}
