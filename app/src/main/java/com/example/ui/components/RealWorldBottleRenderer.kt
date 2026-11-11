package com.example.ui.components

import android.graphics.Paint as AndroidPaint
import android.graphics.Typeface as AndroidTypeface
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import com.example.model.Ingredient
import com.example.model.IngredientType
import java.util.Locale

/**
 * Draws authentic real-world bottles for Wine, Beer, Spirits, Mixers, and Bitters.
 * Every bottle features a real-world silhouette and a crisp black background label
 * with white titles and red ABV % that automatically scales so everything fits inside the label.
 */
object RealWorldBottleRenderer {

    private val LabelBlack = Color(0xFF0A0A0A)
    private val LabelBorder = Color(0xFF333333)
    private val TitleWhite = Color(0xFFFFFFFF)
    private val AbvRed = Color(0xFFFF2A2A)

    fun drawBottle(
        scope: DrawScope,
        ingredient: Ingredient,
        remainingFraction: Float,
        isPouring: Boolean
    ) = with(scope) {
        when (ingredient.type) {
            IngredientType.WINE -> drawWineBottle(ingredient, remainingFraction)
            IngredientType.BEER -> drawBeerBottle(ingredient, remainingFraction)
            else -> drawSpiritBottle(ingredient, remainingFraction)
        }
    }

    // ==========================================
    // 1. REAL WORLD WINE BOTTLES
    // ==========================================
    private fun DrawScope.drawWineBottle(ingredient: Ingredient, remainingFraction: Float) {
        val w = size.width
        val h = size.height
        val id = ingredient.id.lowercase()

        val isSparkling = id.contains("champagne") || id.contains("prosecco")
        val isWhiteOrRose = id.contains("rose") || id.contains("blanc") || id.contains("chardonnay") ||
                id.contains("pinot_gris") || id.contains("pinot_blanc") || id.contains("riesling") ||
                id.contains("viognier") || id.contains("albarino") || id.contains("vermentino")

        val neckW = w * 0.22f
        val neckLeft = (w - neckW) / 2f
        val neckTop = h * 0.04f
        val neckBottom = h * 0.30f
        val bodyW = if (isSparkling) w * 0.80f else w * 0.76f
        val bodyLeft = (w - bodyW) / 2f
        val bodyTop = neckBottom
        val bodyBottom = h * 0.94f

        val bottlePath = Path().apply {
            moveTo(neckLeft, neckTop)
            lineTo(neckLeft + neckW, neckTop)
            lineTo(neckLeft + neckW, neckBottom)
            if (isSparkling || id.contains("pinot_noir") || id.contains("chardonnay") || id.contains("syrah")) {
                cubicTo(neckLeft + neckW, neckBottom + 18f, bodyLeft + bodyW, bodyTop + 5f, bodyLeft + bodyW, bodyTop + 22f)
            } else {
                cubicTo(neckLeft + neckW, neckBottom + 8f, bodyLeft + bodyW, bodyTop + 5f, bodyLeft + bodyW, bodyTop + 14f)
            }
            lineTo(bodyLeft + bodyW, bodyBottom - 8f)
            arcTo(Rect(bodyLeft + bodyW - 16f, bodyBottom - 16f, bodyLeft + bodyW, bodyBottom), 0f, 90f, false)
            lineTo(w / 2f + 8f, bodyBottom)
            lineTo(w / 2f, bodyBottom - 5f)
            lineTo(w / 2f - 8f, bodyBottom)
            lineTo(bodyLeft + 8f, bodyBottom)
            arcTo(Rect(bodyLeft, bodyBottom - 16f, bodyLeft + 16f, bodyBottom), 90f, 90f, false)
            lineTo(bodyLeft, bodyTop + 14f)
            if (isSparkling || id.contains("pinot_noir") || id.contains("chardonnay") || id.contains("syrah")) {
                cubicTo(bodyLeft, bodyTop + 5f, neckLeft, neckBottom + 18f, neckLeft, neckBottom)
            } else {
                cubicTo(bodyLeft, bodyTop + 5f, neckLeft, neckBottom + 8f, neckLeft, neckBottom)
            }
            close()
        }

        val glassBrush = if (isWhiteOrRose) {
            Brush.horizontalGradient(
                listOf(Color(0x33FFFFFF), Color(0x18FFFFFF), Color(0x33B2DFDB)),
                startX = bodyLeft, endX = bodyLeft + bodyW
            )
        } else {
            Brush.horizontalGradient(
                listOf(Color(0xE61B3018), Color(0xCC2E4E28), Color(0xF00D1B0B)),
                startX = bodyLeft, endX = bodyLeft + bodyW
            )
        }

        drawPath(bottlePath, glassBrush)

        val liquidH = (bodyBottom - bodyTop) * remainingFraction
        val liquidTop = bodyBottom - liquidH
        clipPath(bottlePath) {
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(ingredient.color.copy(alpha = 0.85f), ingredient.color)
                ),
                topLeft = Offset(bodyLeft, liquidTop),
                size = Size(bodyW, liquidH)
            )

