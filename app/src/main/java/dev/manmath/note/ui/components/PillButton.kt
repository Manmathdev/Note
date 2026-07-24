package dev.manmath.note.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.manmath.note.ui.theme.*

/**
 * Pill Action Button — Primary CTA surface
 * Cream fill, 1.5px Charcoal border, 20px pill radius
 */
@Composable
fun PillButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        enabled = enabled,
        shape = PillShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = CreamPaper,
            contentColor = Charcoal,
            disabledContainerColor = CreamPaper.copy(alpha = 0.5f),
            disabledContentColor = Charcoal.copy(alpha = 0.4f)
        ),
        border = BorderStroke(1.5.dp, Charcoal),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp
        ),
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = ButtonTextStyle
        )
    }
}

/**
 * Pill Button variant with orange accent border
 */
@Composable
fun PillButtonAccent(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(44.dp),
        enabled = enabled,
        shape = PillShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MarkerOrange,
            contentColor = CreamPaper,
            disabledContainerColor = MarkerOrange.copy(alpha = 0.5f),
            disabledContentColor = CreamPaper.copy(alpha = 0.6f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp
        ),
        contentPadding = PaddingValues(horizontal = 28.dp, vertical = 10.dp)
    ) {
        Text(
            text = text,
            style = ButtonTextStyle
        )
    }
}
