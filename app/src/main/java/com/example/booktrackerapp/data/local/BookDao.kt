package com.example.booktrackerapp.data.local

import androidx.room.*
import com.example.booktrackerapp.data.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: Book): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: BookNote): Long

    @Transaction
    @Query("SELECT * FROM books WHERE id = :bookId")
    fun getBookWithNotes(bookId: Int): Flow<BookWithNotes>
}