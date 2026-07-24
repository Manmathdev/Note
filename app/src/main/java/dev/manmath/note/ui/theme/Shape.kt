package dev.manmath.note.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val SuperrShapes = Shapes(
    small = RoundedCornerShape(8.dp),    // inputs
    medium = RoundedCornerShape(12.dp),   // cards
    large = RoundedCornerShape(20.dp)     // buttons, tags
)

val PillShape = RoundedCornerShape(20.dp)
val CardShape = RoundedCornerShape(12.dp)
val FooterShape = RoundedCornerShape(topStart = 56.dp, topEnd = 56.dp)
val TagShape = RoundedCornerShape(20.dp)
