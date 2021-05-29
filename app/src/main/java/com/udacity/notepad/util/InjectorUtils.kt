package com.udacity.notepad.util

import android.content.Context
import com.udacity.notepad.data.AppDatabase
import com.udacity.notepad.data.NoteRepository
import com.udacity.notepad.viewmodel.NoteViewModelFactory

object InjectorUtils {

    private fun getNoteRepository(context: Context): NoteRepository{
        return NoteRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).noteDao()
        )
    }

    fun provideNoteViewModelFactory(context: Context):NoteViewModelFactory{
        return NoteViewModelFactory(getNoteRepository(context))
    }
}