package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Ingredient
import com.example.model.IngredientType
import com.example.ui.theme.*

@Composable
fun InteractiveBottleCard(
    ingredient: Ingredient,
    currentVolumeOz: Float,
    isPouring: Boolean,
    onPourClicked: (amount: Float) -> Unit,
    modifier: Modifier = Modifier,
    isLocked: Boolean = false,
    selectedPourOz: Float? = null
) {
    // Rotation tilt when pouring
    val tiltAngle by animateFloatAsState(
        targetValue = if (isPouring) -45f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "bottle_tilt"
    )

    // Depletion fraction (1f is full, 0f is empty)
    val remainingFraction = (currentVolumeOz / ingredient.maxCapacityOz).coerceIn(0.05f, 1f)

    val defaultPour = selectedPourOz ?: when (ingredient.type) {
        IngredientType.WINE -> 4.0f
        IngredientType.BEER -> 12.0f
        else -> ingredient.standardPourOz
    }

    val cardWidth = when (ingredient.type) {
        IngredientType.WINE, IngredientType.BEER -> 106.dp
        else -> 96.dp
    }

    Surface(
        modifier = modifier
            .width(cardWidth)
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = if (isPouring) 2.dp else 1.dp,
                color = if (isLocked) AmberGold.copy(alpha = 0.5f) else if (isPouring) AmberGold else ObsidianCard,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onPourClicked(defaultPour) },
        color = ObsidianSurfaceVariant,
        tonalElevation = if (isPouring) 8.dp else 2.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 8.dp)
        ) {
            // Visual animated bottle silhouette with depletion gauge
            Box(
                modifier = Modifier
                    .size(width = 65.dp, height = 95.dp)
                    .rotate(tiltAngle),
                contentAlignment = Alignment.Center
            ) {
                BottleCanvas(
                    ingredient = ingredient,
                    remainingFraction = remainingFraction,
                    isPouring = isPouring,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Standardized Black Label Box with White Title & Red ABV %
            val abvFormatted = if (ingredient.abv % 1.0f == 0f) {
                "${ingredient.abv.toInt()}% ABV"
            } else {
                "${String.format(java.util.Locale.US, "%.1f", ingredient.abv)}% ABV"
            }

            Surface(
                shape = RoundedCornerShape(6.dp),
                color = Color.Black,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF333333)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 2.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = ingredient.name,
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontSize = 9.5.sp,
                            lineHeight = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = abvFormatted,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 9.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFFFF2A2A)
                        ),
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Depletion Level Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(ObsidianBg)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(remainingFraction)
                            .background(ingredient.color.copy(alpha = 1f))
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${(remainingFraction * 100).toInt()}%",
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 8.sp, color = TextMuted)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Quick Pour Button(s)
            if (isLocked) {
                FilledTonalButton(
                    onClick = { onPourClicked(defaultPour) },
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = AmberGold.copy(alpha = 0.2f),
                        contentColor = AmberGold
                    )
                ) {
                    Text(
                        text = "🔒 PRO ($1)",
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else if (isPouring) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = AmberGold,
                    modifier = Modifier.fillMaxWidth().height(28.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text("Pouring...", fontSize = 9.5.sp, fontWeight = FontWeight.Bold, color = ObsidianBg)
                    }
                }
            } else if (ingredient.type == IngredientType.WINE) {
                // Wine measurements: 4oz and 8oz
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilledTonalButton(
                        onClick = { onPourClicked(4.0f) },
                        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                        modifier = Modifier.weight(1f).height(28.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (selectedPourOz == 4.0f) WineVelvet else ObsidianCard,
                            contentColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("+4 oz", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                    FilledTonalButton(
                        onClick = { onPourClicked(8.0f) },
                        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                        modifier = Modifier.weight(1f).height(28.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (selectedPourOz == 8.0f) WineVelvet else ObsidianCard,
                            contentColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("+8 oz", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            } else if (ingredient.type == IngredientType.BEER) {
                // Beer measurements: 8oz and 12oz
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilledTonalButton(
                        onClick = { onPourClicked(8.0f) },
                        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                        modifier = Modifier.weight(1f).height(28.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (selectedPourOz == 8.0f) AmberGold else ObsidianCard,
                            contentColor = if (selectedPourOz == 8.0f) ObsidianBg else TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("+8 oz", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                    FilledTonalButton(
                        onClick = { onPourClicked(12.0f) },
                        contentPadding = PaddingValues(horizontal = 2.dp, vertical = 2.dp),
                        modifier = Modifier.weight(1f).height(28.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = if (selectedPourOz == 12.0f) AmberGold else ObsidianCard,
                            contentColor = if (selectedPourOz == 12.0f) ObsidianBg else TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text("+12 oz", fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            } else {
                FilledTonalButton(
                    onClick = { onPourClicked(defaultPour) },
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = ObsidianCard,
                        contentColor = TextPrimary
                    )
                ) {
                    Text(
                        text = "+${defaultPour} ${ingredient.unit}",
                        fontSize = 9.5.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun BottleCanvas(
    ingredient: Ingredient,
    remainingFraction: Float,
    isPouring: Boolean,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        if (ingredient.type == IngredientType.BEER) {
            val id = ingredient.id.lowercase()
            val isCan = id.contains("can") || id.contains("spun") || id.contains("aluminum") ||
                    id == "guinness_draught" || id == "sapporo_premium" || id == "bud_platinum" ||
                    id == "michelob_ultra" || id == "miller_lite" || id == "blue_moon" || id == "shock_top"

            if (isCan) {
                RealWorldCanRenderer.drawBeerCan(this, ingredient, remainingFraction, isPouring)
            } else {
                RealWorldBottleRenderer.drawBottle(this, ingredient, remainingFraction, isPouring)
            }
        } else {
            // Spirits, Wines, Mixers, Bitters render as authentic real-world bottles
            RealWorldBottleRenderer.drawBottle(this, ingredient, remainingFraction, isPouring)
        }
    }
}
