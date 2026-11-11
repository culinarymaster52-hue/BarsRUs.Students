package com.example.ui.components

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarCraftData
import com.example.data.WineCatalog
import com.example.data.BeerCatalog
import com.example.model.*
import com.example.ui.theme.*
import com.example.viewmodel.BarCraftViewModel

@Composable
fun BehindTheBarWorkbench(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        // Station Selector Header
        Text(
            text = "BEHIND-THE-BAR STATIONS & TOOLS",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.ExtraBold,
                color = AmberGold,
                letterSpacing = 1.sp
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Station Tabs
        ScrollableTabRow(
            selectedTabIndex = BarStation.values().indexOf(barState.activeBarStation),
            containerColor = ObsidianSurface,
            contentColor = AmberGold,
            edgePadding = 0.dp,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            BarStation.values().forEach { station ->
                val selected = barState.activeBarStation == station
                Tab(
                    selected = selected,
                    onClick = { viewModel.setBarStation(station) },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(station.iconEmoji, fontSize = 13.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = station.displayName,
                                fontSize = 11.sp,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Station Content
        AnimatedContent(targetState = barState.activeBarStation, label = "station_transition") { station ->
            when (station) {
                BarStation.SPEED_RAIL -> SpeedRailStation(barState, viewModel)
                BarStation.TOOLS -> ToolsStation(barState, viewModel)
                BarStation.MIXERS -> MixersStation(barState, viewModel)
                BarStation.GARNISH_BOARD -> GarnishBoardStation(barState, viewModel)
                BarStation.ICE_WELL -> IceWellStation(barState, viewModel)
                BarStation.RIMMER -> RimmerStation(barState, viewModel)
                BarStation.BEER_AND_WINE -> BeerAndWineStation(barState, viewModel)
                BarStation.GLASS_RACK -> GlassRackStation(barState, viewModel)
            }
        }
    }
}

// === STATION 1: SPEED RAIL & SPIRITS ===
@Composable
private fun SpeedRailStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    var selectedSpiritType by remember { mutableStateOf(IngredientType.SPIRIT) }
    val spiritCategories = listOf(IngredientType.SPIRIT, IngredientType.APERITIF, IngredientType.BITTERS, IngredientType.WINE)

    Column(modifier = Modifier.fillMaxWidth()) {
        if (selectedSpiritType == IngredientType.BITTERS) {
            // Dedicated Bitters Measured in Dashes (1 to 6 dashes)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("💧", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Bitters Measured in Dashes:",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                            Text(
                                text = "${barState.selectedBittersDashes} ${if (barState.selectedBittersDashes == 1) "DASH" else "DASHES"} (${String.format(java.util.Locale.US, "%.2f", barState.selectedBittersDashes * 0.03f)} oz)",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = ObsidianBg, fontSize = 9.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        (1..6).forEach { dashes ->
                            val isSelected = barState.selectedBittersDashes == dashes
                            OutlinedButton(
                                onClick = { viewModel.setBittersDashes(dashes) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSelected) AmberGold else ObsidianCard,
                                    contentColor = if (isSelected) ObsidianBg else TextPrimary
                                ),
                                contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "$dashes",
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }
        } else if (selectedSpiritType == IngredientType.WINE) {
            // Wine Pour Measurements (4 oz and 8 oz)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🍷", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Wine Pour Measurements:",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(6.dp), color = WineVelvet) {
                            Text(
                                text = "${barState.selectedWinePourOz.toInt()} OZ POUR",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 9.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        listOf(4.0f to "4 oz Standard Glass", 8.0f to "8 oz Large Pour / Carafe").forEach { (oz, label) ->
                            val isSel = barState.selectedWinePourOz == oz
                            OutlinedButton(
                                onClick = { viewModel.setWinePourMeasure(oz) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSel) WineVelvet else ObsidianCard,
                                    contentColor = TextPrimary
                                ),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 10.5.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        } else if (selectedSpiritType == IngredientType.BEER) {
            // Beer Pour Measurements (8 oz and 12 oz)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🍺", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Beer Pour Measurements:",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                            Text(
                                text = "${barState.selectedBeerPourOz.toInt()} OZ POUR",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = ObsidianBg, fontSize = 9.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        listOf(8.0f to "8 oz Half Pint / Goblet", 12.0f to "12 oz Full Bottle / Pint").forEach { (oz, label) ->
                            val isSel = barState.selectedBeerPourOz == oz
                            OutlinedButton(
                                onClick = { viewModel.setBeerPourMeasure(oz) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSel) AmberGold else ObsidianCard,
                                    contentColor = if (isSel) ObsidianBg else TextPrimary
                                ),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 10.5.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        } else {
            // Jigger Measure Quick-Bar
            JiggerQuickBar(
                selectedOz = barState.selectedJiggerMeasureOz,
                onSelectOz = { viewModel.setJiggerMeasure(it) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Subcategory Filter Chips
        val spiritCategories = listOf(IngredientType.SPIRIT, IngredientType.APERITIF, IngredientType.BITTERS, IngredientType.WINE, IngredientType.BEER)
        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            spiritCategories.forEach { type ->
                FilterChip(
                    selected = selectedSpiritType == type,
                    onClick = { selectedSpiritType = type },
                    label = { Text(type.displayName.split(" ").first(), fontSize = 11.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = AmberGold.copy(alpha = 0.25f),
                        selectedLabelColor = AmberGold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Bottles Row
        val spirits = BarCraftData.ingredients.filter { it.type == selectedSpiritType }
        val currentPour = when (selectedSpiritType) {
            IngredientType.WINE -> barState.selectedWinePourOz
            IngredientType.BEER -> barState.selectedBeerPourOz
            else -> barState.selectedJiggerMeasureOz
        }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(spirits) { ing ->
                val currentVol = barState.bottleDepletions[ing.id] ?: ing.maxCapacityOz
                val isLocked = !viewModel.isIngredientUnlocked(ing.id)
                InteractiveBottleCard(
                    ingredient = ing,
                    currentVolumeOz = currentVol,
                    isPouring = barState.isPouringId == ing.id,
                    isLocked = isLocked,
                    selectedPourOz = currentPour,
                    onPourClicked = { amount ->
                        if (ing.type == IngredientType.BITTERS) {
                            viewModel.pourBitters(ing, barState.selectedBittersDashes)
                        } else {
                            viewModel.pourIngredient(ing, amount)
                        }
                    }
                )
            }
        }
    }
}

// === STATION 2: BARTENDER TOOLS ===
@Composable
private fun ToolsStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    var showMasterToolsGuide by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        // 1. Japanese Precision Jigger Tool Card (1/10 oz to 2.0 oz)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("📐", fontSize = 22.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Japanese Precision Jigger (⅒ oz - 2 oz)",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                        )
                        Text(
                            text = "Calibrate exact fluid ounces from 1/10 oz (0.1 oz) up to 2.0 oz before pouring into Boston tin",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.5.sp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = AmberGold
                    ) {
                        Text(
                            text = "${barState.selectedJiggerMeasureOz} OZ",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black, color = ObsidianBg)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))

                // Quick Oz Selector Steps from 1/10 oz to 2 oz
                val jiggerSteps = listOf(
                    0.1f to "⅒",
                    0.25f to "¼",
                    0.33f to "⅓",
                    0.5f to "½",
                    0.75f to "¾",
                    1.0f to "1",
                    1.25f to "1¼",
                    1.5f to "1½",
                    1.75f to "1¾",
                    2.0f to "2"
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(jiggerSteps) { (oz, label) ->
                        val isSelected = kotlin.math.abs(barState.selectedJiggerMeasureOz - oz) < 0.04f
                        OutlinedButton(
                            onClick = { viewModel.setJiggerMeasure(oz) },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) AmberGold else ObsidianSurface,
                                contentColor = if (isSelected) ObsidianBg else TextPrimary
                            ),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianCard)
                            ),
                            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$label oz",
                                fontSize = 10.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Continuous 0.1 oz Increment Precision Slider
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text("0.1 oz", fontSize = 9.5.sp, color = TextMuted)
                    Slider(
                        value = barState.selectedJiggerMeasureOz,
                        onValueChange = { raw ->
                            val stepped = (kotlin.math.round(raw * 10f) / 10f).coerceIn(0.1f, 2.0f)
                            viewModel.setJiggerMeasure(stepped)
                        },
                        valueRange = 0.1f..2.0f,
                        steps = 18,
                        colors = SliderDefaults.colors(thumbColor = AmberGold, activeTrackColor = AmberGold),
                        modifier = Modifier.weight(1f).padding(horizontal = 6.dp)
                    )
                    Text("2.0 oz", fontSize = 9.5.sp, color = TextMuted)
                }
            }
        }

        // 2. Bitters Dashes Station (1 to 6 Dashes)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💧", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Bitters Dropper & Dashes (1 to 6)",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                        )
                        Text(
                            text = "Aromatic bitters are dosed in exact dashes (1 dash ≈ 0.03 oz / ~10 drops) to balance aromatics",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.5.sp)
                        )
                    }
                    Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                        Text(
                            text = "${barState.selectedBittersDashes} DASHES",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black, color = ObsidianBg, fontSize = 9.sp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    (1..6).forEach { dashes ->
                        val isSelected = barState.selectedBittersDashes == dashes
                        OutlinedButton(
                            onClick = { viewModel.setBittersDashes(dashes) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = if (isSelected) AmberGold else ObsidianSurface,
                                contentColor = if (isSelected) ObsidianBg else TextPrimary
                            ),
                            border = ButtonDefaults.outlinedButtonBorder.copy(
                                brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianCard)
                            ),
                            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 4.dp)
                        ) {
                            Text(
                                text = "$dashes",
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        }
                    }
                }
            }
        }

        // 3. Cocktail Smoke Chimney & Cloche Card
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💨", fontSize = 20.sp)
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Cocktail Smoker & Cloche",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                            )
                            if (barState.isSmoked) {
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    shape = RoundedCornerShape(4.dp),
                                    color = WarningOrange.copy(alpha = 0.2f)
                                ) {
                                    Text(
                                        text = "SMOKED",
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(color = WarningOrange, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                                    )
                                }
                            }
                        }
                        Text(
                            text = "Torch hardwood chips over the glass to infuse aromatic campfire smoke into Old Fashioneds",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.5.sp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Wood Chip Selection
                val woodChips = listOf("Charred Bourbon Oak", "Applewood", "Hickory", "Cherrywood")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    woodChips.forEach { wood ->
                        val isSelected = barState.selectedWoodChip == wood
                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.useCocktailSmoker(wood) },
                            label = { Text(wood.split(" ").first(), fontSize = 10.sp) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { viewModel.useCocktailSmoker(barState.selectedWoodChip) },
                        enabled = !barState.isSmokingActive,
                        colors = ButtonDefaults.buttonColors(containerColor = WarningOrange, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f).testTag("smoke_glass_button")
                    ) {
                        Icon(Icons.Default.LocalFireDepartment, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(if (barState.isSmokingActive) "Billowing Smoke..." else "🔥 Smoke Glass", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }

                    if (barState.isSmoked) {
                        OutlinedButton(
                            onClick = { viewModel.clearSmoke() },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Clear Smoke", fontSize = 11.sp)
                        }
                    }
                }
            }
        }

        // 4. Strainer Dock: Hawthorne vs Julep vs Fine Mesh
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "STRAINER DOCK & DOUBLE-STRAINING",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Hawthorne for shaken tins with coil spring; Julep for stirred mixing glasses.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.5.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Hawthorne
                    OutlinedButton(
                        onClick = { viewModel.equipStrainer(BarTool.HAWTHORNE) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (barState.activeStrainer == BarTool.HAWTHORNE) AmberGold.copy(alpha = 0.2f) else ObsidianSurface,
                            contentColor = if (barState.activeStrainer == BarTool.HAWTHORNE) AmberGold else TextPrimary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🌀 Hawthorne", fontSize = 11.sp)
                    }

                    // Julep
                    OutlinedButton(
                        onClick = { viewModel.equipStrainer(BarTool.JULEP) },
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = if (barState.activeStrainer == BarTool.JULEP) AmberGold.copy(alpha = 0.2f) else ObsidianSurface,
                            contentColor = if (barState.activeStrainer == BarTool.JULEP) AmberGold else TextPrimary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("🥏 Julep", fontSize = 11.sp)
                    }

                    // Double Strain Toggle
                    Button(
                        onClick = { viewModel.toggleDoubleStraining() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (barState.isDoubleStraining) SuccessGreen else ObsidianSurface,
                            contentColor = if (barState.isDoubleStraining) ObsidianBg else TextSecondary
                        ),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(if (barState.isDoubleStraining) "🕸️ Fine Mesh (ON)" else "🕸️ Double Strain", fontSize = 10.sp)
                    }
                }
            }
        }

        // 5. Bar Station Tools Grid (Muddler, Barspoon, Knife, Tongs, Squeezer, Towel)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            // Muddler
            ToolActionCard(
                icon = "🪵",
                title = "Hardwood Muddler",
                subtitle = "Crush mint & sugar",
                actionLabel = if (barState.isMuddling) "Muddling..." else "Muddle Tin",
                onClick = { viewModel.useMuddler() },
                enabled = !barState.isMuddling,
                modifier = Modifier.weight(1f)
            )

            // Barspoon
            ToolActionCard(
                icon = "🥄",
                title = "Twisted Bar Spoon",
                subtitle = "Laminar gentle stir",
                actionLabel = if (barState.isStirring) "Stirring..." else "Stir Shaker",
                onClick = { viewModel.useBarSpoon() },
                enabled = !barState.isStirring,
                modifier = Modifier.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            // Paring Knife
            ToolActionCard(
                icon = "🔪",
                title = "Paring Knife",
                subtitle = "Express citrus twist",
                actionLabel = "Carve Garnish",
                onClick = { viewModel.useParingKnife("lemon_twist") },
                modifier = Modifier.weight(1f)
            )

            // Sanitary Ice Tongs
            ToolActionCard(
                icon = "🥢",
                title = "Ice Tongs & Scoop",
                subtitle = "ServSafe sanitary ice",
                actionLabel = if (barState.hasIce) "Empty Ice" else "Add Ice Tongs",
                onClick = { viewModel.useIceTongs(!barState.hasIce) },
                modifier = Modifier.weight(1f)
            )
        }

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            // Citrus Elbow Squeezer
            ToolActionCard(
                icon = "🍋",
                title = "Citrus Elbow Press",
                subtitle = "Fresh cold-pressed juice",
                actionLabel = "Press Lime / Lemon",
                onClick = { viewModel.useCitrusSqueezer("lime_juice") },
                modifier = Modifier.weight(1f)
            )

            // Sanitizer Towel
            ToolActionCard(
                icon = "🧻",
                title = "Microfiber Bar Towel",
                subtitle = "Sanitize workstation",
                actionLabel = "Wipe Counter",
                onClick = { viewModel.useBarTowel() },
                modifier = Modifier.weight(1f)
            )
        }

        // 6. Expandable Bartending Tools Master Guide (All 13 Professional Tools)
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianCard),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { showMasterToolsGuide = !showMasterToolsGuide }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("📘", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Bartender Tools Master Guide (13 Tools)",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                            )
                            Text(
                                text = "What each professional tool is used for and operational technique",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                    }
                    IconButton(onClick = { showMasterToolsGuide = !showMasterToolsGuide }) {
                        Icon(
                            imageVector = if (showMasterToolsGuide) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = AmberGold
                        )
                    }
                }

                AnimatedVisibility(visible = showMasterToolsGuide) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(top = 10.dp)
                    ) {
                        BarTool.values().forEach { tool ->
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = ObsidianSurface,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Text(tool.iconEmoji, fontSize = 22.sp)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text(
                                                text = tool.displayName,
                                                fontWeight = FontWeight.Bold,
                                                color = TextPrimary,
                                                fontSize = 12.sp
                                            )
                                            Surface(
                                                shape = RoundedCornerShape(4.dp),
                                                color = AmberGold.copy(alpha = 0.15f)
                                            ) {
                                                Text(
                                                    text = tool.category.uppercase(),
                                                    color = AmberGold,
                                                    fontSize = 8.5.sp,
                                                    fontWeight = FontWeight.Bold,
                                                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = tool.description,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                fontSize = 10.sp,
                                                lineHeight = 14.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// === STATION 3: MIXERS & JUICES ===
@Composable
private fun MixersStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        JiggerQuickBar(
            selectedOz = barState.selectedJiggerMeasureOz,
            onSelectOz = { viewModel.setJiggerMeasure(it) }
        )

        Spacer(modifier = Modifier.height(10.dp))

        val mixers = BarCraftData.ingredients.filter { it.type == IngredientType.MIXER }
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(mixers) { ing ->
                val currentVol = barState.bottleDepletions[ing.id] ?: ing.maxCapacityOz
                val isLocked = !viewModel.isIngredientUnlocked(ing.id)
                InteractiveBottleCard(
                    ingredient = ing,
                    currentVolumeOz = currentVol,
                    isPouring = barState.isPouringId == ing.id,
                    isLocked = isLocked,
                    onPourClicked = { viewModel.pourIngredient(ing, barState.selectedJiggerMeasureOz) }
                )
            }
        }
    }
}

// === STATION 4: GARNISH BOARD ===
@Composable
private fun GarnishBoardStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🔪", fontSize = 22.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Cutting Board & Garnish Caddy",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                    )
                    Text(
                        text = "Clip freshly sliced citrus wheels, slap mint bouquets, and spear Spanish olives",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val garnishes = BarCraftData.ingredients.filter { it.type == IngredientType.GARNISH && !it.id.contains("rim") }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                garnishes.chunked(2).forEach { rowGarnishes ->
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        rowGarnishes.forEach { g ->
                            val isSelected = barState.selectedGarnish == g.id
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) AmberGold.copy(alpha = 0.25f) else ObsidianSurface,
                                border = ButtonDefaults.outlinedButtonBorder.copy(
                                    brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianCard)
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { viewModel.setGarnish(g.id) }
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    val emoji = when (g.id) {
                                        "lime_wheel" -> "🍋"
                                        "lemon_wheel" -> "🍋"
                                        "orange_wheel" -> "🍊"
                                        "lemon_twist" -> "🍋"
                                        "orange_twist" -> "🍊"
                                        "orange_slice" -> "🍊"
                                        "mint_sprig" -> "🌿"
                                        "cherry" -> "🍒"
                                        "three_olives" -> "🫒"
                                        "olive" -> "🫒"
                                        "coffee_beans" -> "☕"
                                        "celery_stalk" -> "🥬"
                                        else -> "✨"
                                    }
                                    Text(emoji, fontSize = 18.sp)
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(g.name, fontWeight = FontWeight.Bold, fontSize = 11.5.sp, color = if (isSelected) AmberGold else TextPrimary)
                                        Text(g.flavorNote, fontSize = 9.5.sp, color = TextSecondary, maxLines = 1)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// === STATION 5: ICE WELL & TONGS ===
@Composable
private fun IceWellStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🧊", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Ice Well & Sanitary Tongs Station",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                    )
                    Text(
                        text = "ServSafe Standard: Always use stainless steel tongs or aluminum scoops. Never handle ice with glassware.",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFF14222D),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF38BDF8))
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column {
                            Text("Ice Status in Vessel", fontWeight = FontWeight.Bold, color = Color(0xFFE0F2FE), fontSize = 13.sp)
                            Text(if (barState.hasIce) "Filled with crystalline cold cubes" else "Empty / Neat / Chilled", color = Color(0xFF7DD3FC), fontSize = 11.sp)
                        }
                        Button(
                            onClick = { viewModel.useIceTongs(!barState.hasIce) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (barState.hasIce) Color(0xFF0284C7) else Color(0xFF0369A1),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(if (barState.hasIce) "🥢 Remove Ice with Tongs" else "🥢 Grip & Add Ice", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }
}

// === STATION 6: 3-TIER ROTATING RIMMER ===
@Composable
private fun RimmerStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🧂", fontSize = 24.sp)
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "3-Tier Rotating Glass Rimmer",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                    )
                    Text(
                        text = "Fold-out tiers: Lime juice sponge, coarse sea salt, raw sugar, and Mexican Tajín",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            val rimOptions = listOf(
                Pair("Flaky Sea Salt Rim", "Margarita, Salty Dog, Paloma"),
                Pair("Raw Cane Sugar Rim", "Sidecar, Lemon Drop, Crusta"),
                Pair("Tajín Chili-Lime Rim", "Michelada, Cantarito, Spicy Mezcal")
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                rimOptions.forEach { (dressing, bestFor) ->
                    val isDressed = barState.rimDressing == dressing
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isDressed) AmberGold.copy(alpha = 0.25f) else ObsidianSurface,
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(if (isDressed) AmberGold else ObsidianCard)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.useGlassRimmer(dressing) }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Column {
                                Text(dressing, fontWeight = FontWeight.Bold, color = if (isDressed) AmberGold else TextPrimary, fontSize = 12.5.sp)
                                Text("Best For: $bestFor", color = TextSecondary, fontSize = 10.sp)
                            }
                            if (isDressed) {
                                Surface(shape = CircleShape, color = SuccessGreen) {
                                    Icon(Icons.Default.Check, contentDescription = null, tint = ObsidianBg, modifier = Modifier.size(16.dp))
                                }
                            } else {
                                OutlinedButton(
                                    onClick = { viewModel.useGlassRimmer(dressing) },
                                    shape = RoundedCornerShape(8.dp),
                                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
                                ) {
                                    Text("Apply Rim", fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }

                if (barState.rimDressing != null) {
                    TextButton(
                        onClick = { viewModel.clearGlassRim() },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Clear Glass Rim", color = WarningOrange, fontSize = 11.sp)
                    }
                }
            }
        }
    }
}

