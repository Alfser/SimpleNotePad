package com.udacity.notepad.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM note")
    fun getNotes() : LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE :id")
    fun getNote(id:Int):LiveData<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note:Note)

    @Delete
    fun deleteNote(note: Note)
}