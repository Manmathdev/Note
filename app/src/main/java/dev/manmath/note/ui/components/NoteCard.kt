package dev.manmath.note.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dev.manmath.note.data.Note
import dev.manmath.note.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * NoteCard — A notebook-style card displaying a note preview
 */
@Composable
fun NoteCard(
    note: Note,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val accentColor = when (note.colorAccent) {
        1 -> NoteAccentOrange
        2 -> NoteAccentBlue
        3 -> NoteAccentPink
        4 -> NoteAccentGreen
        else -> DewDrop
    }

    val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    val dateStr = dateFormat.format(Date(note.updatedAt))

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = CardShape,
        colors = CardDefaults.cardColors(containerColor = accentColor),
        border = BorderStroke(1.dp, Charcoal.copy(alpha = 0.15f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            // Header row with pin indicator
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (note.isPinned) {
                    Icon(
                        imageVector = Icons.Default.PushPin,
                        contentDescription = "Pinned",
                        tint = MarkerOrange,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = dateStr,
                    style = CaptionStyle,
                    color = Charcoal.copy(alpha = 0.6f)
                )
            }

            if (note.title.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.title,
                    style = HeadingSmStyle,
                    color = CocoaInk,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (note.content.isNotBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = note.content,
                    style = BodySmStyle,
                    color = Charcoal.copy(alpha = 0.8f),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (note.title.isBlank() && note.content.isBlank()) {
                Text(
                    text = "Empty note",
                    style = BodySmStyle,
                    color = Charcoal.copy(alpha = 0.4f)
                )
            }
        }
    }
}
