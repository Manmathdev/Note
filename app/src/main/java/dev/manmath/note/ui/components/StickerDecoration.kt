package dev.manmath.note.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import dev.manmath.note.ui.theme.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * Decorative sticker illustrations — scattered personality elements
 * These are NOT functional UI; purely decorative.
 */

@Composable
fun LightningSticker(modifier: Modifier = Modifier, rotation: Float = 12f) {
    Box(modifier = modifier.size(32.dp)) {
        Canvas(modifier = Modifier.size(32.dp)) {
            rotate(rotation) {
                val path = Path().apply {
                    moveTo(size.width * 0.55f, 0f)
                    lineTo(size.width * 0.2f, size.height * 0.45f)
                    lineTo(size.width * 0.45f, size.height * 0.45f)
                    lineTo(size.width * 0.35f, size.height)
                    lineTo(size.width * 0.8f, size.height * 0.4f)
                    lineTo(size.width * 0.55f, size.height * 0.4f)
                    close()
                }
                drawPath(path, color = SkySticker)
                drawPath(path, color = Charcoal, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round))
            }
        }
    }
}

@Composable
fun HeartSticker(modifier: Modifier = Modifier, rotation: Float = -8f) {
    Box(modifier = modifier.size(28.dp)) {
        Canvas(modifier = Modifier.size(28.dp)) {
            rotate(rotation) {
                val path = Path().apply {
                    moveTo(size.width * 0.5f, size.height * 0.35f)
                    cubicTo(
                        size.width * 0.5f, size.height * 0.2f,
                        size.width * 0.8f, size.height * 0.05f,
                        size.width * 0.85f, size.height * 0.3f
                    )
                    cubicTo(
                        size.width * 0.9f, size.height * 0.55f,
                        size.width * 0.5f, size.height * 0.8f,
                        size.width * 0.5f, size.height * 0.95f
                    )
                    cubicTo(
                        size.width * 0.5f, size.height * 0.8f,
                        size.width * 0.1f, size.height * 0.55f,
                        size.width * 0.15f, size.height * 0.3f
                    )
                    cubicTo(
                        size.width * 0.2f, size.height * 0.05f,
                        size.width * 0.5f, size.height * 0.2f,
                        size.width * 0.5f, size.height * 0.35f
                    )
                }
                drawPath(path, color = MarkerOrange)
                drawPath(path, color = Charcoal, style = Stroke(width = 2.dp.toPx(), cap = StrokeCap.Round))
            }
        }
    }
}

@Composable
fun SparkleSticker(modifier: Modifier = Modifier, color: Color = SproutSticker, rotation: Float = 5f) {
    Box(modifier = modifier.size(20.dp)) {
        Canvas(modifier = Modifier.size(20.dp)) {
            rotate(rotation) {
                val cx = size.width / 2
                val cy = size.height / 2
                // 4-point star
                val path = Path().apply {
                    moveTo(cx, 0f)
                    lineTo(cx + 3.dp.toPx(), cy - 3.dp.toPx())
                    lineTo(size.width, cy)
                    lineTo(cx + 3.dp.toPx(), cy + 3.dp.toPx())
                    lineTo(cx, size.height)
                    lineTo(cx - 3.dp.toPx(), cy + 3.dp.toPx())
                    lineTo(0f, cy)
                    lineTo(cx - 3.dp.toPx(), cy - 3.dp.toPx())
                    close()
                }
                drawPath(path, color = color)
                drawPath(path, color = Charcoal, style = Stroke(width = 1.5.dp.toPx()))
            }
        }
    }
}

@Composable
fun StickerCluster(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LightningSticker(rotation = 12f)
    }
}
