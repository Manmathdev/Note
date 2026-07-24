package dev.manmath.note.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// We use system fonts as a fallback that approximates the gelica aesthetic:
// Rounded, soft sans-serif. In production, you'd bundle the gelica font.
val GelicaFont = FontFamily.Default
val GeistFont = FontFamily.Default

// Superr Type Scale
val DisplayStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 48.sp,
    lineHeight = 52.sp,
    letterSpacing = 0.sp
)

val HeadingLgStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 36.sp,
    lineHeight = 43.sp,
    letterSpacing = 0.sp
)

val HeadingStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 28.sp,
    lineHeight = 34.sp,
    letterSpacing = 0.sp
)

val HeadingSmStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Medium,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val SubheadingStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Medium,
    fontSize = 18.sp,
    lineHeight = 25.sp,
    letterSpacing = 0.sp
)

val BodyStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val BodySmStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 21.sp,
    letterSpacing = 0.sp
)

val CaptionStyle = TextStyle(
    fontFamily = GeistFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.sp
)

val ButtonTextStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val HandwrittenCaptionStyle = TextStyle(
    fontFamily = GelicaFont,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.sp
)
