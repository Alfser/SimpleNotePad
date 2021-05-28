package com.udacity.notepad.recycler

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.udacity.notepad.R
import com.udacity.notepad.data.DataStore
import com.udacity.notepad.data.Note
import com.udacity.notepad.recycler.NotesAdapter.NotesViewHolder
import java.util.*

class NotesAdapter(private val context: Context) : RecyclerView.Adapter<NotesViewHolder>() {
    private var notes: List<Note> = ArrayList()
    private var isRefreshing = false

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        refresh()
    }

    override fun getItemId(position: Int): Long {
        return notes[position].id.toLong()
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note = notes[position]
        holder.text.text = note.text
    }

    fun refresh() {
        if (isRefreshing) return
        isRefreshing = true
        DataStore.execute {
            val notes = DataStore.notes.getAll()
            Handler(Looper.getMainLooper()).post {
                this@NotesAdapter.notes = notes
                notifyDataSetChanged()
                isRefreshing = false
            }
        }
    }

    class NotesViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView get() = itemView.findViewById(R.id.text)

    }

    init {
        setHasStableIds(true)
    }
}