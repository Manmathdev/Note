package dev.manmath.note.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import dev.manmath.note.data.Note
import dev.manmath.note.ui.components.*
import dev.manmath.note.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    note: Note?,
    onBack: () -> Unit,
    onSave: (title: String, content: String, colorAccent: Int) -> Unit,
    onDelete: () -> Unit,
    onTogglePin: () -> Unit
) {
    var title by remember(note?.id) { mutableStateOf(note?.title ?: "") }
    var content by remember(note?.id) { mutableStateOf(note?.content ?: "") }
    var colorAccent by remember(note?.id) { mutableIntStateOf(note?.colorAccent ?: 0) }
    var showDeleteConfirm by remember { mutableStateOf(false) }
    var showColorPicker by remember { mutableStateOf(false) }

    val titleFocusRequester = remember { FocusRequester() }
    val isNewNote = note == null || note.id == 0L

    // Auto-focus title for new notes
    LaunchedEffect(isNewNote) {
        if (isNewNote) {
            titleFocusRequester.requestFocus()
        }
    }

    val accentColor = when (colorAccent) {
        1 -> NoteAccentOrange
        2 -> NoteAccentBlue
        3 -> NoteAccentPink
        4 -> NoteAccentGreen
        else -> CreamPaper
    }

    Scaffold(
        containerColor = accentColor,
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        onSave(title, content, colorAccent)
                        onBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Charcoal
                        )
                    }
                },
                actions = {
                    // Color picker toggle
                    IconButton(onClick = { showColorPicker = !showColorPicker }) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = "Color",
                            tint = Charcoal
                        )
                    }

                    // Pin toggle
                    if (!isNewNote) {
                        IconButton(onClick = onTogglePin) {
                            Icon(
                                imageVector = if (note?.isPinned == true) Icons.Default.PushPin else Icons.Default.PushPin,
                                contentDescription = "Pin",
                                tint = if (note?.isPinned == true) MarkerOrange else Charcoal.copy(alpha = 0.5f)
                            )
                        }

                        // Delete
                        IconButton(onClick = { showDeleteConfirm = true }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Charcoal.copy(alpha = 0.6f)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = accentColor
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            // Color picker
            if (showColorPicker) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "cover:", style = BodySmStyle, color = Charcoal.copy(alpha = 0.6f))
                    val colors = listOf(
                        0 to CreamPaper,
                        1 to NoteAccentOrange,
                        2 to NoteAccentBlue,
                        3 to NoteAccentPink,
                        4 to NoteAccentGreen
                    )
                    colors.forEach { (value, color) ->
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(color)
                                .border(
                                    width = if (colorAccent == value) 2.dp else 1.dp,
                                    color = if (colorAccent == value) MarkerOrange else Charcoal.copy(alpha = 0.2f),
                                    shape = CircleShape
                                )
                                .clickable {
                                    colorAccent = value
                                    showColorPicker = false
                                }
                        )
                    }
                }
            }

            // Title field
            TextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .focusRequester(titleFocusRequester),
                placeholder = {
                    Text(
                        "title...",
                        style = HeadingLgStyle,
                        color = Charcoal.copy(alpha = 0.3f)
                    )
                },
                textStyle = HeadingLgStyle.copy(color = CocoaInk),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    cursorColor = MarkerOrange
                )
            )

            // Divider — hand-drawn style
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp),
                color = Charcoal.copy(alpha = 0.1f),
                thickness = 1.dp
            )

            // Content field
            TextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 400.dp)
                    .padding(horizontal = 20.dp),
                placeholder = {
                    Text(
                        "start writing...",
                        style = BodyStyle,
                        color = Charcoal.copy(alpha = 0.3f)
                    )
                },
                textStyle = BodyStyle.copy(color = Charcoal),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedContainerColor = androidx.compose.ui.graphics.Color.Transparent,
                    focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                    cursorColor = MarkerOrange
                )
            )

            // Name label sticker decoration at the bottom
            if (!isNewNote && note != null) {
                Spacer(modifier = Modifier.height(32.dp))
                NameLabelSticker(
                    title = if (title.isNotBlank()) title else "untitled",
                    date = java.text.SimpleDateFormat("MMM d, yyyy", java.util.Locale.getDefault())
                        .format(java.util.Date(note.createdAt)),
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }

    // Delete confirmation dialog
    if (showDeleteConfirm) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirm = false },
            title = { Text("Delete this note?", style = HeadingSmStyle, color = CocoaInk) },
            text = { Text("This can't be undone. The note will be gone forever!", style = BodySmStyle, color = Charcoal) },
            confirmButton = {
                PillButtonAccent(text = "Delete") {
                    onDelete()
                    showDeleteConfirm = false
                }
            },
            dismissButton = {
                PillButton(text = "Keep it") {
                    showDeleteConfirm = false
                }
            },
            containerColor = CreamPaper,
            shape = CardShape
        )
    }
}

/**
 * Name Label Sticker — mimics a real school label
 */
@Composable
fun NameLabelSticker(
    title: String,
    date: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.width(220.dp),
        shape = RoundedCornerShape(8.dp),
        color = CreamPaper,
        border = BorderStroke(1.dp, Charcoal),
        shadowElevation = 2.dp
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = "Name: $title",
                style = BodySmStyle,
                color = CocoaInk
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Class: notes",
                style = CaptionStyle,
                color = Charcoal.copy(alpha = 0.7f)
            )
            Text(
                text = "Date: $date",
                style = CaptionStyle,
                color = Charcoal.copy(alpha = 0.7f)
            )
        }
    }
}
