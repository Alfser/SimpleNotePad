package com.udacity.notepad.crud

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.udacity.notepad.R
import com.udacity.notepad.data.Note
import com.udacity.notepad.util.InjectorUtils
import com.udacity.notepad.viewmodel.NoteViewModel
import java.util.*

class CreateActivity: AppCompatActivity() {

    private lateinit var editText: TextView

    companion object {
        operator fun get(context: Context): Intent {
            return Intent(context, CreateActivity::class.java)
        }
    }

    //ViewModel
    private var viewModel: NoteViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        editText = findViewById(R.id.edit_text)

        viewModel = ViewModelProvider(
            this,
            InjectorUtils.provideNoteViewModelFactory(this@CreateActivity)
        ).get(NoteViewModel::class.java).also { this.viewModel = it }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_accept, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_accept -> {
                save()
                finish()
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun save() {

        val note = updateNote()
        viewModel!!.insertNote(note)
    }

    private fun updateNote(): Note {
        val note = Note()
        note.text = editText.text.toString()
        note.updatedAt = Date()
        return note
    }
}