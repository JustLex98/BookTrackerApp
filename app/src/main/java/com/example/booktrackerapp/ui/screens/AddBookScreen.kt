package com.example.booktrackerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun AddBookScreen(viewModel: BookViewModel, onBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var synopsis by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Agregar Nuevo Libro", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = author, onValueChange = { author = it }, label = { Text("Autor") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = synopsis, onValueChange = { synopsis = it }, label = { Text("Sinopsis") }, modifier = Modifier.fillMaxWidth(), minLines = 3)

        Button(
            onClick = { viewModel.addBook(title, author, synopsis, onBack) },
            modifier = Modifier.fillMaxWidth(),
            enabled = title.isNotBlank() && author.isNotBlank()
        ) {
            Text("Guardar Libro")
        }
    }
}