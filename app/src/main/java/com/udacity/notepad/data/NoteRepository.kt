package com.udacity.notepad.data

class NoteRepository private constructor(val noteDao: NoteDao) {


    fun getNotes() = noteDao.getNotes()

    fun getNote(id:Int) = noteDao.getNote(id)

    suspend fun insertNote(note:Note) = noteDao.insertNote(note)

    fun deleteNote(note: Note) = noteDao.deleteNote(note)

    companion object{

        @Volatile private var instance: NoteRepository? = null

        fun getInstance(noteDao: NoteDao) =
            instance?: synchronized(this){
                instance?: NoteRepository(noteDao).also { instance = it }
        }

    }
}