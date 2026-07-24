package dev.manmath.note.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import dev.manmath.note.NoteApplication
import dev.manmath.note.data.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = (application as NoteApplication).repository

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val notes: StateFlow<List<Note>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.allNotes
            } else {
                repository.searchNotes(query)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val _currentNote = MutableStateFlow<Note?>(null)
    val currentNote: StateFlow<Note?> = _currentNote.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun loadNote(id: Long) {
        viewModelScope.launch {
            _currentNote.value = repository.getNoteById(id)
        }
    }

    fun clearCurrentNote() {
        _currentNote.value = null
    }

    fun saveNote(title: String, content: String, colorAccent: Int, noteId: Long? = null) {
        viewModelScope.launch {
            val now = System.currentTimeMillis()
            if (noteId != null && noteId > 0) {
                val existing = repository.getNoteById(noteId)
                repository.update(
                    Note(
                        id = noteId,
                        title = title,
                        content = content,
                        colorAccent = colorAccent,
                        createdAt = existing?.createdAt ?: now,
                        updatedAt = now,
                        isPinned = existing?.isPinned ?: false
                    )
                )
            } else {
                repository.insert(
                    Note(
                        title = title,
                        content = content,
                        colorAccent = colorAccent,
                        createdAt = now,
                        updatedAt = now
                    )
                )
            }
        }
    }

    fun deleteNote(noteId: Long) {
        viewModelScope.launch {
            repository.deleteById(noteId)
        }
    }

    fun togglePin(note: Note) {
        viewModelScope.launch {
            repository.update(note.copy(isPinned = !note.isPinned, updatedAt = System.currentTimeMillis()))
        }
    }
}
