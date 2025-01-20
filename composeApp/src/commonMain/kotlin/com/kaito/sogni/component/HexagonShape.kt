package com.kaito.sogni.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.RepeatMode.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class HexagonShape: Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ): Outline {
        val path = Path().apply {
            val width = size.width
            val height = size.height
            val radius = width / 2f
            moveTo(radius, 0f)
            lineTo(width, height / 4f)
            lineTo(width, height * 3 / 4f)
            lineTo(radius, height)
            lineTo(0f, height * 3 / 4f)
            lineTo(0f, height / 4f)
            close()
        }
        return Outline.Generic(path)
    }
}

@Composable
fun HexagonPulseEffect(color: Color = Color.Gray) {
    val infiniteTransition = rememberInfiniteTransition()

    // Scaling Animation
    val scale = infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = Reverse
        )
    )

    // Alpha (Fading) Animation
    val alpha = infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = Reverse
        )
    )

    Box(
        modifier = Modifier
            .size(120.dp)
            .graphicsLayer(
                scaleX = scale.value,
                scaleY = scale.value
            )
            .alpha(alpha.value)
            .border(2.dp, color, shape = HexagonShape())
    )
}