package com.example.booktrackerapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.booktrackerapp.viewmodel.BookViewModel

@Composable
fun BookListScreen(viewModel: BookViewModel, onNavigateToAdd: () -> Unit, onNavigateToDetail: (Int) -> Unit) {
    val books by viewModel.books.collectAsState()
    val query by viewModel.searchQuery.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToAdd) { Icon(Icons.Default.Add, "Add") }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = query,
                onValueChange = { viewModel.searchQuery.value = it },
                label = { Text("Buscar por título o autor") },
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
            LazyColumn {
                items(books) { book ->
                    Card(modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onNavigateToDetail(book.id) }) {
                        Column(Modifier.padding(16.dp)) {
                            Text(book.title, style = MaterialTheme.typography.titleLarge)
                            Text(book.author, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }
        }
    }
}