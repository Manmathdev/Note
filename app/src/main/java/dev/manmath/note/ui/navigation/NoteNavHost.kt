package dev.manmath.note.ui.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.manmath.note.ui.screens.NoteEditScreen
import dev.manmath.note.ui.screens.NoteListScreen
import dev.manmath.note.viewmodel.NoteViewModel

@Composable
fun NoteNavHost(viewModel: NoteViewModel) {
    val navController = rememberNavController()
    val notes by viewModel.notes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.NoteList.route
    ) {
        composable(Screen.NoteList.route) {
            NoteListScreen(
                notes = notes,
                searchQuery = searchQuery,
                onSearchQueryChange = { viewModel.setSearchQuery(it) },
                onNoteClick = { noteId ->
                    navController.navigate(Screen.NoteEdit.createRoute(noteId))
                },
                onNewNote = {
                    navController.navigate(Screen.NoteEdit.createRoute(0L))
                },
                onDeleteNote = { noteId ->
                    viewModel.deleteNote(noteId)
                },
                onTogglePin = { note ->
                    viewModel.togglePin(note)
                }
            )
        }

        composable(
            route = Screen.NoteEdit.route,
            arguments = listOf(navArgument("noteId") { type = NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getLong("noteId") ?: 0L

            LaunchedEffect(noteId) {
                if (noteId > 0) {
                    viewModel.loadNote(noteId)
                } else {
                    viewModel.clearCurrentNote()
                }
            }

            val currentNote by viewModel.currentNote.collectAsState()

            NoteEditScreen(
                note = if (noteId > 0L) currentNote else null,
                onBack = { navController.popBackStack() },
                onSave = { title, content, colorAccent ->
                    viewModel.saveNote(
                        title = title,
                        content = content,
                        colorAccent = colorAccent,
                        noteId = if (noteId > 0L) noteId else null
                    )
                },
                onDelete = {
                    viewModel.deleteNote(noteId)
                    navController.popBackStack()
                },
                onTogglePin = {
                    currentNote?.let { viewModel.togglePin(it) }
                }
            )
        }
    }
}