            // Standardized Black Label with White Title and Red ABV %
            val labelW = bodyW * 0.90f
            val labelLeft = (w - labelW) / 2f
            val labelH = (bodyBottom - bodyTop) * 0.48f
            val labelY = bodyTop + (bodyBottom - bodyTop) * 0.25f

            drawStandardBlackLabel(
                ingredient = ingredient,
                labelLeft = labelLeft,
                labelY = labelY,
                labelW = labelW,
                labelH = labelH
            )
        }

        // Foil Neck Capsule
        val capsuleColor = if (isSparkling) Color(0xFFD4AF37) else if (isWhiteOrRose) Color(0xFFC0A060) else Color(0xFF7A1C28)
        val capsuleH = (neckBottom - neckTop) * 0.80f
        drawRect(
            color = capsuleColor,
            topLeft = Offset(neckLeft - 1f, neckTop - 2f),
            size = Size(neckW + 2f, capsuleH)
        )
        drawLine(Color(0x66FFFFFF), Offset(neckLeft, neckTop + capsuleH * 0.4f), Offset(neckLeft + neckW, neckTop + capsuleH * 0.4f), 1.5f)
        drawLine(Color(0x66FFFFFF), Offset(neckLeft, neckTop + capsuleH * 0.8f), Offset(neckLeft + neckW, neckTop + capsuleH * 0.8f), 1.5f)

        drawLine(Color(0x4DFFFFFF), Offset(bodyLeft + 3f, bodyTop + 20f), Offset(bodyLeft + 3f, bodyBottom - 12f), 2f)
        drawPath(bottlePath, Color(0x33FFFFFF), style = Stroke(width = 1.5f))
    }

    // ==========================================
    // 2. REAL WORLD BEER BOTTLES
    // ==========================================
    private fun DrawScope.drawBeerBottle(ingredient: Ingredient, remainingFraction: Float) {
        val w = size.width
        val h = size.height
        val id = ingredient.id.lowercase()

        val isGrolschSwingTop = id.contains("grolsch")
        val isChimayCorked = id.contains("chimay") && id.contains("reserve")
        val isRedStripeStubby = id.contains("red_stripe")
        val isCoronaClear = id.contains("corona")
        val isGreenGlass = id.contains("heineken") || id.contains("peroni") || id.contains("grolsch") || id.contains("stella")

        val neckW = if (isRedStripeStubby) w * 0.32f else w * 0.24f
        val neckLeft = (w - neckW) / 2f
        val neckTop = h * 0.04f
        val neckBottom = if (isRedStripeStubby) h * 0.26f else h * 0.32f

        val bodyW = if (isRedStripeStubby) w * 0.84f else if (isChimayCorked) w * 0.80f else w * 0.76f
        val bodyLeft = (w - bodyW) / 2f
        val bodyTop = neckBottom
        val bodyBottom = h * 0.94f

        val bottlePath = Path().apply {
            moveTo(neckLeft, neckTop)
            lineTo(neckLeft + neckW, neckTop)
            lineTo(neckLeft + neckW, neckBottom)
            cubicTo(neckLeft + neckW, neckBottom + 12f, bodyLeft + bodyW, bodyTop + 4f, bodyLeft + bodyW, bodyTop + 18f)
            lineTo(bodyLeft + bodyW, bodyBottom - 6f)
            arcTo(Rect(bodyLeft + bodyW - 12f, bodyBottom - 12f, bodyLeft + bodyW, bodyBottom), 0f, 90f, false)
            lineTo(bodyLeft + 6f, bodyBottom)
            arcTo(Rect(bodyLeft, bodyBottom - 12f, bodyLeft + 12f, bodyBottom), 90f, 90f, false)
            lineTo(bodyLeft, bodyTop + 18f)
            cubicTo(bodyLeft, bodyTop + 4f, neckLeft, neckBottom + 12f, neckLeft, neckBottom)
            close()
        }

        val glassColor = when {
            isCoronaClear -> Brush.horizontalGradient(listOf(Color(0x28FFFFFF), Color(0x14FFFFFF), Color(0x33FFFFFF)))
            isGreenGlass -> Brush.horizontalGradient(listOf(Color(0xE60D381E), Color(0xCC1A5D33), Color(0xF0082413)))
            else -> Brush.horizontalGradient(listOf(Color(0xE64E260A), Color(0xCC783D14), Color(0xF03B1C06)))
        }

        drawPath(bottlePath, glassColor)

        val liquidH = (bodyBottom - bodyTop) * remainingFraction
        val liquidTop = bodyBottom - liquidH
        clipPath(bottlePath) {
            drawRect(
                brush = Brush.verticalGradient(listOf(ingredient.color.copy(alpha = 0.88f), ingredient.color)),
                topLeft = Offset(bodyLeft, liquidTop),
                size = Size(bodyW, liquidH)
            )

            // Standardized Black Label with White Title and Red ABV %
            val labelW = bodyW * 0.90f
            val labelLeft = (w - labelW) / 2f
            val labelH = (bodyBottom - bodyTop) * 0.48f
            val labelY = bodyTop + (bodyBottom - bodyTop) * 0.25f

            drawStandardBlackLabel(
                ingredient = ingredient,
                labelLeft = labelLeft,
                labelY = labelY,
                labelW = labelW,
                labelH = labelH
            )
        }

        when {
            isGrolschSwingTop -> {
                drawRoundRect(Color(0xFFEEEEEE), topLeft = Offset(neckLeft - 2f, neckTop - 6f), size = Size(neckW + 4f, 8f), cornerRadius = CornerRadius(2f, 2f))
                drawCircle(Color(0xFFE53935), radius = 2f, center = Offset(w / 2f, neckTop - 2f))
                drawLine(Color(0xFFB0BEC5), Offset(neckLeft - 3f, neckTop + 2f), Offset(neckLeft + neckW + 3f, neckTop + 2f), 2f)
            }
            isChimayCorked -> {
                drawRoundRect(Color(0xFFD7CCC8), topLeft = Offset(neckLeft, neckTop - 7f), size = Size(neckW, 9f), cornerRadius = CornerRadius(3f, 3f))
                drawLine(Color(0xFFD4AF37), Offset(neckLeft, neckTop - 3f), Offset(neckLeft + neckW, neckTop - 3f), 1.5f)
            }
            else -> {
                val capColor = if (isCoronaClear) Color(0xFF1B365D) else Color(0xFFD4AF37)
                drawRoundRect(capColor, topLeft = Offset(neckLeft - 2f, neckTop - 4f), size = Size(neckW + 4f, 6f), cornerRadius = CornerRadius(1.5f, 1.5f))
            }
        }

        drawLine(Color(0x55FFFFFF), Offset(bodyLeft + 3f, bodyTop + 14f), Offset(bodyLeft + 3f, bodyBottom - 8f), 2f)
    }

    // ==========================================
    // 3. REAL WORLD SPIRIT, MIXER & BITTERS BOTTLES
    // ==========================================
    private fun DrawScope.drawSpiritBottle(ingredient: Ingredient, remainingFraction: Float) {
        val w = size.width
        val h = size.height
        val id = ingredient.id.lowercase()

        val isBitters = ingredient.type == IngredientType.BITTERS
        val isWhiskey = id.contains("bourbon") || id.contains("rye") || id.contains("jack_daniels") || id.contains("jameson") || id.contains("whiskey")
        val isVodka = id.contains("vodka") || id.contains("absolut") || id.contains("grey_goose") || id.contains("smirnoff")
        val isTequila = id.contains("tequila") || id.contains("patron") || id.contains("mezcal")
        val isGin = id.contains("gin") || id.contains("bombay") || id.contains("tanqueray")

        val neckW = when {
            isTequila -> w * 0.32f
            isBitters -> w * 0.22f
            else -> w * 0.26f
        }
        val neckLeft = (w - neckW) / 2f
        val neckTop = h * 0.05f
        val neckBottom = h * 0.28f

        val bodyW = when {
            isWhiskey -> w * 0.80f
            isTequila -> w * 0.82f
            isVodka -> w * 0.74f
            else -> w * 0.78f
        }
        val bodyLeft = (w - bodyW) / 2f
        val bodyTop = neckBottom
        val bodyBottom = h * 0.94f

        val bottlePath = Path().apply {
            moveTo(neckLeft, neckTop)
            lineTo(neckLeft + neckW, neckTop)
            lineTo(neckLeft + neckW, neckBottom)
            if (isWhiskey) {
                lineTo(bodyLeft + bodyW, bodyTop + 4f)
            } else {
                cubicTo(neckLeft + neckW, neckBottom + 10f, bodyLeft + bodyW, bodyTop + 5f, bodyLeft + bodyW, bodyTop + 16f)
            }
            lineTo(bodyLeft + bodyW, bodyBottom - 6f)
            arcTo(Rect(bodyLeft + bodyW - 12f, bodyBottom - 12f, bodyLeft + bodyW, bodyBottom), 0f, 90f, false)
            lineTo(bodyLeft + 6f, bodyBottom)
            arcTo(Rect(bodyLeft, bodyBottom - 12f, bodyLeft + 12f, bodyBottom), 90f, 90f, false)
            if (isWhiskey) {
                lineTo(bodyLeft, bodyTop + 4f)
            } else {
                lineTo(bodyLeft, bodyTop + 16f)
                cubicTo(bodyLeft, bodyTop + 5f, neckLeft, neckBottom + 10f, neckLeft, neckBottom)
            }
            close()
        }

        val glassBrush = if (isGin && id.contains("bombay")) {
            Brush.horizontalGradient(listOf(Color(0xD90072CE), Color(0x9929B6F6), Color(0xD90277BD)))
        } else if (isVodka) {
            Brush.horizontalGradient(listOf(Color(0x33FFFFFF), Color(0x20FFFFFF), Color(0x33E0F2F1)))
        } else {
            Brush.horizontalGradient(listOf(Color(0x33FFFFFF), Color(0x18FFFFFF), Color(0x2AFFFFFF)))
        }

        drawPath(bottlePath, glassBrush)

        val liquidH = (bodyBottom - bodyTop) * remainingFraction
        val liquidTop = bodyBottom - liquidH
        clipPath(bottlePath) {
            drawRect(
                brush = Brush.verticalGradient(listOf(ingredient.color.copy(alpha = 0.88f), ingredient.color)),
                topLeft = Offset(bodyLeft, liquidTop),
                size = Size(bodyW, liquidH)
            )

            // Standardized Black Label with White Title and Red ABV %
            val labelW = bodyW * 0.90f
            val labelLeft = (w - labelW) / 2f
            val labelH = (bodyBottom - bodyTop) * 0.50f
            val labelY = bodyTop + (bodyBottom - bodyTop) * 0.24f

            drawStandardBlackLabel(
                ingredient = ingredient,
                labelLeft = labelLeft,
                labelY = labelY,
                labelW = labelW,
                labelH = labelH
            )
        }

        when {
            isBitters -> {
                drawRoundRect(Color(0xFFFACC15), topLeft = Offset(neckLeft - 1f, neckTop - 5f), size = Size(neckW + 2f, 7f), cornerRadius = CornerRadius(1.5f, 1.5f))
            }
            isTequila -> {
                drawCircle(Color(0xFFB45309), radius = 6.5f, center = Offset(w / 2f, neckTop - 4f))
                drawRoundRect(Color(0xFF16A34A), topLeft = Offset(neckLeft, neckTop + 2f), size = Size(neckW, 4f), cornerRadius = CornerRadius(1f, 1f))
            }
            isWhiskey -> {
                drawRoundRect(Color(0xFF78350F), topLeft = Offset(neckLeft - 1f, neckTop - 5f), size = Size(neckW + 2f, 7f), cornerRadius = CornerRadius(2f, 2f))
            }
            else -> {
                drawRoundRect(Color(0xFFD4AF37), topLeft = Offset(neckLeft - 1f, neckTop - 4f), size = Size(neckW + 2f, 6f), cornerRadius = CornerRadius(1.5f, 1.5f))
            }
        }

        drawLine(Color(0x66FFFFFF), Offset(bodyLeft + 3f, bodyTop + 14f), Offset(bodyLeft + 3f, bodyBottom - 8f), 2f)
    }

    /**
     * Draws a solid Black Background label with crisp White Titles and Red ABV %
     * and dynamically scales/wraps text so everything fits cleanly inside the label bounds.
     */
    fun DrawScope.drawStandardBlackLabel(
        ingredient: Ingredient,
        labelLeft: Float,
        labelY: Float,
        labelW: Float,
        labelH: Float
    ) {
        // 1. Solid Black Background
        drawRoundRect(
            color = LabelBlack,
            topLeft = Offset(labelLeft, labelY),
            size = Size(labelW, labelH),
            cornerRadius = CornerRadius(3f, 3f)
        )
        // Subtle inner border
        drawRoundRect(
            color = LabelBorder,
            topLeft = Offset(labelLeft + 1f, labelY + 1f),
            size = Size(labelW - 2f, labelH - 2f),
            cornerRadius = CornerRadius(2.5f, 2.5f),
            style = Stroke(width = 1f)
        )

        val cx = labelLeft + labelW / 2f
        val maxTextW = (labelW - 6f).coerceAtLeast(16f)

        // Split ingredient name into 1 or 2 balanced lines so the full title fits cleanly
        val cleanName = ingredient.name
            .replace(Regex("\\s*\\([^)]*\\)"), "") // strip parenthetical notes so title fits cleanly
            .trim()
        val words = cleanName.split(" ").filter { it.isNotEmpty() }
        val (line1, line2) = when {
            words.size <= 1 -> words.joinToString("") to ""
            words.size == 2 -> words[0] to words[1]
            words.size == 3 -> "${words[0]} ${words[1]}" to words[2]
            else -> {
                val mid = (words.size + 1) / 2
                words.take(mid).joinToString(" ") to words.drop(mid).joinToString(" ")
            }
        }

        // Format ABV string in red
        val abvStr = if (ingredient.abv > 0f) {
            val formatted = if (ingredient.abv % 1.0f == 0f) {
                "${ingredient.abv.toInt()}% ABV"
            } else {
                "${String.format(Locale.US, "%.1f", ingredient.abv)}% ABV"
            }
            formatted
        } else {
            "0% ABV"
        }

        if (line2.isEmpty()) {
            // Single-line White Title + Red ABV %
            drawFittedText(
                text = line1.uppercase(Locale.US),
                cx = cx,
                cy = labelY + labelH * 0.44f,
                maxWidthPx = maxTextW,
                initialTextSizePx = labelH * 0.30f,
                color = TitleWhite
            )
            drawFittedText(
                text = abvStr,
                cx = cx,
                cy = labelY + labelH * 0.80f,
                maxWidthPx = maxTextW,
                initialTextSizePx = labelH * 0.25f,
                color = AbvRed
            )
        } else {
            // Two-line White Title + Red ABV %
            drawFittedText(
                text = line1.uppercase(Locale.US),
                cx = cx,
                cy = labelY + labelH * 0.31f,
                maxWidthPx = maxTextW,
                initialTextSizePx = labelH * 0.24f,
                color = TitleWhite
            )
            drawFittedText(
                text = line2.uppercase(Locale.US),
                cx = cx,
                cy = labelY + labelH * 0.56f,
                maxWidthPx = maxTextW,
                initialTextSizePx = labelH * 0.24f,
                color = TitleWhite
            )
            drawFittedText(
                text = abvStr,
                cx = cx,
                cy = labelY + labelH * 0.84f,
                maxWidthPx = maxTextW,
                initialTextSizePx = labelH * 0.23f,
                color = AbvRed
            )
        }
    }

    private fun DrawScope.drawFittedText(
        text: String,
        cx: Float,
        cy: Float,
        maxWidthPx: Float,
        initialTextSizePx: Float,
        color: Color
    ) {
        drawIntoCanvas { canvas ->
            val paint = AndroidPaint().apply {
                this.color = color.toArgb()
                this.isAntiAlias = true
                this.typeface = AndroidTypeface.DEFAULT_BOLD
                this.textAlign = AndroidPaint.Align.CENTER
                this.textSize = initialTextSizePx.coerceAtLeast(8f)
            }
            // Dynamically shrink text size until it strictly fits within maxWidthPx
            var measured = paint.measureText(text)
            while (measured > maxWidthPx && paint.textSize > 6f) {
                paint.textSize -= 0.75f
                measured = paint.measureText(text)
            }
            canvas.nativeCanvas.drawText(text, cx, cy, paint)
        }
    }
}
