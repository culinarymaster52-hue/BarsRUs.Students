package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.example.model.MeasuredIngredient
import com.example.ui.theme.AmberGold
import com.example.ui.theme.IceColor
import kotlin.math.sin

@Composable
fun VirtualShakerCanvas(
    pouredIngredients: List<MeasuredIngredient>,
    hasIce: Boolean,
    isShaking: Boolean,
    isStirring: Boolean,
    shakeProgress: Float,
    modifier: Modifier = Modifier
) {
    // Shake vibration transition
    val shakeTransition = rememberInfiniteTransition(label = "shaker_wobble")
    val shakeAngle by shakeTransition.animateFloat(
        initialValue = if (isShaking) -12f else 0f,
        targetValue = if (isShaking) 12f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(80, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "shake_angle"
    )

    val stirTransition = rememberInfiniteTransition(label = "stir_swirl")
    val stirOffset by stirTransition.animateFloat(
        initialValue = 0f,
        targetValue = 6.28f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "stir_phase"
    )

    val totalOz = pouredIngredients.sumOf { it.amountOz.toDouble() }.toFloat()
    // Standard Boston shaker capacity = ~28 oz max
    val fillFraction = (totalOz / 18.0f).coerceIn(0f, 0.9f)

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Apply shake rotation around center
        val currentAngle = if (isShaking) shakeAngle else 0f
        rotate(currentAngle, pivot = Offset(w * 0.5f, h * 0.5f)) {
            drawShaker(
                w = w,
                h = h,
                ingredients = pouredIngredients,
                fillFraction = fillFraction,
                hasIce = hasIce,
                isShaking = isShaking,
                isStirring = isStirring,
                stirPhase = stirOffset,
                shakeProgress = shakeProgress
            )
        }
    }
}

