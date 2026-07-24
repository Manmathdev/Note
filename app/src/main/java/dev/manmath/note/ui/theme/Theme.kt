package dev.manmath.note.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SuperrLightColorScheme = lightColorScheme(
    primary = MarkerOrange,
    onPrimary = CreamPaper,
    primaryContainer = NoteAccentOrange,
    onPrimaryContainer = CocoaInk,
    secondary = BurntSienna,
    onSecondary = CreamPaper,
    secondaryContainer = DewDrop,
    onSecondaryContainer = CocoaInk,
    tertiary = SkySticker,
    onTertiary = CreamPaper,
    background = CreamPaper,
    onBackground = CocoaInk,
    surface = CreamPaper,
    onSurface = Charcoal,
    surfaceVariant = DewDrop,
    onSurfaceVariant = CocoaInk,
    outline = Charcoal,
    outlineVariant = ShadowMist,
    error = BurntSienna,
    onError = CreamPaper,
)

@Composable
fun SuperrNoteTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = SuperrLightColorScheme,
        typography = MaterialTheme.typography.copy(
            displayLarge = DisplayStyle,
            headlineLarge = HeadingLgStyle,
            headlineMedium = HeadingStyle,
            headlineSmall = HeadingSmStyle,
            titleMedium = SubheadingStyle,
            bodyLarge = BodyStyle,
            bodyMedium = BodySmStyle,
            bodySmall = CaptionStyle,
            labelLarge = ButtonTextStyle,
        ),
        shapes = SuperrShapes,
        content = content
    )
}