// === STATION 7: GLASSWARE RACK ===
@Composable
private fun GlassRackStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianCard),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = "BAR GLASSWARE RACK & PHYSICAL SPECS",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Each glass dimension accurately calibrated: capacity (oz/ml), height (mm), and rim diameter (mm).",
                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                GlassType.values().forEach { glass ->
                    val isSelected = barState.selectedGlass == glass
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = if (isSelected) AmberGold.copy(alpha = 0.25f) else ObsidianSurface,
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(if (isSelected) AmberGold else ObsidianCard)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { viewModel.setGlassType(glass) }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(glass.displayName, fontWeight = FontWeight.Bold, color = if (isSelected) AmberGold else TextPrimary, fontSize = 12.sp)
                                Text("${glass.capacityOz} oz (${glass.capacityMl} ml) | H: ${glass.realHeightMm}mm | Rim: ${glass.realDiameterMm}mm", color = AmberGold, fontSize = 10.sp)
                                Text(glass.description, color = TextSecondary, fontSize = 9.5.sp)
                            }
                            if (isSelected) {
                                Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                                    Text("ACTIVE", modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp), style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Black, color = ObsidianBg, fontSize = 9.sp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ToolActionCard(
    icon: String,
    title: String,
    subtitle: String,
    actionLabel: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = ObsidianSurface,
        border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(ObsidianCard)),
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(icon, fontSize = 18.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(title, fontWeight = FontWeight.Bold, fontSize = 11.sp, color = TextPrimary, maxLines = 1)
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(subtitle, fontSize = 9.5.sp, color = TextSecondary, maxLines = 1)
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = onClick,
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(actionLabel, fontSize = 10.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun JiggerQuickBar(
    selectedOz: Float,
    onSelectOz: (Float) -> Unit
) {
    var showPrecisionSlider by remember { mutableStateOf(false) }

    Surface(
        shape = RoundedCornerShape(10.dp),
        color = ObsidianSurfaceVariant,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("📐", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Jigger (${selectedOz} oz):", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp))
                }
                TextButton(
                    onClick = { showPrecisionSlider = !showPrecisionSlider },
                    contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                    modifier = Modifier.height(24.dp)
                ) {
                    Text(if (showPrecisionSlider) "Hide Slider" else "0.1-2.0 oz Precision Slider", fontSize = 9.5.sp, color = AmberGold)
                }
            }

            val presets = listOf(
                0.1f to "⅒",
                0.25f to "¼",
                0.33f to "⅓",
                0.5f to "½",
                0.75f to "¾",
                1.0f to "1",
                1.25f to "1¼",
                1.5f to "1½",
                1.75f to "1¾",
                2.0f to "2"
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxWidth().padding(top = 2.dp)
            ) {
                items(presets) { (oz, label) ->
                    val isSelected = kotlin.math.abs(selectedOz - oz) < 0.04f
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (isSelected) AmberGold else ObsidianCard,
                        modifier = Modifier
                            .clickable { onSelectOz(oz) }
                            .padding(horizontal = 1.dp)
                    ) {
                        Text(
                            text = "$label oz",
                            modifier = Modifier.padding(horizontal = 5.dp, vertical = 3.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) ObsidianBg else TextPrimary,
                                fontSize = 9.5.sp
                            )
                        )
                    }
                }
            }

            if (showPrecisionSlider) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text("0.1", fontSize = 9.sp, color = TextMuted)
                    Slider(
                        value = selectedOz,
                        onValueChange = { raw ->
                            val stepped = (kotlin.math.round(raw * 10f) / 10f).coerceIn(0.1f, 2.0f)
                            onSelectOz(stepped)
                        },
                        valueRange = 0.1f..2.0f,
                        steps = 18,
                        colors = SliderDefaults.colors(thumbColor = AmberGold, activeTrackColor = AmberGold),
                        modifier = Modifier.weight(1f).padding(horizontal = 6.dp)
                    )
                    Text("2.0", fontSize = 9.sp, color = TextMuted)
                }
            }
        }
    }
}

