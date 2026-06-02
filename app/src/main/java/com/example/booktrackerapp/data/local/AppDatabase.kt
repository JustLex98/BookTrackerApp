package com.example.booktrackerapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booktrackerapp.data.entities.Book
import com.example.booktrackerapp.data.entities.BookNote

@Database(entities = [Book::class, BookNote::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}