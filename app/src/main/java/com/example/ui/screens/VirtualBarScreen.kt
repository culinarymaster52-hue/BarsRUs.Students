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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarCraftData
import com.example.model.*
import com.example.ui.components.BehindTheBarWorkbench
import com.example.ui.components.GlasswareCanvas
import com.example.ui.components.InteractiveBottleCard
import com.example.ui.components.VirtualShakerCanvas
import com.example.ui.theme.*
import com.example.util.ShareHelper
import com.example.viewmodel.BarCraftViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VirtualBarScreen(
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val barState by viewModel.barState.collectAsState()
    var showClaimDialog by remember { mutableStateOf(false) }
    var showGlasswareSheet by remember { mutableStateOf(false) }
    var showCopyrightDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // App Header Banner
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "VIRTUAL BAR LAB",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGold,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "Craft, calibrate, and strain authentic recipes",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }

            // Glassware Selector Button
            OutlinedButton(
                onClick = { showGlasswareSheet = true },
                shape = RoundedCornerShape(10.dp),
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(AmberGold)),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = AmberGold),
                modifier = Modifier.testTag("glassware_picker_button")
            ) {
                Icon(Icons.Default.LocalBar, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = barState.selectedGlass.displayName.split(" ").first(), fontSize = 12.sp)
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Center Stage: Dual View (Virtual Shaker & Serving Glass)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface)
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                ) {
                    // Left: Boston Shaker Tin
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = if (barState.isStrainedToGlass) "SHAKER (EMPTY)" else "BOSTON SHAKER",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (barState.pouredInShaker.isNotEmpty()) AmberGold else TextMuted
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            VirtualShakerCanvas(
                                pouredIngredients = barState.pouredInShaker,
                                hasIce = barState.hasIce,
                                isShaking = barState.isShaking,
                                isStirring = barState.isStirring,
                                shakeProgress = barState.shakeProgress,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(130.dp)
                            )
                        }
                        val shakerOz = barState.pouredInShaker.sumOf { it.amountOz.toDouble() }.toFloat()
                        Text(
                            text = "${String.format("%.1f", shakerOz)} oz in shaker",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = TextSecondary)
                        )
                    }

                    // Center Divider with Strain Arrow
                    Box(
                        modifier = Modifier
                            .width(42.dp)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(
                            onClick = { viewModel.strainToServingGlass() },
                            enabled = barState.pouredInShaker.isNotEmpty(),
                            modifier = Modifier
                                .size(38.dp)
                                .background(
                                    if (barState.pouredInShaker.isNotEmpty()) AmberGold else ObsidianCard,
                                    CircleShape
                                )
                                .testTag("strain_button")
                        ) {
                            Icon(
                                Icons.Default.ArrowForward,
                                contentDescription = "Strain into Glass",
                                tint = if (barState.pouredInShaker.isNotEmpty()) ObsidianBg else TextMuted,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    // Right: Serving Glass
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                    ) {
                        Text(
                            text = barState.selectedGlass.displayName.uppercase(),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (barState.servedInGlass.isNotEmpty()) AmberGold else TextMuted
                            ),
                            maxLines = 1
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            GlasswareCanvas(
                                glassType = barState.selectedGlass,
                                pouredIngredients = barState.servedInGlass,
                                hasIce = barState.hasIce,
                                garnishName = barState.selectedGarnish,
                                isSmoked = barState.isSmoked || barState.isSmokingActive,
                                rimDressing = barState.rimDressing,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(130.dp)
                            )
                        }
                        val glassOz = barState.servedInGlass.sumOf { it.amountOz.toDouble() }.toFloat()
                        Text(
                            text = "${String.format("%.1f", glassOz)} / ${barState.selectedGlass.capacityOz} oz",
                            style = MaterialTheme.typography.bodySmall.copy(fontSize = 11.sp, color = TextSecondary)
                        )
                    }
                }

                // Pouring liquid stream animation overlay
                if (barState.isPouringId != null) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = 16.dp)
                            .background(AmberGold.copy(alpha = 0.9f), RoundedCornerShape(12.dp))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "⚡ Pouring ${BarCraftData.getIngredient(barState.isPouringId!!).name}...",
                            style = MaterialTheme.typography.labelMedium.copy(color = ObsidianBg, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Status Feedback Bar
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = ObsidianSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = AmberGold, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = barState.statusMessage,
                    style = MaterialTheme.typography.bodySmall.copy(color = TextPrimary, fontSize = 11.sp),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Shaker Controls Action Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Ice Toggle
            Button(
                onClick = { viewModel.toggleIce() },
                modifier = Modifier.weight(1f).testTag("ice_toggle_button"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (barState.hasIce) Color(0xFF244855) else ObsidianCard
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(if (barState.hasIce) "🧊 Ice (ON)" else "🧊 No Ice", fontSize = 11.sp)
            }

            // Shake Button
            Button(
                onClick = { viewModel.startShake() },
                enabled = barState.pouredInShaker.isNotEmpty() && !barState.isShaking,
                modifier = Modifier.weight(1f).testTag("shake_button"),
                colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(if (barState.isShaking) "Shaking..." else "⚡ Shake", fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }

            // Stir Button
            Button(
                onClick = { viewModel.startStir() },
                enabled = barState.pouredInShaker.isNotEmpty() && !barState.isStirring,
                modifier = Modifier.weight(1f).testTag("stir_button"),
                colors = ButtonDefaults.buttonColors(containerColor = CopperAccent, contentColor = ObsidianBg),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(if (barState.isStirring) "Stirring..." else "🥄 Stir", fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }

            // Reset
            OutlinedButton(
                onClick = { viewModel.resetBar() },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.testTag("reset_bar_button")
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Reset", modifier = Modifier.size(16.dp))
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Recipe Match / Novel Creation Card
        AnimatedVisibility(visible = barState.isStrainedToGlass) {
            Column {
                if (barState.detectedRecipeMatch != null) {
                    val match = barState.detectedRecipeMatch!!
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianCard)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "MATCH: ${match.name.uppercase()}",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                                )
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = if (barState.matchAccuracyPercent >= 85) SuccessGreen else WarningOrange
                                ) {
                                    Text(
                                        text = "${barState.matchAccuracyPercent}% ACCURACY",
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = ObsidianBg)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = match.description,
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = { viewModel.exportAndShareRecipePdf(context, match) },
                                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.PictureAsPdf, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Export PDF Card", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                                OutlinedButton(
                                    onClick = { ShareHelper.shareRecipeToSocialMedia(context, match, "Student Bartender") },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text("Share Recipe", fontSize = 11.sp)
                                }
                            }
                        }
                    }
                } else if (barState.isNovelCreation) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF26192E))
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.AutoAwesome, contentDescription = null, tint = AmberGold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "NOVEL COCKTAIL FORMULATION!",
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "This signature blend doesn't match any classic catalog cocktail. You have created a brand new drink! Claim it to formalize your intellectual property.",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Button(
                                    onClick = { showClaimDialog = true },
                                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                                    modifier = Modifier.weight(1f).testTag("claim_creation_button")
                                ) {
                                    Text("Claim Creation as My Own", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                                OutlinedButton(
                                    onClick = { showCopyrightDialog = true },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Text("Copyright & IP Info", fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // BEHIND-THE-BAR STATIONS & WORKBENCH
        BehindTheBarWorkbench(
            barState = barState,
            viewModel = viewModel
        )

        Spacer(modifier = Modifier.height(30.dp))
    }

    // Modal: Claim Custom Cocktail
    if (showClaimDialog) {
        var drinkName by remember { mutableStateOf("") }
        var authorName by remember { mutableStateOf("Alex Rivera") }
        var tastingNotes by remember { mutableStateOf("Rich botanical bouquet with crisp citrus finish.") }

        AlertDialog(
            onDismissRequest = { showClaimDialog = false },
            title = { Text("Claim Custom Cocktail Creation", color = AmberGold, fontWeight = FontWeight.Bold) },
            text = {
                Column {
                    Text(
                        "Register your original drink into the Bars R Us database with official authorship timestamp and formatted PDF export.",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = drinkName,
                        onValueChange = { drinkName = it },
                        label = { Text("Cocktail Name (e.g. Midnight Solstice)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth().testTag("custom_cocktail_name_input")
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = authorName,
                        onValueChange = { authorName = it },
                        label = { Text("Creator / Mixologist Name") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = tastingNotes,
                        onValueChange = { tastingNotes = it },
                        label = { Text("Tasting Profile & Notes") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.claimCustomCocktail(drinkName, authorName, tastingNotes, barState.selectedGarnish)
                        showClaimDialog = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg)
                ) {
                    Text("Register & Save Drink", fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showClaimDialog = false }) {
                    Text("Cancel", color = TextSecondary)
                }
            }
        )
    }

    // Modal: Glassware Specs Sheet
    if (showGlasswareSheet) {
        AlertDialog(
            onDismissRequest = { showGlasswareSheet = false },
            title = { Text("Real-World Glassware Specifications", color = AmberGold, fontWeight = FontWeight.Bold) },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    GlassType.values().forEach { glass ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (barState.selectedGlass == glass) ObsidianSurfaceVariant else Color.Transparent,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.setGlassType(glass)
                                    showGlasswareSheet = false
                                }
                                .padding(vertical = 4.dp, horizontal = 6.dp)
                        ) {
                            Column(modifier = Modifier.padding(6.dp)) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(glass.displayName, fontWeight = FontWeight.Bold, color = TextPrimary, fontSize = 13.sp)
                                    Text("${glass.capacityOz} oz (${glass.capacityMl} ml)", color = AmberGold, fontSize = 12.sp)
                                }
                                Text("Height: ${glass.realHeightMm}mm | Rim: ${glass.realDiameterMm}mm", color = TextSecondary, fontSize = 10.sp)
                                Text(glass.description, color = TextMuted, fontSize = 10.sp)
                            }
                        }
                        Divider(color = ObsidianCard, thickness = 0.5.dp)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showGlasswareSheet = false }) { Text("Close", color = AmberGold) }
            }
        )
    }

    // Modal: Copyright & Intellectual Property Guide
    if (showCopyrightDialog) {
        AlertDialog(
            onDismissRequest = { showCopyrightDialog = false },
            title = { Text("Cocktail Copyright & IP Protection", color = AmberGold, fontWeight = FontWeight.Bold) },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(
                        "Can you copyright a cocktail recipe?",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        "Under US Copyright Law (17 U.S.C. § 102), simple lists of ingredients are treated as functional formulas and cannot be copyrighted. However, you CAN legally protect your creations through:\n\n" +
                                "1. Trademark (USPTO): You can trademark your original cocktail name (e.g. Dark 'n Stormy is a registered trademark of Gosling's).\n\n" +
                                "2. Copyright on Creative Text & Formatted Cards: The creative literary description, story, illustration, and graphic layout on your exported PDF recipe card is protected by federal copyright.\n\n" +
                                "3. Trade Secret: Proprietary secret syrups, infusions, and bitters recipes (like Angostura or Chartreuse) are protected as trade secrets.",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_USPTO_TRADEMARK) },
                        colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Visit USPTO Trademark Search", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedButton(
                        onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_COPYRIGHT_GOV) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("US Copyright Office Guidance", fontSize = 11.sp)
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showCopyrightDialog = false }) { Text("Got It", color = AmberGold) }
            }
        )
    }
}
