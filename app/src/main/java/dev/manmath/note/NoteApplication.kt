package dev.manmath.note

import android.app.Application
import dev.manmath.note.data.NoteDatabase
import dev.manmath.note.data.NoteRepository

class NoteApplication : Application() {
    val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.noteDao()) }
}
