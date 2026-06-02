package com.example.booktrackerapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booktrackerapp.data.entities.*
import com.example.booktrackerapp.data.local.BookDao
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BookViewModel(private val dao: BookDao) : ViewModel() {

    // Estado para el buscador
    val searchQuery = MutableStateFlow("")

    // Búsqueda Reactiva: Filtra la lista cada vez que cambia el texto
    @OptIn(ExperimentalCoroutinesApi::class)
    val books = searchQuery.flatMapLatest { query ->
        dao.getAllBooks().map { list ->
            if (query.isEmpty()) list
            else list.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.author.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addBook(title: String, author: String, synopsis: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            dao.insertBook(Book(title = title, author = author, synopsis = synopsis))
            onSuccess()
        }
    }

    fun addNote(bookId: Int, content: String) {
        if (content.isBlank()) return
        viewModelScope.launch {
            dao.insertNote(BookNote(bookId = bookId, content = content))
        }
    }

    fun getBookDetail(bookId: Int) = dao.getBookWithNotes(bookId)
}