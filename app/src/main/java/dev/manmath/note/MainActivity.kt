package dev.manmath.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.manmath.note.ui.navigation.NoteNavHost
import dev.manmath.note.ui.theme.CreamPaper
import dev.manmath.note.ui.theme.SuperrNoteTheme
import dev.manmath.note.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            SuperrNoteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = CreamPaper
                ) {
                    NoteNavHost(viewModel = noteViewModel)
                }
            }
        }
    }
}
