package com.example.booktrackerapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun BookDetailScreen(bookId: Int, viewModel: BookViewModel) {
    val detail by viewModel.getBookDetail(bookId).collectAsState(initial = null)
    var noteText by remember { mutableStateOf("") }

    detail?.let { data ->
        Column(Modifier.padding(16.dp).fillMaxSize()) {
            Text(data.book.title, style = MaterialTheme.typography.headlineMedium)
            Text("Por ${data.book.author}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(data.book.synopsis, style = MaterialTheme.typography.bodyLarge)

            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
            Text("Notas", style = MaterialTheme.typography.titleLarge)

            LazyColumn(Modifier.weight(1f)) {
                items(data.notes.sortedByDescending { it.dateTimestamp }) { note ->
                    Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                        Text(note.content, modifier = Modifier.padding(8.dp))
                    }
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 8.dp)) {
                TextField(value = noteText, onValueChange = { noteText = it }, modifier = Modifier.weight(1f), placeholder = { Text("Escribe una nota...") })
                Button(onClick = { viewModel.addNote(bookId, noteText); noteText = "" }, modifier = Modifier.padding(start = 8.dp)) {
                    Text("Añadir")
                }
            }
        }
    }
}