package com.udacity.notepad

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.udacity.notepad.crud.CreateActivity
import com.udacity.notepad.data.Note
import com.udacity.notepad.recycler.NotesAdapter
import com.udacity.notepad.util.InjectorUtils
import com.udacity.notepad.util.SpaceItemDecoration
import com.udacity.notepad.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    private var recycler: RecyclerView? = null
    var notes: List<Note> = emptyList()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(
            this,
            InjectorUtils.provideNoteViewModelFactory(this@MainActivity)
        ).get(NoteViewModel::class.java)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener { startActivity(CreateActivity.get(this@MainActivity)) }

        viewModel.getNotes().observe(this){
            value -> value?.let {
                notes = it
            }
        }

        recycler = findViewById(R.id.recycler)
        recycler!!.setLayoutManager(LinearLayoutManager(this))
        recycler!!.addItemDecoration(SpaceItemDecoration(this, R.dimen.margin_small))
        recycler!!.setAdapter(NotesAdapter(this, notes))

    }


    public override fun onDestroy() {
        super.onDestroy()
        recycler!!.adapter = null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
