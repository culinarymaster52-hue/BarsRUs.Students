package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.model.GlassType
import com.example.model.MeasuredIngredient
import com.example.ui.theme.CopperAccent
import kotlin.math.sin

@Composable
fun GlasswareCanvas(
    glassType: GlassType,
    pouredIngredients: List<MeasuredIngredient>,
    hasIce: Boolean,
    garnishName: String,
    isSmoked: Boolean = false,
    rimDressing: String? = null,
    modifier: Modifier = Modifier
) {
    // Animation for bubbling / liquid settling
    val infiniteTransition = rememberInfiniteTransition(label = "liquid_wobble")
    val waveOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 6.28f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "wave"
    )

    // Calculate blended color and total volume
    val totalOz = pouredIngredients.sumOf { it.amountOz.toDouble() }.toFloat()
    val fillFraction = (totalOz / glassType.capacityOz).coerceIn(0f, 1f)

    val blendedColor = remember(pouredIngredients) {
        if (pouredIngredients.isEmpty()) {
            Color.Transparent
        } else {
            var r = 0f
            var g = 0f
            var b = 0f
            var a = 0f
            val sum = pouredIngredients.sumOf { it.amountOz.toDouble() }.toFloat().coerceAtLeast(0.01f)
            pouredIngredients.forEach { item ->
                val weight = item.amountOz / sum
                r += item.ingredient.color.red * weight
                g += item.ingredient.color.green * weight
                b += item.ingredient.color.blue * weight
                a += item.ingredient.color.alpha * weight
            }
            Color(r.coerceIn(0f, 1f), g.coerceIn(0f, 1f), b.coerceIn(0f, 1f), a.coerceIn(0.2f, 0.95f))
        }
    }

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        when (glassType) {
            GlassType.HIGHBALL -> drawHighball(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.COUPE -> drawCoupe(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.MARTINI -> drawMartini(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.ROCKS -> drawRocks(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.MARGARITA -> drawMargarita(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.COPPER_MUG -> drawCopperMug(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.HURRICANE -> drawHurricane(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.SHOT -> drawShot(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.WINE -> drawWineGlass(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
            GlassType.BEER_PINT -> drawPint(w, h, fillFraction, blendedColor, hasIce, garnishName, waveOffset)
        }

        if (rimDressing != null) {
            drawGlassRimDressing(w, h, glassType, rimDressing)
        }
        if (isSmoked) {
            drawSmokeEffect(w, h, glassType, waveOffset)
        }
    }
}

// 1. Highball / Collins Glass
private fun DrawScope.drawHighball(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val glassLeft = w * 0.28f
    val glassRight = w * 0.72f
    val glassTop = h * 0.12f
    val glassBottom = h * 0.88f
    val glassWidth = glassRight - glassLeft
    val glassHeight = glassBottom - glassTop

    val glassPath = Path().apply {
        moveTo(glassLeft, glassTop)
        lineTo(glassLeft, glassBottom - 10f)
        arcTo(Rect(glassLeft, glassBottom - 20f, glassLeft + 20f, glassBottom), 90f, 90f, false)
        lineTo(glassRight - 10f, glassBottom)
        arcTo(Rect(glassRight - 20f, glassBottom - 20f, glassRight, glassBottom), 0f, 90f, false)
        lineTo(glassRight, glassTop)
    }

    // Heavy base
    drawRect(
        color = Color(0x33FFFFFF),
        topLeft = Offset(glassLeft + 3f, glassBottom - 18f),
        size = Size(glassWidth - 6f, 15f)
    )

    // Liquid
    if (fill > 0.01f) {
        val liquidTop = glassBottom - 18f - ((glassHeight - 35f) * fill)
        val liquidPath = Path().apply {
            moveTo(glassLeft + 4f, glassBottom - 18f)
            lineTo(glassLeft + 4f, liquidTop)
            val steps = 15
            for (i in 0..steps) {
                val x = (glassLeft + 4f) + (glassWidth - 8f) * (i.toFloat() / steps)
                val y = liquidTop + (sin(wave + i * 0.5f) * 2.5f)
                lineTo(x, y)
            }
            lineTo(glassRight - 4f, glassBottom - 18f)
            close()
        }
        drawPath(liquidPath, Brush.verticalGradient(listOf(color.copy(alpha = 0.85f), color)))

        // Effervescence bubbles
        for (i in 1..8) {
            val bx = glassLeft + 15f + ((i * 37) % (glassWidth.toInt() - 30))
            val by = glassBottom - 25f - ((i * 43 + (wave * 20).toInt()) % (glassHeight * fill).toInt().coerceAtLeast(10))
            drawCircle(Color(0x77FFFFFF), radius = 2.5f, center = Offset(bx, by))
        }
    }

    // Ice Cubes
    if (ice && fill > 0.05f) {
        drawIceCube(glassLeft + 12f, glassBottom - 55f, 32f)
        drawIceCube(glassLeft + 20f, glassBottom - 95f, 30f)
        drawIceCube(glassLeft + 14f, glassBottom - 135f, 28f)
    }

    // Glass outline & rim
    drawPath(glassPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f, cap = StrokeCap.Round))
    drawLine(Color(0xAAFFFFFF), Offset(glassLeft - 2f, glassTop), Offset(glassRight + 2f, glassTop), strokeWidth = 3f)

    drawGarnish(w, glassLeft, glassRight, glassTop, garnish)
}

// 2. Coupe Glass
private fun DrawScope.drawCoupe(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val cx = w * 0.5f
    val bowlLeft = w * 0.16f
    val bowlRight = w * 0.84f
    val bowlTop = h * 0.18f
    val bowlBottom = h * 0.52f
    val stemBottom = h * 0.82f
    val footWidth = w * 0.44f

    // Bowl Path
    val bowlPath = Path().apply {
        moveTo(bowlLeft, bowlTop)
        cubicTo(bowlLeft, bowlBottom, bowlRight, bowlBottom, bowlRight, bowlTop)
    }

    // Liquid inside bowl
    if (fill > 0.01f) {
        val bowlDepth = bowlBottom - bowlTop
        val liquidTop = bowlBottom - (bowlDepth * fill)
        val liquidPath = Path().apply {
            moveTo(cx, bowlBottom - 3f)
            val leftX = bowlLeft + (1f - fill) * (cx - bowlLeft) * 0.8f
            val rightX = bowlRight - (1f - fill) * (bowlRight - cx) * 0.8f
            lineTo(leftX, liquidTop)
            lineTo(rightX, liquidTop)
            cubicTo(rightX, bowlBottom - 2f, leftX, bowlBottom - 2f, leftX, liquidTop)
            close()
        }
        drawPath(liquidPath, Brush.radialGradient(listOf(color.copy(alpha = 0.95f), color), center = Offset(cx, bowlBottom - 10f)))
    }

    // Stem and Foot
    drawLine(Color(0x88FFFFFF), Offset(cx, bowlBottom), Offset(cx, stemBottom), strokeWidth = 5f, cap = StrokeCap.Round)
    drawLine(Color(0x99FFFFFF), Offset(cx - footWidth / 2, stemBottom + 4f), Offset(cx + footWidth / 2, stemBottom + 4f), strokeWidth = 4f, cap = StrokeCap.Round)

    // Bowl rim and outline
    drawPath(bowlPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))
    drawOval(Color(0x66FFFFFF), topLeft = Offset(bowlLeft, bowlTop - 4f), size = Size(bowlRight - bowlLeft, 8f), style = Stroke(width = 2f))

    drawGarnish(w, bowlLeft, bowlRight, bowlTop, garnish)
}

// 3. Martini Glass
private fun DrawScope.drawMartini(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val cx = w * 0.5f
    val rimLeft = w * 0.14f
    val rimRight = w * 0.86f
    val rimTop = h * 0.16f
    val vertexY = h * 0.50f
    val stemBottom = h * 0.84f
    val footWidth = w * 0.42f

    // Liquid in inverted cone
    if (fill > 0.01f) {
        val liquidTop = vertexY - ((vertexY - rimTop) * fill)
        val leftX = vertexY.let { cx - (cx - rimLeft) * fill }
        val rightX = vertexY.let { cx + (rimRight - cx) * fill }
        val coneLiquid = Path().apply {
            moveTo(cx, vertexY - 2f)
            lineTo(leftX, liquidTop)
            lineTo(rightX, liquidTop)
            close()
        }
        drawPath(coneLiquid, Brush.verticalGradient(listOf(color.copy(alpha = 0.9f), color)))
    }

    // Cone V lines
    drawLine(Color(0x88FFFFFF), Offset(rimLeft, rimTop), Offset(cx, vertexY), strokeWidth = 3.5f)
    drawLine(Color(0x88FFFFFF), Offset(rimRight, rimTop), Offset(cx, vertexY), strokeWidth = 3.5f)

    // Stem and base
    drawLine(Color(0x88FFFFFF), Offset(cx, vertexY), Offset(cx, stemBottom), strokeWidth = 4.5f)
    drawLine(Color(0x99FFFFFF), Offset(cx - footWidth / 2, stemBottom + 3f), Offset(cx + footWidth / 2, stemBottom + 3f), strokeWidth = 4f, cap = StrokeCap.Round)

    // Rim
    drawLine(Color(0xAAFFFFFF), Offset(rimLeft - 2f, rimTop), Offset(rimRight + 2f, rimTop), strokeWidth = 3f)

    drawGarnish(w, rimLeft, rimRight, rimTop, garnish)
}

// 4. Old Fashioned / Rocks Glass
private fun DrawScope.drawRocks(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val glassLeft = w * 0.22f
    val glassRight = w * 0.78f
    val glassTop = h * 0.30f
    val glassBottom = h * 0.86f
    val glassWidth = glassRight - glassLeft
    val glassHeight = glassBottom - glassTop

    val glassPath = Path().apply {
        moveTo(glassLeft, glassTop)
        lineTo(glassLeft + 4f, glassBottom - 12f)
        arcTo(Rect(glassLeft + 4f, glassBottom - 24f, glassLeft + 24f, glassBottom), 90f, 90f, false)
        lineTo(glassRight - 14f, glassBottom)
        arcTo(Rect(glassRight - 24f, glassBottom - 24f, glassRight - 4f, glassBottom), 0f, 90f, false)
        lineTo(glassRight, glassTop)
    }

    // Solid heavy crystal base
    drawRect(
        color = Color(0x44FFFFFF),
        topLeft = Offset(glassLeft + 6f, glassBottom - 22f),
        size = Size(glassWidth - 12f, 20f)
    )

    // Liquid
    if (fill > 0.01f) {
        val liquidTop = glassBottom - 22f - ((glassHeight - 32f) * fill)
        val liquidPath = Path().apply {
            moveTo(glassLeft + 6f, glassBottom - 22f)
            lineTo(glassLeft + 6f, liquidTop)
            lineTo(glassRight - 6f, liquidTop)
            lineTo(glassRight - 6f, glassBottom - 22f)
            close()
        }
        drawPath(liquidPath, Brush.verticalGradient(listOf(color.copy(alpha = 0.9f), color)))
    }

    // Large King Cube Ice
    if (ice) {
        drawIceCube(w * 0.5f - 24f, glassBottom - 72f, 48f)
    }

    // Glass outline
    drawPath(glassPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))
    drawLine(Color(0xAAFFFFFF), Offset(glassLeft - 2f, glassTop), Offset(glassRight + 2f, glassTop), strokeWidth = 3f)

    drawGarnish(w, glassLeft, glassRight, glassTop, garnish)
}

// 5. Margarita Glass
private fun DrawScope.drawMargarita(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val cx = w * 0.5f
    val rimLeft = w * 0.12f
    val rimRight = w * 0.88f
    val rimTop = h * 0.18f
    val stepY = h * 0.32f
    val stepLeft = w * 0.30f
    val stepRight = w * 0.70f
    val bowlBottom = h * 0.48f
    val stemBottom = h * 0.82f
    val footWidth = w * 0.42f

    // Stepped Bowl
    val bowlPath = Path().apply {
        moveTo(rimLeft, rimTop)
        lineTo(stepLeft, stepY)
        cubicTo(stepLeft, bowlBottom, stepRight, bowlBottom, stepRight, stepY)
        lineTo(rimRight, rimTop)
    }

    if (fill > 0.01f) {
        val liquidTop = bowlBottom - ((bowlBottom - rimTop) * fill)
        drawPath(bowlPath, Brush.verticalGradient(listOf(color.copy(alpha = 0.9f), color)))
    }

    // Salt Rim
    for (i in 0..18) {
        val x = rimLeft + (rimRight - rimLeft) * (i / 18f)
        drawCircle(Color.White, radius = 2f, center = Offset(x, rimTop - 2f + (i % 3) * 1.5f))
    }

    drawLine(Color(0x88FFFFFF), Offset(cx, bowlBottom), Offset(cx, stemBottom), strokeWidth = 4.5f)
    drawLine(Color(0x99FFFFFF), Offset(cx - footWidth / 2, stemBottom + 3f), Offset(cx + footWidth / 2, stemBottom + 3f), strokeWidth = 4f, cap = StrokeCap.Round)
    drawPath(bowlPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))

    drawGarnish(w, rimLeft, rimRight, rimTop, garnish)
}

// 6. Copper Mug (Moscow Mule)
private fun DrawScope.drawCopperMug(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val mugLeft = w * 0.26f
    val mugRight = w * 0.74f
    val mugTop = h * 0.28f
    val mugBottom = h * 0.82f
    val mugWidth = mugRight - mugLeft

    // Copper Handle
    val handlePath = Path().apply {
        moveTo(mugRight - 4f, mugTop + 20f)
        cubicTo(w * 0.96f, mugTop + 20f, w * 0.96f, mugBottom - 20f, mugRight - 4f, mugBottom - 20f)
    }
    drawPath(handlePath, CopperAccent, style = Stroke(width = 8f, cap = StrokeCap.Round))

    // Copper body
    val bodyRect = Rect(mugLeft, mugTop, mugRight, mugBottom)
    drawRoundRect(
        Brush.horizontalGradient(listOf(Color(0xFFB55D2C), CopperAccent, Color(0xFFF39D6D), CopperAccent, Color(0xFF8B3F18))),
        topLeft = Offset(mugLeft, mugTop),
        size = Size(mugWidth, mugBottom - mugTop),
        cornerRadius = androidx.compose.ui.geometry.CornerRadius(12f, 12f)
    )

    // Inner rim & liquid view from top
    drawOval(Color(0xFF6B2F10), topLeft = Offset(mugLeft, mugTop - 8f), size = Size(mugWidth, 16f))
    if (fill > 0.01f) {
        drawOval(color.copy(alpha = 0.9f), topLeft = Offset(mugLeft + 4f, mugTop - 5f), size = Size(mugWidth - 8f, 12f))
    }

    // Crushed ice on top
    if (ice) {
        for (i in 0..6) {
            drawCircle(Color(0xCCFFFFFF), radius = 6f, center = Offset(mugLeft + 18f + i * 11f, mugTop - 2f))
        }
    }

    drawGarnish(w, mugLeft, mugRight, mugTop, garnish)
}

// 7. Hurricane Glass
private fun DrawScope.drawHurricane(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val cx = w * 0.5f
    val rimLeft = w * 0.26f
    val rimRight = w * 0.74f
    val rimTop = h * 0.14f
    val waistLeft = w * 0.36f
    val waistRight = w * 0.64f
    val waistY = h * 0.38f
    val hipLeft = w * 0.22f
    val hipRight = w * 0.78f
    val hipY = h * 0.58f
    val baseBottom = h * 0.76f

    val glassPath = Path().apply {
        moveTo(rimLeft, rimTop)
        cubicTo(waistLeft, waistY, hipLeft, hipY, cx, baseBottom)
        cubicTo(hipRight, hipY, waistRight, waistY, rimRight, rimTop)
    }

    if (fill > 0.01f) {
        drawPath(glassPath, Brush.verticalGradient(listOf(color.copy(alpha = 0.85f), color)))
    }

    // Foot
    drawLine(Color(0x88FFFFFF), Offset(cx, baseBottom), Offset(cx, h * 0.84f), strokeWidth = 5f)
    drawOval(Color(0x88FFFFFF), topLeft = Offset(w * 0.32f, h * 0.83f), size = Size(w * 0.36f, 10f), style = Stroke(width = 3f))

    drawPath(glassPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))
    drawGarnish(w, rimLeft, rimRight, rimTop, garnish)
}

// 8. Shot Glass
private fun DrawScope.drawShot(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val rimLeft = w * 0.30f
    val rimRight = w * 0.70f
    val rimTop = h * 0.38f
    val baseLeft = w * 0.35f
    val baseRight = w * 0.65f
    val baseBottom = h * 0.78f

    val path = Path().apply {
        moveTo(rimLeft, rimTop)
        lineTo(baseLeft, baseBottom)
        lineTo(baseRight, baseBottom)
        lineTo(rimRight, rimTop)
    }

    // Heavy base
    drawRect(Color(0x44FFFFFF), topLeft = Offset(baseLeft + 2f, baseBottom - 18f), size = Size((baseRight - baseLeft) - 4f, 16f))

    if (fill > 0.01f) {
        drawPath(path, Brush.verticalGradient(listOf(color.copy(alpha = 0.95f), color)))
    }

    drawPath(path, Color(0x88FFFFFF), style = Stroke(width = 3f))
    drawLine(Color(0xAAFFFFFF), Offset(rimLeft, rimTop), Offset(rimRight, rimTop), strokeWidth = 3f)
    drawGarnish(w, rimLeft, rimRight, rimTop, garnish)
}

// 9. Wine Goblet
private fun DrawScope.drawWineGlass(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val cx = w * 0.5f
    val bowlLeft = w * 0.25f
    val bowlRight = w * 0.75f
    val bowlTop = h * 0.16f
    val bowlBottom = h * 0.54f
    val stemBottom = h * 0.84f

    val bowlPath = Path().apply {
        moveTo(bowlLeft + 10f, bowlTop)
        cubicTo(bowlLeft - 10f, bowlBottom * 0.75f, cx - 15f, bowlBottom, cx, bowlBottom)
        cubicTo(cx + 15f, bowlBottom, bowlRight + 10f, bowlBottom * 0.75f, bowlRight - 10f, bowlTop)
    }

    if (fill > 0.01f) {
        drawPath(bowlPath, Brush.radialGradient(listOf(color.copy(alpha = 0.9f), color), center = Offset(cx, bowlBottom - 15f)))
    }

    drawLine(Color(0x88FFFFFF), Offset(cx, bowlBottom), Offset(cx, stemBottom), strokeWidth = 4.5f)
    drawOval(Color(0x88FFFFFF), topLeft = Offset(w * 0.32f, stemBottom), size = Size(w * 0.36f, 8f), style = Stroke(width = 3f))
    drawPath(bowlPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))
    drawGarnish(w, bowlLeft, bowlRight, bowlTop, garnish)
}

// 10. American Pint Glass
private fun DrawScope.drawPint(
    w: Float, h: Float,
    fill: Float, color: Color,
    ice: Boolean, garnish: String, wave: Float
) {
    val rimLeft = w * 0.22f
    val rimRight = w * 0.78f
    val rimTop = h * 0.12f
    val baseLeft = w * 0.30f
    val baseRight = w * 0.70f
    val baseBottom = h * 0.86f

    val glassPath = Path().apply {
        moveTo(rimLeft, rimTop)
        lineTo(baseLeft, baseBottom)
        lineTo(baseRight, baseBottom)
        lineTo(rimRight, rimTop)
    }

    if (fill > 0.01f) {
        drawPath(glassPath, Brush.verticalGradient(listOf(color.copy(alpha = 0.9f), color)))
        // Beer foam head
        drawRect(Color(0xEEFFFFFF), topLeft = Offset(rimLeft + 6f, rimTop + 15f), size = Size((rimRight - rimLeft) - 12f, 18f))
    }

    drawPath(glassPath, Color(0x88FFFFFF), style = Stroke(width = 3.5f))
    drawLine(Color(0xAAFFFFFF), Offset(rimLeft, rimTop), Offset(rimRight, rimTop), strokeWidth = 3f)
    drawGarnish(w, rimLeft, rimRight, rimTop, garnish)
}

// Helper: draw clean geometric ice cube
private fun DrawScope.drawIceCube(x: Float, y: Float, size: Float) {
    val cubePath = Path().apply {
        addRoundRect(
            androidx.compose.ui.geometry.RoundRect(
                left = x,
                top = y,
                right = x + size,
                bottom = y + size,
                radiusX = 6f,
                radiusY = 6f
            )
        )
    }
    drawPath(cubePath, Color(0x55DDF4FF))
    drawPath(cubePath, Color(0x88FFFFFF), style = Stroke(width = 1.5f))
    // Diagonal glint
    drawLine(Color(0x99FFFFFF), Offset(x + 5f, y + 5f), Offset(x + size - 5f, y + size - 5f), strokeWidth = 1f)
}

// Helper: draw garnish scaled to real-world dimensions relative to the glass rim width
private fun DrawScope.drawGarnish(w: Float, left: Float, right: Float, top: Float, garnish: String) {
    val g = garnish.lowercase()
    val rimWidth = (right - left).coerceAtLeast(30f)
    val cx = (left + right) / 2f

    when {
        g.contains("lime") -> {
            // Lime wheel (~32% of rim diameter), notched on left rim
            val radius = rimWidth * 0.32f
            val wheelCenter = Offset(left, top - radius * 0.35f)
            // Dark green outer rind
            drawCircle(Color(0xFF1B5E20), radius = radius, center = wheelCenter)
            // Pale pith
            drawCircle(Color(0xFFDCEDC8), radius = radius * 0.88f, center = wheelCenter)
            // Lime pulp segments
            drawCircle(Color(0xFF8BC34A), radius = radius * 0.78f, center = wheelCenter)
            for (i in 0..7) {
                val rad = i * 0.785f
                drawLine(Color(0xFFF1F8E9), wheelCenter, Offset(wheelCenter.x + sin(rad) * radius * 0.76f, wheelCenter.y + kotlin.math.cos(rad) * radius * 0.76f), strokeWidth = 1.2f)
            }
            // Rim notch cut
            drawLine(Color(0x88FFFFFF), Offset(left, wheelCenter.y), Offset(left, wheelCenter.y + radius), strokeWidth = 2f)
        }
        g.contains("lemon_wheel") || (g.contains("lemon") && g.contains("wheel")) -> {
            // Lemon wheel (~32% of rim diameter), clipped on left rim
            val radius = rimWidth * 0.32f
            val wheelCenter = Offset(left, top - radius * 0.35f)
            // Bright lemon yellow outer rind
            drawCircle(Color(0xFFFBC02D), radius = radius, center = wheelCenter)
            // Pale white pith
            drawCircle(Color(0xFFFFFDE7), radius = radius * 0.88f, center = wheelCenter)
            // Juicy lemon pulp
            drawCircle(Color(0xFFFFF176), radius = radius * 0.78f, center = wheelCenter)
            for (i in 0..7) {
                val rad = i * 0.785f
                drawLine(Color.White, wheelCenter, Offset(wheelCenter.x + sin(rad) * radius * 0.76f, wheelCenter.y + kotlin.math.cos(rad) * radius * 0.76f), strokeWidth = 1.2f)
            }
        }
        g.contains("orange_wheel") || (g.contains("orange") && g.contains("wheel")) -> {
            // Orange wheel (~38% of rim diameter), seated on right rim
            val radius = rimWidth * 0.38f
            val wheelCenter = Offset(right, top - radius * 0.4f)
            // Deep orange zest rind
            drawCircle(Color(0xFFE65100), radius = radius, center = wheelCenter)
            // Pale pith
            drawCircle(Color(0xFFFFE0B2), radius = radius * 0.90f, center = wheelCenter)
            // Juicy orange pulp
            drawCircle(Color(0xFFFF9800), radius = radius * 0.82f, center = wheelCenter)
            for (i in 0..9) {
                val rad = i * 0.628f
                drawLine(Color(0xFFFFF3E0), wheelCenter, Offset(wheelCenter.x + sin(rad) * radius * 0.80f, wheelCenter.y + kotlin.math.cos(rad) * radius * 0.80f), strokeWidth = 1.2f)
            }
        }
        g.contains("lemon_twist") || (g.contains("lemon") && g.contains("twist")) -> {
            // Lemon spiral twist peel curling over rim
            val twistPath = Path().apply {
                moveTo(right - 8f, top - 24f)
                cubicTo(right + 22f, top - 12f, right + 18f, top + 10f, right - 4f, top + 16f)
                cubicTo(right - 18f, top + 22f, right + 12f, top + 36f, right + 6f, top + 48f)
            }
            // Yellow peel ribbon with white pith inner side
            drawPath(twistPath, Color(0xFFFDD835), style = Stroke(width = 5.5f, cap = StrokeCap.Round))
            drawPath(twistPath, Color(0xFFFFF9C4), style = Stroke(width = 2.0f, cap = StrokeCap.Round))
        }
        g.contains("orange_twist") || (g.contains("orange") && (g.contains("twist") || g.contains("peel") || g.contains("slice"))) -> {
            // Expressed Orange Peel Ribbon
            val orangePath = Path().apply {
                moveTo(right - 6f, top - 22f)
                cubicTo(right + 25f, top - 8f, right + 20f, top + 16f, right - 6f, top + 24f)
                cubicTo(right - 22f, top + 32f, right + 16f, top + 42f, right + 10f, top + 54f)
            }
            drawPath(orangePath, Color(0xFFE65100), style = Stroke(width = 6.5f, cap = StrokeCap.Round))
            drawPath(orangePath, Color(0xFFFFB74D), style = Stroke(width = 2.5f, cap = StrokeCap.Round))
        }
        g.contains("three_olives") || g.contains("3 olives") || g.contains("3_olives") -> {
            // Stainless cocktail pick across rim with 3 Queen olives
            drawLine(Color(0xFFE0E0E0), Offset(left - 8f, top - 6f), Offset(right + 12f, top + 18f), strokeWidth = 2.5f, cap = StrokeCap.Round)
            // Sphere stop on pick
            drawCircle(Color(0xFFBDBDBD), radius = 3.5f, center = Offset(left - 8f, top - 6f))

            val oliveOffsets = listOf(
                Offset(cx - rimWidth * 0.22f, top + 2f),
                Offset(cx, top + 6f),
                Offset(cx + rimWidth * 0.22f, top + 10f)
            )
            oliveOffsets.forEach { pos ->
                drawOval(Color(0xFF558B2F), topLeft = Offset(pos.x - 9f, pos.y - 12f), size = Size(18f, 24f))
                drawCircle(Color(0xFFD84315), radius = 3.5f, center = Offset(pos.x, pos.y - 1f)) // Red pimiento
                drawOval(Color(0x44FFFFFF), topLeft = Offset(pos.x - 6f, pos.y - 9f), size = Size(5f, 10f)) // Brine sheen
            }
        }
        g.contains("olive") -> {
            // Single Queen olive on cocktail pick
            drawLine(Color(0xFFCCCCCC), Offset(left + 8f, top - 8f), Offset(right - 8f, top + 16f), strokeWidth = 2f, cap = StrokeCap.Round)
            drawOval(Color(0xFF558B2F), topLeft = Offset(cx - 9f, top - 2f), size = Size(18f, 24f))
            drawCircle(Color(0xFFD84315), radius = 3.5f, center = Offset(cx, top + 10f)) // Pimiento
        }
        g.contains("coffee") || g.contains("beans") -> {
            // Three roasted espresso coffee beans floating in triangular formation (health, wealth, happiness)
            val beanCenters = listOf(
                Offset(cx, top + 10f),
                Offset(cx - 14f, top + 22f),
                Offset(cx + 14f, top + 22f)
            )
            beanCenters.forEach { bean ->
                // Roasted dark brown oval bean
                drawOval(Color(0xFF3E2723), topLeft = Offset(bean.x - 7f, bean.y - 5f), size = Size(14f, 10f))
                // Crease line down center of bean
                drawLine(Color(0xFF1B0000), Offset(bean.x - 5f, bean.y), Offset(bean.x + 5f, bean.y), strokeWidth = 1.2f)
                // Crema shadow ring
                drawOval(Color(0x33000000), topLeft = Offset(bean.x - 8f, bean.y - 6f), size = Size(16f, 12f), style = Stroke(width = 1f))
            }
        }
        g.contains("cherry") -> {
            // Brandied maraschino/Luxardo cherry with curved stem
            val cherryCenter = Offset(cx + rimWidth * 0.15f, top + 14f)
            drawCircle(Color(0xFF4A0007), radius = 13f, center = cherryCenter) // Deep dark cherry
            drawCircle(Color(0x66FF1744), radius = 4f, center = Offset(cherryCenter.x - 4f, cherryCenter.y - 4f)) // Highlight
            // Natural woody curved stem
            val stemPath = Path().apply {
                moveTo(cherryCenter.x, cherryCenter.y - 12f)
                cubicTo(cherryCenter.x + 8f, top - 10f, cherryCenter.x + 2f, top - 22f, cherryCenter.x + 12f, top - 28f)
            }
            drawPath(stemPath, Color(0xFF5D4037), style = Stroke(width = 2f, cap = StrokeCap.Round))
        }
        g.contains("mint") -> {
            // Fresh mint bouquet: 4-5 bushy leaves
            drawOval(Color(0xFF2E7D32), topLeft = Offset(right - 24f, top - 20f), size = Size(24f, 14f))
            drawOval(Color(0xFF388E3C), topLeft = Offset(right - 14f, top - 28f), size = Size(20f, 12f))
            drawOval(Color(0xFF4CAF50), topLeft = Offset(right - 6f, top - 18f), size = Size(18f, 10f))
            drawLine(Color(0xFF1B5E20), Offset(right - 16f, top - 12f), Offset(right + 2f, top - 14f), strokeWidth = 1f)
        }
        g.contains("celery") -> {
            // Tall crunchy celery stalk extending from glass bottom to far above rim
            drawLine(Color(0xFF689F38), Offset(right - 8f, top + 60f), Offset(right + 16f, top - 45f), strokeWidth = 9f, cap = StrokeCap.Round)
            drawLine(Color(0xFF7CB342), Offset(right - 9f, top + 55f), Offset(right + 15f, top - 40f), strokeWidth = 3f, cap = StrokeCap.Round) // Rib
            drawOval(Color(0xFF8BC34A), topLeft = Offset(right + 10f, top - 55f), size = Size(16f, 20f)) // Leafy plume
        }
    }
}

private fun DrawScope.drawGlassRimDressing(w: Float, h: Float, glass: GlassType, dressing: String) {
    val (left, right, top) = when (glass) {
        GlassType.MARTINI -> Triple(w * 0.14f, w * 0.86f, h * 0.14f)
        GlassType.COUPE -> Triple(w * 0.16f, w * 0.84f, h * 0.18f)
        GlassType.MARGARITA -> Triple(w * 0.14f, w * 0.86f, h * 0.14f)
        GlassType.ROCKS -> Triple(w * 0.26f, w * 0.74f, h * 0.26f)
        GlassType.HIGHBALL -> Triple(w * 0.28f, w * 0.72f, h * 0.12f)
        GlassType.COPPER_MUG -> Triple(w * 0.26f, w * 0.74f, h * 0.24f)
        GlassType.HURRICANE -> Triple(w * 0.27f, w * 0.73f, h * 0.12f)
        GlassType.SHOT -> Triple(w * 0.33f, w * 0.67f, h * 0.24f)
        GlassType.WINE -> Triple(w * 0.26f, w * 0.74f, h * 0.14f)
        GlassType.BEER_PINT -> Triple(w * 0.24f, w * 0.76f, h * 0.12f)
    }
    val crustColor = when {
        dressing.contains("Tajín", ignoreCase = true) -> Color(0xFFD84315)
        dressing.contains("Sugar", ignoreCase = true) -> Color(0xFFFFF9C4)
        else -> Color.White
    }
    // Draw crust crystals along rim
    drawLine(crustColor.copy(alpha = 0.85f), Offset(left - 2f, top), Offset(right + 2f, top), strokeWidth = 4.5f, cap = StrokeCap.Round)
    for (step in 0..16) {
        val px = left + (right - left) * (step / 16f)
        val py = top + (sin(step * 1.5f) * 2.5f)
        drawCircle(crustColor, radius = 2.2f, center = Offset(px, py))
    }
}

private fun DrawScope.drawSmokeEffect(w: Float, h: Float, glass: GlassType, wave: Float) {
    val top = when (glass) {
        GlassType.ROCKS -> h * 0.28f
        GlassType.HIGHBALL -> h * 0.15f
        GlassType.MARTINI, GlassType.COUPE, GlassType.MARGARITA -> h * 0.18f
        else -> h * 0.22f
    }
    val centerX = w * 0.5f

    // Layer 1: Dense lower mist in glass
    drawOval(
        color = Color(0x75E2E8F0),
        topLeft = Offset(centerX - w * 0.25f + sin(wave) * 6f, top + 15f),
        size = Size(w * 0.5f, 32f)
    )
    // Layer 2: Swirling cloud wisps
    drawOval(
        color = Color(0x60CBD5E1),
        topLeft = Offset(centerX - w * 0.2f + sin(wave + 1.5f) * 10f, top - 2f),
        size = Size(w * 0.42f, 26f)
    )
    // Layer 3: Rising smoke plume escaping glass rim
    drawOval(
        color = Color(0x45F1F5F9),
        topLeft = Offset(centerX - w * 0.18f + sin(wave * 1.8f) * 14f, top - 24f),
        size = Size(w * 0.36f, 22f)
    )
    drawOval(
        color = Color(0x28FFFFFF),
        topLeft = Offset(centerX - w * 0.12f + sin(wave * 2.2f) * 18f, top - 44f),
        size = Size(w * 0.25f, 18f)
    )
}