// === STATION: CELLAR & TAPS (WINE & BEER) ===
@Composable
fun BeerAndWineStation(
    barState: com.example.viewmodel.VirtualBarUiState,
    viewModel: BarCraftViewModel
) {
    var subTab by remember { mutableStateOf(0) }
    var wineFilter by remember { mutableStateOf("All") }
    var beerFilter by remember { mutableStateOf("All") }

    val wineList = BarCraftData.wineList
    val beerList = BarCraftData.beerList

    val filteredWines = remember(wineFilter) {
        when (wineFilter) {
            "Whites" -> WineCatalog.whiteWines
            "Reds" -> WineCatalog.redWines
            "Cellar" -> WineCatalog.cellarWines
            else -> wineList
        }
    }

    val filteredBeers = remember(beerFilter) {
        when (beerFilter) {
            "American" -> BeerCatalog.americanBeers
            "Belgium" -> BeerCatalog.belgiumBeers
            "Germany" -> BeerCatalog.germanyBeers
            "UK" -> BeerCatalog.englandBeers + BeerCatalog.scotlandBeers
            "World" -> BeerCatalog.internationalBeers + BeerCatalog.hollandBeers + BeerCatalog.mexicoBeers
            "Alternatives" -> BeerCatalog.alternativeBeers
            else -> beerList
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                if (subTab == 0) "Cellar Wine Station (${wineList.size})" else "Taps & Beer Station (${beerList.size})",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
            )
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf("🍷 Fine Wine (${wineList.size})", "🍺 Craft Beer (${beerList.size})").forEachIndexed { index, title ->
                    val isSel = subTab == index
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = if (isSel) (if (index == 0) WineVelvet else AmberGold) else ObsidianCard,
                        modifier = Modifier.clickable { subTab = index }
                    ) {
                        Text(
                            title,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSel) (if (index == 0) TextPrimary else ObsidianBg) else TextSecondary,
                                fontSize = 10.sp
                            )
                        )
                    }
                }
            }
        }

        // Dedicated Pour Measurements: 4oz/8oz for Wine, 8oz/12oz for Beer
        if (subTab == 0) {
            // WINE POUR MEASUREMENTS: 4oz and 8oz
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🍷", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Wine Pour Measurements:",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(6.dp), color = WineVelvet) {
                            Text(
                                text = "${barState.selectedWinePourOz.toInt()} OZ ACTIVE POUR",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 9.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        listOf(
                            4.0f to "🍷 4 oz Standard Glass",
                            8.0f to "🍷 8 oz Large Pour / Carafe"
                        ).forEach { (oz, label) ->
                            val isSel = barState.selectedWinePourOz == oz
                            OutlinedButton(
                                onClick = { viewModel.setWinePourMeasure(oz) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSel) WineVelvet else ObsidianCard,
                                    contentColor = TextPrimary
                                ),
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 10.5.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // Wine Varietal Category Filters
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
                listOf(
                    "All" to "All (${wineList.size})",
                    "Whites" to "Whites (${WineCatalog.whiteWines.size})",
                    "Reds" to "Reds (${WineCatalog.redWines.size})",
                    "Cellar" to "Cellar (${WineCatalog.cellarWines.size})"
                ).forEach { (id, label) ->
                    val isSel = wineFilter == id
                    FilterChip(
                        selected = isSel,
                        onClick = { wineFilter = id },
                        label = { Text(label, fontSize = 10.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = WineVelvet.copy(alpha = 0.5f),
                            selectedLabelColor = TextPrimary
                        )
                    )
                }
            }
        } else {
            // BEER POUR MEASUREMENTS: 8oz and 12oz
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = ObsidianSurfaceVariant,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("🍺", fontSize = 14.sp)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                "Beer Pour Measurements:",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }
                        Surface(shape = RoundedCornerShape(6.dp), color = AmberGold) {
                            Text(
                                text = "${barState.selectedBeerPourOz.toInt()} OZ ACTIVE POUR",
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = ObsidianBg, fontSize = 9.sp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
                        listOf(
                            8.0f to "🍺 8 oz Half Pint / Goblet",
                            12.0f to "🍺 12 oz Full Bottle / Pint"
                        ).forEach { (oz, label) ->
                            val isSel = barState.selectedBeerPourOz == oz
                            OutlinedButton(
                                onClick = { viewModel.setBeerPourMeasure(oz) },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(8.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = if (isSel) AmberGold else ObsidianCard,
                                    contentColor = if (isSel) ObsidianBg else TextPrimary
                                ),
                                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 10.5.sp,
                                    fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }

            // Beer Regional Category Filters
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.fillMaxWidth()) {
                val beerFilterChips = listOf(
                    "All" to "All (${beerList.size})",
                    "American" to "American (${BeerCatalog.americanBeers.size})",
                    "Belgium" to "Belgium (${BeerCatalog.belgiumBeers.size})",
                    "Germany" to "Germany (${BeerCatalog.germanyBeers.size})",
                    "UK" to "UK & Scotland (${BeerCatalog.englandBeers.size + BeerCatalog.scotlandBeers.size})",
                    "World" to "World (${BeerCatalog.internationalBeers.size + BeerCatalog.hollandBeers.size + BeerCatalog.mexicoBeers.size})",
                    "Alternatives" to "Ciders & Malts (${BeerCatalog.alternativeBeers.size})"
                )
                items(beerFilterChips) { (id, label) ->
                    val isSel = beerFilter == id
                    FilterChip(
                        selected = isSel,
                        onClick = { beerFilter = id },
                        label = { Text(label, fontSize = 10.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = AmberGold.copy(alpha = 0.25f),
                            selectedLabelColor = AmberGold
                        )
                    )
                }
            }
        }

        val activeList = if (subTab == 0) filteredWines else filteredBeers
        val activePourOz = if (subTab == 0) barState.selectedWinePourOz else barState.selectedBeerPourOz

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 4.dp)
        ) {
            items(activeList, key = { it.id }) { ing ->
                val currentVol = barState.bottleDepletions[ing.id] ?: ing.maxCapacityOz
                val isLocked = !viewModel.isIngredientUnlocked(ing.id)
                InteractiveBottleCard(
                    ingredient = ing,
                    currentVolumeOz = currentVol,
                    isPouring = barState.isPouringId == ing.id,
                    isLocked = isLocked,
                    selectedPourOz = activePourOz,
                    onPourClicked = { amount -> viewModel.pourIngredient(ing, amount) }
                )
            }
        }

        // Quick glassware suggestion for wine/beer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { viewModel.setGlassType(GlassType.WINE) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("🍷 Use Wine Goblet (12 oz)", fontSize = 11.sp, maxLines = 1)
            }
            OutlinedButton(
                onClick = { viewModel.setGlassType(GlassType.BEER_PINT) },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                Text("🍺 Use Beer Pint (16 oz)", fontSize = 11.sp, maxLines = 1)
            }
        }
    }
}
