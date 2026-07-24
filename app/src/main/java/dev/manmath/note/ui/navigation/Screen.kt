package dev.manmath.note.ui.navigation

sealed class Screen(val route: String) {
    data object NoteList : Screen("note_list")
    data object NoteEdit : Screen("note_edit/{noteId}") {
        fun createRoute(noteId: Long = 0L) = "note_edit/$noteId"
    }
}
