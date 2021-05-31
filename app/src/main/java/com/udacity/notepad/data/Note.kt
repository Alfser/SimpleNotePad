package com.udacity.notepad.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    var id:Int=0,

    var text: String? = null,

    @ColumnInfo(name = "is_pinned")
    var isPinned:Boolean = false,

    @ColumnInfo(name = "create_at")
    var createdAt:Date = Date(),

    @ColumnInfo(name = "update_at")
    var updatedAt: Date? = null
){
    override fun toString() = id.toString()
}