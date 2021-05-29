package com.udacity.notepad.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.notepad.data.Note
import com.udacity.notepad.data.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel internal constructor(private val repository: NoteRepository): ViewModel() {

    fun getNotes(): LiveData<List<Note>> = repository.getNotes()

    fun getNote(id: Int): LiveData<Note> = repository.getNote(id)

    fun deleteNote(note: Note) = repository.deleteNote(note)

    fun insertNote(note: Note){
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

}