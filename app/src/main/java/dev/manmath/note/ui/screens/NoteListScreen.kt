package dev.manmath.note.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.manmath.note.data.Note
import dev.manmath.note.ui.components.*
import dev.manmath.note.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    notes: List<Note>,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onNoteClick: (Long) -> Unit,
    onNewNote: () -> Unit,
    onDeleteNote: (Long) -> Unit,
    onTogglePin: (Note) -> Unit
) {
    var isSearchActive by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        containerColor = CreamPaper,
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNewNote,
                shape = CircleShape,
                containerColor = MarkerOrange,
                contentColor = CreamPaper,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Note",
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Header
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Brand mark
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(MarkerOrange),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "s",
                                style = HeadingSmStyle,
                                color = CreamPaper
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "superr notes",
                            style = HeadingSmStyle,
                            color = CocoaInk
                        )
                    }

                    // Search toggle
                    IconButton(onClick = { isSearchActive = !isSearchActive }) {
                        Icon(
                            imageVector = if (isSearchActive) Icons.Default.Close else Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Charcoal,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }

            // Search bar
            AnimatedVisibility(
                visible = isSearchActive,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 4.dp)
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("search your notes...", style = BodyStyle, color = Charcoal.copy(alpha = 0.4f))
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = null, tint = Charcoal.copy(alpha = 0.5f))
                        },
                        textStyle = BodyStyle.copy(color = CocoaInk),
                        shape = PillShape,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Charcoal,
                            unfocusedBorderColor = Charcoal.copy(alpha = 0.3f),
                            cursorColor = MarkerOrange,
                            focusedContainerColor = CreamPaper,
                            unfocusedContainerColor = DewDrop
                        ),
                        singleLine = true
                    )
                }
            }

            // Notes count
            if (notes.isNotEmpty()) {
                Text(
                    text = "${notes.size} ${if (notes.size == 1) "note" else "notes"}",
                    style = CaptionStyle,
                    color = Charcoal.copy(alpha = 0.5f),
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
                )
            }

            // Notes grid
            if (notes.isEmpty()) {
                // Empty state
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Decorative sticker cluster
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        LightningSticker(rotation = -10f)
                        HeartSticker(rotation = 8f)
                        SparkleSticker(color = SkySticker, rotation = 15f)
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Text(
                        text = "no notes yet",
                        style = HeadingStyle,
                        color = CocoaInk
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "tap the + button to create your first note",
                        style = BodyStyle,
                        color = Charcoal.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Handwritten caption
                    Text(
                        text = "let's get started ✨",
                        style = HandwrittenCaptionStyle,
                        color = MarkerOrange
                    )
                }
            } else {
                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalItemSpacing = 12.dp,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(notes, key = { it.id }) { note ->
                        NoteCard(
                            note = note,
                            onClick = { onNoteClick(note.id) },
                            onLongClick = { showDeleteDialog = note.id },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Swipe-to-delete via long press dialog
                        if (showDeleteDialog == note.id) {
                            AlertDialog(
                                onDismissRequest = { showDeleteDialog = null },
                                title = { Text("Delete note?", style = HeadingSmStyle, color = CocoaInk) },
                                text = { Text("This note will be gone forever. No take-backs!", style = BodySmStyle, color = Charcoal) },
                                confirmButton = {
                                    PillButtonAccent(text = "Delete") {
                                        onDeleteNote(note.id)
                                        showDeleteDialog = null
                                    }
                                },
                                dismissButton = {
                                    PillButton(text = "Keep it") {
                                        showDeleteDialog = null
                                    }
                                },
                                containerColor = CreamPaper,
                                shape = CardShape
                            )
                        }
                    }
                }
            }
        }
    }
}
