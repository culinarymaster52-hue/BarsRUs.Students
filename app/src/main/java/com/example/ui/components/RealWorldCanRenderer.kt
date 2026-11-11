package com.example.ui.components

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import com.example.model.Ingredient
import com.example.ui.components.RealWorldBottleRenderer.drawStandardBlackLabel

/**
 * Draws an authentic real-world aluminum beer can with metallic chimes,
 * pull-tab lid bevel, cylindrical 3D reflections, and a standardized
 * Black Background label with White Titles and Red ABV % that fits cleanly inside the label.
 */
object RealWorldCanRenderer {

    fun drawBeerCan(
        scope: DrawScope,
        ingredient: Ingredient,
        remainingFraction: Float,
        isPouring: Boolean
    ) = with(scope) {
        val w = size.width
        val h = size.height

        // Realistic Can Geometry
        val canW = w * 0.86f
        val canLeft = (w - canW) / 2f
        val canRight = canLeft + canW
        val topRimW = canW * 0.84f
        val topRimLeft = (w - topRimW) / 2f
        val topRimY = h * 0.04f
        val topChimeH = h * 0.05f
        val bodyTop = topRimY + topChimeH
        val bodyBottom = h * 0.90f
        val bottomChimeH = h * 0.05f
        val bottomRimY = bodyBottom + bottomChimeH
        val bottomRimW = canW * 0.78f
        val bottomRimLeft = (w - bottomRimW) / 2f

        val canPath = Path().apply {
            moveTo(topRimLeft, topRimY)
            lineTo(topRimLeft + topRimW, topRimY)
            lineTo(canRight, bodyTop)
            lineTo(canRight, bodyBottom)
            lineTo(bottomRimLeft + bottomRimW, bottomRimY)
            lineTo(bottomRimLeft, bottomRimY)
            lineTo(canLeft, bodyBottom)
            lineTo(canLeft, bodyTop)
            close()
        }

        // 1. Draw Aluminum Metal Base & Chimes
        val brushedAluminumBrush = Brush.horizontalGradient(
            0.0f to Color(0xFFC0C0C0),
            0.2f to Color(0xFFEBEBEB),
            0.5f to Color(0xFFD4D4D4),
            0.85f to Color(0xFFA8A8A8),
            1.0f to Color(0xFF909090),
            startX = canLeft,
            endX = canRight
        )

        drawPath(canPath, brush = brushedAluminumBrush)

        // Top Rim Chime & Bevel Lid
        drawRoundRect(
            color = Color(0xFFE0E0E0),
            topLeft = Offset(topRimLeft - 1f, topRimY - 3f),
            size = Size(topRimW + 2f, 5f),
            cornerRadius = CornerRadius(2.5f, 2.5f)
        )
        drawOval(
            brush = Brush.radialGradient(
                colors = listOf(Color(0xFFBDBDBD), Color(0xFF757575)),
                center = Offset(w / 2f, topRimY),
                radius = topRimW / 2f
            ),
            topLeft = Offset(topRimLeft + 2f, topRimY - 2f),
            size = Size(topRimW - 4f, 4.5f)
        )
        drawCircle(
            color = Color(0xFFE0E0E0),
            radius = 2.2f,
            center = Offset(w / 2f, topRimY)
        )

        // 2. Draw Can Body & Black Label with White Title + Red ABV %
        val bodyH = bodyBottom - bodyTop
        val labelPath = Path().apply {
            moveTo(canLeft, bodyTop)
            lineTo(canRight, bodyTop)
            lineTo(canRight, bodyBottom)
            lineTo(canLeft, bodyBottom)
            close()
        }

        clipPath(labelPath) {
            // Subtle metallic band at top/bottom of can body with brand accent stripe
            drawRect(
                color = Color(0xFF1A1A1A),
                topLeft = Offset(canLeft, bodyTop),
                size = Size(canW, bodyH)
            )
            // Thin top and bottom brand color trim
            drawRect(
                color = ingredient.color,
                topLeft = Offset(canLeft, bodyTop + 2f),
                size = Size(canW, 4f)
            )
            drawRect(
                color = ingredient.color,
                topLeft = Offset(canLeft, bodyBottom - 6f),
                size = Size(canW, 4f)
            )

            // Black Label in the center of the can with White Title and Red ABV %
            val labelW = canW * 0.92f
            val labelLeft = (w - labelW) / 2f
            val labelH = bodyH * 0.72f
            val labelY = bodyTop + (bodyH - labelH) / 2f

            drawStandardBlackLabel(
                ingredient = ingredient,
                labelLeft = labelLeft,
                labelY = labelY,
                labelW = labelW,
                labelH = labelH
            )

            // Depletion frost/tint overlay if partially consumed
            if (remainingFraction < 0.98f) {
                val emptyH = bodyH * (1f - remainingFraction)
                drawRect(
                    color = Color(0x55000000),
                    topLeft = Offset(canLeft, bodyTop),
                    size = Size(canW, emptyH)
                )
                drawLine(
                    color = Color(0x99FFFFFF),
                    start = Offset(canLeft, bodyTop + emptyH),
                    end = Offset(canRight, bodyTop + emptyH),
                    strokeWidth = 1.5f
                )
            }

            // 3. 3D Cylindrical Specular Highlight
            drawRect(
                brush = Brush.horizontalGradient(
                    0.0f to Color.Transparent,
                    0.20f to Color(0x28FFFFFF),
                    0.35f to Color(0x40FFFFFF),
                    0.50f to Color.Transparent,
                    startX = canLeft,
                    endX = canLeft + canW * 0.45f
                ),
                topLeft = Offset(canLeft, bodyTop),
                size = Size(canW, bodyH)
            )
        }

        // Bottom Chime & Rim Bevel
        drawRoundRect(
            color = Color(0xFFB0B0B0),
            topLeft = Offset(bottomRimLeft, bottomRimY - 3f),
            size = Size(bottomRimW, 5f),
            cornerRadius = CornerRadius(2.5f, 2.5f)
        )

        drawPath(canPath, color = Color(0x44000000), style = Stroke(width = 1.2f))
    }
}
