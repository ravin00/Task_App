package com.example.taskapp.helper

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update



@Dao
interface TodoDao {
    @Query("SELECT * FROM todoTable ORDER BY timestamp DESC")
    fun getAllTodos(): List<Todo>

    @Insert
    fun insert(todo: Todo): Long

    @Update
    fun update(todo: Todo)

    @Delete
    fun delete(todo: Todo)
}
