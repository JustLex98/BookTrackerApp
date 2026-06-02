package com.example.booktrackerapp.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "book_notes",
    foreignKeys = [ForeignKey(
        entity = Book::class,
        parentColumns = ["id"],
        childColumns = ["bookId"],
        onDelete = ForeignKey.CASCADE // Si borras el libro, se borran sus notas
    )]
)
data class BookNote(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val bookId: Int,
    val content: String,
    val dateTimestamp: Long = System.currentTimeMillis()
)