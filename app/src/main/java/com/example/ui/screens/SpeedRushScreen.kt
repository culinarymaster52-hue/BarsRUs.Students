package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarCraftData
import com.example.model.*
import com.example.ui.components.CustomerOrderCard
import com.example.ui.components.GlasswareCanvas
import com.example.ui.theme.*
import com.example.util.ShareHelper
import com.example.viewmodel.BarCraftViewModel
import java.util.Locale

@Composable
fun SpeedRushScreen(
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val speedState by viewModel.speedRushState.collectAsState()

    val currentDrink = if (speedState.roundDrinks.isNotEmpty()) {
        speedState.roundDrinks[speedState.currentCustomerIndex.coerceIn(0, speedState.roundDrinks.size - 1)]
    } else {
        BarCraftData.classicCocktails[0]
    }

    val currentCustomer = BarCraftData.speedCustomers[speedState.currentCustomerIndex % BarCraftData.speedCustomers.size]

    val scrollState = rememberScrollState()

    // Crafting local state for adding ingredients
    var selectedIngredientId by remember { mutableStateOf(currentDrink.ingredients.firstOrNull()?.ingredient?.id ?: "vodka") }
    var selectedPourOz by remember { mutableStateOf(1.0f) }
    var selectedDashes by remember { mutableStateOf(2) }

    // When the customer index changes, reset the default selected ingredient to the new drink's first ingredient
    LaunchedEffect(speedState.currentCustomerIndex) {
        selectedIngredientId = currentDrink.ingredients.firstOrNull()?.ingredient?.id ?: "vodka"
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Top Header & 10-Minute Timer
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "BARTENDER SPEED RUSH",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGold,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "10-Minute Shift • 12 Rotating Customer Drinks",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }

            // Simulated Tips Badge
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(AmberGold))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text("💵", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "$${String.format(Locale.US, "%.2f", speedState.simulatedTipsEarned)} Tips",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = SuccessGreen)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        if (!speedState.isPlaying && !speedState.roundFinished) {
            // Start Shift Screen
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text("⏱️", fontSize = 52.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "10-MINUTE SPEED RUSH SHIFT",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Real bar customers are waiting for 12 distinct cocktails!\n\n" +
                                "• 10-Minute Shift Clock (600 seconds)\n" +
                                "• Exactly 12 different drinks each round (new dozen every 10 min!)\n" +
                                "• Interactively select: (1) Glassware, (2) Technique, (3) Ingredients & Measurements, and (4) Garnish!\n\n" +
                                "Deliver precision drinks before time expires to earn generous cash tips!",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary, textAlign = TextAlign.Center)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { viewModel.startSpeedRush() },
                        colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("start_rush_button")
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Start 10-Minute Shift (12 Drinks)", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                }
            }
        } else if (speedState.isPlaying) {
            // 10-Minute Timer Bar
            val minutes = speedState.secondsLeft / 60
            val seconds = speedState.secondsLeft % 60
            val timerColor = when {
                speedState.secondsLeft > 180 -> SuccessGreen
                speedState.secondsLeft > 60 -> AmberGold
                else -> ErrorRed
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ObsidianSurface,
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(timerColor)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("⏱️", fontSize = 18.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "SHIFT CLOCK",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextSecondary, fontSize = 9.sp)
                            )
                            Text(
                                text = String.format(Locale.US, "%02d:%02d", minutes, seconds),
                                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black, color = timerColor)
                            )
                        }
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                            Text(
                                text = "ORDER ${speedState.currentCustomerIndex + 1} OF 12",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = ObsidianBg, fontSize = 10.sp)
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Shift Round #${speedState.roundNumber}",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextMuted, fontSize = 10.sp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Customer Order Details Card
            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    val avatarEmoji = when (currentCustomer.id) {
                        "c1" -> "👔"
                        "c2" -> "👩‍💼"
                        "c3" -> "🎩"
                        "c4" -> "💃"
                        "c5" -> "🕶️"
                        else -> "🍸"
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            shape = CircleShape,
                            color = ObsidianCard,
                            modifier = Modifier.size(42.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(avatarEmoji, fontSize = 22.sp)
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = currentCustomer.name,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                fontSize = 13.sp
                            )
                            Text(
                                text = "\"I'd like a classic ${currentDrink.name}!\"",
                                color = AmberGold,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 12.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = ObsidianCard,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "COCKTAIL SPECS (TARGET REQUIREMENTS):",
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextSecondary, fontSize = 9.sp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Glass: ${currentDrink.glass.displayName}  •  Method: ${currentDrink.technique.actionName}  •  Garnish: ${currentDrink.garnish}",
                                style = MaterialTheme.typography.bodySmall.copy(color = AmberGoldLight, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            val recipeIngredientsText = currentDrink.ingredients.joinToString(" + ") {
                                "${it.amountOz} oz ${it.ingredient.name}"
                            }
                            Text(
                                text = "Recipe: $recipeIngredientsText",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextPrimary, fontSize = 10.5.sp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // === INTERACTIVE CRAFTING WORKBENCH ===
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "INTERACTIVE CRAFTING WORKBENCH",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.ExtraBold, color = AmberGold)
                    )
                    Text(
                        text = "Build the order by selecting Glassware, Technique, Ingredients & Garnish",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.sp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // 1. GLASS SELECTION
                    Text("1. Select Glassware:", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                        items(GlassType.values()) { glass ->
                            val isSelected = speedState.selectedGlass == glass
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) AmberGold else ObsidianCard,
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianSurfaceVariant)
                                ),
                                modifier = Modifier.clickable { viewModel.selectSpeedGlass(glass) }
                            ) {
                                Text(
                                    text = glass.displayName.split(" ").first(),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) ObsidianBg else TextPrimary,
                                        fontSize = 10.5.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 2. PROCEDURE / TECHNIQUE SELECTION
                    Text("2. Select Technique / Procedure:", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                        PrepTechnique.values().forEach { tech ->
                            val isSelected = speedState.selectedTechnique == tech
                            val emoji = when (tech) {
                                PrepTechnique.SHAKEN -> "🌪️"
                                PrepTechnique.STIRRED -> "🥄"
                                PrepTechnique.BUILT -> "🧊"
                                PrepTechnique.BLENDED -> "⚡"
                            }
                            OutlinedButton(
                                onClick = { viewModel.selectSpeedTechnique(tech) },
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSelected) AmberGold else ObsidianCard,
                                    contentColor = if (isSelected) ObsidianBg else TextPrimary
                                ),
                                modifier = Modifier.weight(1f),
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 6.dp)
                            ) {
                                Text("$emoji ${tech.actionName.split(" ").first()}", fontSize = 10.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 3. INGREDIENTS & EXACT MEASUREMENTS PANEL
                    Text("3. Add Ingredients & Measurements:", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(4.dp))

                    // Quick Select Ingredient from shelf
                    val shelfIngredients = (currentDrink.ingredients.map { it.ingredient } + BarCraftData.ingredients.take(20)).distinctBy { it.id }
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                        items(shelfIngredients) { ing ->
                            val isSelected = selectedIngredientId == ing.id
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) AmberGold.copy(alpha = 0.25f) else ObsidianCard,
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianSurfaceVariant)
                                ),
                                modifier = Modifier.clickable { selectedIngredientId = ing.id }
                            ) {
                                Text(
                                    text = ing.name,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) AmberGold else TextPrimary,
                                        fontSize = 10.sp
                                    ),
                                    maxLines = 1
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Measurement Step Selector (0.1 oz to 2.0 oz, Dashes, 4/8oz Wine, 8/12oz Beer)
                    val activeIng = BarCraftData.getIngredient(selectedIngredientId)
                    val isBitters = activeIng.type == IngredientType.BITTERS
                    val isWine = activeIng.type == IngredientType.WINE
                    val isBeer = activeIng.type == IngredientType.BEER

                    if (isBitters) {
                        Text("Bitters Dashes (1 to 6):", fontSize = 10.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
                            (1..6).forEach { d ->
                                val isSel = selectedDashes == d
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSel) AmberGold else ObsidianCard,
                                    modifier = Modifier.weight(1f).clickable { selectedDashes = d }
                                ) {
                                    Text(
                                        text = "$d",
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        textAlign = TextAlign.Center,
                                        fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSel) ObsidianBg else TextPrimary,
                                        fontSize = 11.sp
                                    )
                                }
                            }
                        }
                    } else if (isWine) {
                        Text("Wine Pour Measurement (4 oz or 8 oz):", fontSize = 10.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                            listOf(4.0f to "🍷 4 oz Standard Glass", 8.0f to "🍷 8 oz Large Pour / Carafe").forEach { (oz, label) ->
                                val isSel = kotlin.math.abs(selectedPourOz - oz) < 0.1f
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSel) WineVelvet else ObsidianCard,
                                    modifier = Modifier.weight(1f).clickable { selectedPourOz = oz }
                                ) {
                                    Text(
                                        text = label,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp),
                                        textAlign = TextAlign.Center,
                                        fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                        color = TextPrimary,
                                        fontSize = 10.5.sp,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    } else if (isBeer) {
                        Text("Beer Pour Measurement (8 oz or 12 oz):", fontSize = 10.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                            listOf(8.0f to "🍺 8 oz Half Pint", 12.0f to "🍺 12 oz Full Bottle / Pint").forEach { (oz, label) ->
                                val isSel = kotlin.math.abs(selectedPourOz - oz) < 0.1f
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSel) AmberGold else ObsidianCard,
                                    modifier = Modifier.weight(1f).clickable { selectedPourOz = oz }
                                ) {
                                    Text(
                                        text = label,
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 6.dp),
                                        textAlign = TextAlign.Center,
                                        fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSel) ObsidianBg else TextPrimary,
                                        fontSize = 10.5.sp,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    } else {
                        Text("Jigger Measurement (oz):", fontSize = 10.sp, color = TextSecondary)
                        Spacer(modifier = Modifier.height(4.dp))
                        val ozSteps = listOf(0.1f, 0.25f, 0.5f, 0.75f, 1.0f, 1.25f, 1.5f, 2.0f)
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
                            items(ozSteps) { oz ->
                                val isSel = kotlin.math.abs(selectedPourOz - oz) < 0.04f
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = if (isSel) AmberGold else ObsidianCard,
                                    modifier = Modifier.clickable { selectedPourOz = oz }
                                ) {
                                    Text(
                                        text = "${oz} oz",
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 4.dp),
                                        fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSel) ObsidianBg else TextPrimary,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Button(
                        onClick = {
                            val amount = if (isBitters) (selectedDashes * 0.03f) else selectedPourOz
                            viewModel.addSpeedIngredient(activeIng, amount)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = ObsidianCard, contentColor = AmberGold),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxWidth().height(36.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = if (isBitters) "Add $selectedDashes Dashes of ${activeIng.name}" else "Add ${selectedPourOz} oz of ${activeIng.name}",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Added Ingredients in Tin List
                    if (speedState.addedIngredients.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(shape = RoundedCornerShape(8.dp), color = ObsidianSurfaceVariant, modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.padding(8.dp)) {
                                Text("Vessel Contents:", fontWeight = FontWeight.Bold, color = TextSecondary, fontSize = 10.sp)
                                Spacer(modifier = Modifier.height(4.dp))
                                speedState.addedIngredients.forEachIndexed { idx, item ->
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "• ${String.format(Locale.US, "%.2f", item.amountOz)} oz ${item.ingredient.name}",
                                            color = TextPrimary,
                                            fontSize = 11.sp
                                        )
                                        IconButton(
                                            onClick = { viewModel.removeSpeedIngredient(idx) },
                                            modifier = Modifier.size(22.dp)
                                        ) {
                                            Icon(Icons.Default.Close, contentDescription = "Remove", tint = TextMuted, modifier = Modifier.size(14.dp))
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // 4. GARNISH SELECTION
                    Text("4. Select Garnish:", fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 11.sp)
                    Spacer(modifier = Modifier.height(4.dp))
                    val speedGarnishes = BarCraftData.ingredients.filter { it.type == IngredientType.GARNISH && !it.id.contains("rim") }
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(6.dp), modifier = Modifier.fillMaxWidth()) {
                        items(speedGarnishes) { g ->
                            val isSelected = speedState.selectedGarnish == g.id
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = if (isSelected) AmberGold else ObsidianCard,
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianSurfaceVariant)
                                ),
                                modifier = Modifier.clickable { viewModel.selectSpeedGarnish(g.id) }
                            ) {
                                Text(
                                    text = g.name,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) ObsidianBg else TextPrimary,
                                        fontSize = 10.5.sp
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons: Clear & Serve
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(
                            onClick = { viewModel.clearSpeedCraft() },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Clear Tin", fontSize = 11.sp)
                        }

                        Button(
                            onClick = { viewModel.serveSpeedCustomer() },
                            colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(2f).height(46.dp).testTag("serve_speed_drink_button")
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Serve Cocktail!", fontWeight = FontWeight.ExtraBold, fontSize = 13.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (speedState.feedbackMessage.isNotBlank()) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = ObsidianCard,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = speedState.feedbackMessage,
                        style = MaterialTheme.typography.bodySmall.copy(color = AmberGoldLight, textAlign = TextAlign.Center),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
        } else if (speedState.roundFinished) {
            // Shift Complete Screen
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(24.dp)
                ) {
                    Text("🎉", fontSize = 48.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "10-MINUTE SHIFT COMPLETE!",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold, color = AmberGold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Total Simulated Tips: $${String.format(Locale.US, "%.2f", speedState.simulatedTipsEarned)}",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = SuccessGreen)
                    )
                    Text(
                        text = "Cocktails Crafted: ${speedState.drinksServedCount} of 12 • Score: ${speedState.score} pts",
                        style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = { viewModel.nextSpeedRushRound() },
                        colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("next_shift_round_button")
                    ) {
                        Icon(Icons.Default.FastForward, contentDescription = null)
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Next 10-Minute Shift (12 New Drinks)", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedButton(
                        onClick = { ShareHelper.shareLeaderboardScore(context, "Student Bartender", speedState.score, "Speed Bartender") },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Share Shift Score")
                    }
                }
            }
        }
    }
}