private fun DrawScope.drawShaker(
    w: Float,
    h: Float,
    ingredients: List<MeasuredIngredient>,
    fillFraction: Float,
    hasIce: Boolean,
    isShaking: Boolean,
    isStirring: Boolean,
    stirPhase: Float,
    shakeProgress: Float
) {
    val cx = w * 0.5f
    val tinTop = h * 0.15f
    val tinBottom = h * 0.88f
    val tinWidthTop = w * 0.65f
    val tinWidthBottom = w * 0.48f

    val topLeft = cx - tinWidthTop / 2
    val topRight = cx + tinWidthTop / 2
    val bottomLeft = cx - tinWidthBottom / 2
    val bottomRight = cx + tinWidthBottom / 2

    // Shaker Tin Path (Tapered stainless steel)
    val shakerPath = Path().apply {
        moveTo(topLeft, tinTop)
        lineTo(bottomLeft + 8f, tinBottom - 8f)
        arcTo(Rect(bottomLeft, tinBottom - 16f, bottomLeft + 16f, tinBottom), 90f, 90f, false)
        lineTo(bottomRight - 8f, tinBottom)
        arcTo(Rect(bottomRight - 16f, tinBottom - 16f, bottomRight, tinBottom), 0f, 90f, false)
        lineTo(topRight, tinTop)
        close()
    }

    // Stainless steel metallic finish
    val steelBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF5A6372),
            Color(0xFF8C98A9),
            Color(0xFFE2E7EE), // Highlight shine
            Color(0xFF8C98A9),
            Color(0xFF454C57)
        ),
        startX = topLeft,
        endX = topRight
    )

    // Base background
    drawPath(shakerPath, steelBrush)

    // Window cut-out in front to show mixed liquid & ice inside
    val windowPath = Path().apply {
        val pad = 12f
        moveTo(topLeft + pad, tinTop + 25f)
        lineTo(bottomLeft + pad + 4f, tinBottom - 16f)
        lineTo(bottomRight - pad - 4f, tinBottom - 16f)
        lineTo(topRight - pad, tinTop + 25f)
        close()
    }
    // Dark interior
    drawPath(windowPath, Color(0xFF131722))

    // Draw Liquid inside window
    clipPath(windowPath) {
        if (fillFraction > 0.01f) {
            val windowH = (tinBottom - tinTop - 45f)
            val liquidTop = tinBottom - 16f - (windowH * fillFraction)

            if (isShaking) {
                // Foaming mixed vortex
                val mixedColor = blendColors(ingredients)
                drawRect(
                    Brush.verticalGradient(listOf(Color(0xEEFFFFFF), mixedColor.copy(alpha = 0.9f))),
                    topLeft = Offset(0f, liquidTop - 15f),
                    size = Size(w, windowH * fillFraction + 25f)
                )
                // Froth bubbles
                for (i in 0..12) {
                    val bx = bottomLeft + 20f + (i * 27) % (tinWidthBottom.toInt())
                    val by = liquidTop + (i * 19) % 50
                    drawCircle(Color.White, radius = 3.5f, center = Offset(bx, by))
                }
            } else {
                // Layered liquid view
                var currentY = tinBottom - 16f
                val totalOz = ingredients.sumOf { it.amountOz.toDouble() }.toFloat().coerceAtLeast(0.01f)
                ingredients.forEach { item ->
                    val layerH = (item.amountOz / totalOz) * (windowH * fillFraction)
                    val layerTop = currentY - layerH
                    drawRect(
                        brush = Brush.verticalGradient(
                            listOf(item.ingredient.color.copy(alpha = 0.9f), item.ingredient.color)
                        ),
                        topLeft = Offset(0f, layerTop),
                        size = Size(w, layerH + 2f)
                    )
                    currentY = layerTop
                }
            }
        }

        // Ice Cubes inside shaker - Fit to size
        if (hasIce) {
            val cubeSize = (tinWidthBottom * 0.36f).coerceIn(24f, 38f)
            // Layer 1 (Base cubes)
            drawShakerIce(cx - cubeSize * 1.05f, tinBottom - cubeSize * 1.2f, cubeSize, if (isShaking) 12f else -4f)
            drawShakerIce(cx + cubeSize * 0.1f, tinBottom - cubeSize * 1.15f, cubeSize, if (isShaking) -15f else 6f)
            // Layer 2 (Stacked mid cubes)
            drawShakerIce(cx - cubeSize * 0.7f, tinBottom - cubeSize * 2.2f, cubeSize * 0.95f, if (isShaking) 20f else 3f)
            drawShakerIce(cx + cubeSize * 0.35f, tinBottom - cubeSize * 2.05f, cubeSize * 0.9f, if (isShaking) -25f else -8f)
            // Layer 3 (Top crown cube)
            drawShakerIce(cx - cubeSize * 0.2f, tinBottom - cubeSize * 3.1f, cubeSize * 0.85f, if (isShaking) 15f else 2f)
        }

        // Barspoon if stirring
        if (isStirring) {
            val spoonX = cx + sin(stirPhase) * 20f
            drawLine(
                color = AmberGold,
                start = Offset(spoonX, tinTop - 25f),
                end = Offset(cx - sin(stirPhase) * 15f, tinBottom - 25f),
                strokeWidth = 5f,
                cap = StrokeCap.Round
            )
        }
    }

    // Frosted condensation droplets on exterior if shaken
    if (shakeProgress > 0.2f) {
        val frostAlpha = (shakeProgress * 0.7f).coerceIn(0.1f, 0.75f)
        drawPath(shakerPath, Color.White.copy(alpha = frostAlpha * 0.25f))
        for (i in 0..15) {
            val dx = bottomLeft + 10f + (i * 31) % (tinWidthBottom.toInt() - 10)
            val dy = tinTop + 30f + (i * 47) % (tinBottom - tinTop - 60f).toInt()
            drawCircle(Color.White.copy(alpha = frostAlpha), radius = 2.5f, center = Offset(dx, dy))
        }
    }

    // Outer rim & metal outlines
    drawPath(shakerPath, Color(0x99FFFFFF), style = Stroke(width = 3f))
    drawPath(windowPath, AmberGold.copy(alpha = 0.7f), style = Stroke(width = 1.5f))

    // Top metal collar rim
    drawRoundRect(
        color = Color(0xFFC0CAD7),
        topLeft = Offset(topLeft - 3f, tinTop - 8f),
        size = Size(tinWidthTop + 6f, 10f),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(3f, 3f)
    )
}

private fun DrawScope.drawShakerIce(x: Float, y: Float, size: Float, tilt: Float) {
    val path = Path().apply {
        addRoundRect(RoundRect(x, y, x + size, y + size, 5f, 5f))
    }
    drawPath(path, IceColor.copy(alpha = 0.75f))
    drawPath(path, Color(0xDDFFFFFF), style = Stroke(width = 1.8f))
    // Crystalline facet glints
    drawLine(Color.White.copy(alpha = 0.8f), Offset(x + 4f, y + 4f), Offset(x + size - 4f, y + size - 4f), strokeWidth = 1.2f)
    drawLine(Color.White.copy(alpha = 0.5f), Offset(x + size * 0.3f, y + 3f), Offset(x + size * 0.7f, y + size - 3f), strokeWidth = 0.8f)
}

private fun blendColors(ingredients: List<MeasuredIngredient>): Color {
    if (ingredients.isEmpty()) return Color(0x33FFFFFF)
    var r = 0f
    var g = 0f
    var b = 0f
    val total = ingredients.sumOf { it.amountOz.toDouble() }.toFloat().coerceAtLeast(0.01f)
    ingredients.forEach {
        val w = it.amountOz / total
        r += it.ingredient.color.red * w
        g += it.ingredient.color.green * w
        b += it.ingredient.color.blue * w
    }
    return Color(r.coerceIn(0f, 1f), g.coerceIn(0f, 1f), b.coerceIn(0f, 1f), 0.9f)
}
