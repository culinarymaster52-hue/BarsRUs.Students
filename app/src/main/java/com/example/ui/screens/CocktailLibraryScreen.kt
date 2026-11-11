package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.model.CocktailRecipe
import com.example.ui.components.GlasswareCanvas
import com.example.ui.theme.*
import com.example.util.ShareHelper
import com.example.viewmodel.AppScreen
import com.example.viewmodel.BarCraftViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CocktailLibraryScreen(
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val libraryRecipes by viewModel.libraryRecipes.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf("All") }
    var selectedRecipeForDetail by remember { mutableStateOf<CocktailRecipe?>(null) }

    val categories = listOf(
        "All",
        "Favorites",
        "Custom",
        "Gin",
        "Whiskey",
        "Tequila",
        "Rum",
        "Vodka",
        "Apéritif",
        "Brandy",
        "Wine & Beer"
    )

    val filteredList = remember(libraryRecipes, searchQuery, selectedCategoryFilter) {
        libraryRecipes.filter { recipe ->
            val matchesQuery = searchQuery.isBlank() ||
                    recipe.name.contains(searchQuery, ignoreCase = true) ||
                    recipe.description.contains(searchQuery, ignoreCase = true) ||
                    recipe.garnish.contains(searchQuery, ignoreCase = true) ||
                    recipe.category.contains(searchQuery, ignoreCase = true) ||
                    recipe.ingredients.any { it.ingredient.name.contains(searchQuery, ignoreCase = true) }

            val matchesCategory = when (selectedCategoryFilter) {
                "All" -> true
                "Favorites" -> recipe.isFavorite
                "Custom" -> recipe.isCustom
                "Wine & Beer" -> recipe.category.equals("Wine & Beer", ignoreCase = true) || recipe.category.contains("Wine", ignoreCase = true)
                else -> recipe.category.contains(selectedCategoryFilter, ignoreCase = true)
            }

            matchesQuery && matchesCategory
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .padding(16.dp)
    ) {
        // Header with Room Database badge
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "COCKTAIL LIBRARY & FORMULAS",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGold,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "300 international recipes persisted in Room Database",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = ObsidianSurfaceVariant
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("⚡", fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${libraryRecipes.size} RECIPES",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = AmberGold,
                            fontSize = 10.sp
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text("Search 300 cocktails, spirits, or garnishes...", fontSize = 12.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = AmberGold) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { searchQuery = "" }) {
                        Icon(Icons.Default.Close, contentDescription = "Clear")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth().testTag("cocktail_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Category Filter Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(categories) { cat ->
                val isSelected = selectedCategoryFilter == cat
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = if (isSelected) AmberGold else ObsidianCard,
                    modifier = Modifier.clickable { selectedCategoryFilter = cat }
                ) {
                    Text(
                        text = if (cat == "Favorites") "❤️ Favorites" else cat,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) ObsidianBg else TextPrimary,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Count indicator
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Showing ${filteredList.size} of ${libraryRecipes.size} recipes",
                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 11.sp)
            )
            if (selectedCategoryFilter != "All" || searchQuery.isNotEmpty()) {
                Text(
                    text = "Reset Filters",
                    style = MaterialTheme.typography.labelSmall.copy(color = AmberGoldLight, fontWeight = FontWeight.Bold),
                    modifier = Modifier.clickable {
                        searchQuery = ""
                        selectedCategoryFilter = "All"
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Recipe List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(filteredList, key = { it.id }) { recipe ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = ObsidianCard,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedRecipeForDetail = recipe }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        // Glass icon preview
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(ObsidianSurfaceVariant, RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            GlasswareCanvas(
                                glassType = recipe.glass,
                                pouredIngredients = recipe.ingredients,
                                hasIce = recipe.iceRequired,
                                garnishName = recipe.garnish,
                                modifier = Modifier.size(45.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = recipe.name,
                                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                                )
                                if (recipe.isCustom) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = AmberGold
                                    ) {
                                        Text(
                                            text = "ORIGINAL",
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.ExtraBold, color = ObsidianBg, fontSize = 8.sp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = "${recipe.glass.displayName} • ${recipe.technique.actionName} • ${recipe.category}",
                                style = MaterialTheme.typography.bodySmall.copy(color = AmberGoldLight, fontSize = 11.sp)
                            )
                            val ingredientsSnippet = recipe.ingredients.joinToString(", ") { it.ingredient.name.split(" ").first() }
                            Text(
                                text = ingredientsSnippet,
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.sp),
                                maxLines = 1
                            )
                        }

                        // Favorite heart toggle button
                        IconButton(
                            onClick = { viewModel.toggleRecipeFavorite(recipe.id, recipe.isFavorite) },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = if (recipe.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                                contentDescription = if (recipe.isFavorite) "Remove from favorites" else "Add to favorites",
                                tint = if (recipe.isFavorite) RubyRed else TextMuted,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextMuted)
                    }
                }
            }
        }
    }

    // Detail Modal
    selectedRecipeForDetail?.let { recipe ->
        AlertDialog(
            onDismissRequest = { selectedRecipeForDetail = null },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(recipe.name, color = AmberGold, fontWeight = FontWeight.Bold, fontSize = 18.sp, modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { viewModel.toggleRecipeFavorite(recipe.id, recipe.isFavorite) },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = if (recipe.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = if (recipe.isFavorite) RubyRed else TextMuted
                        )
                    }
                }
            },
            text = {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(130.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        GlasswareCanvas(
                            glassType = recipe.glass,
                            pouredIngredients = recipe.ingredients,
                            hasIce = recipe.iceRequired,
                            garnishName = recipe.garnish,
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Vessel: ${recipe.glass.displayName} (${recipe.glass.capacityOz} oz / ${recipe.glass.capacityMl} ml)",
                        style = MaterialTheme.typography.labelSmall.copy(color = AmberGoldLight, fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "Method: ${recipe.technique.actionName} | Ice: ${if (recipe.iceRequired) "On the Rocks" else "Served Up"} | Category: ${recipe.category}",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    Text("MEASURED FORMULA:", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary))
                    Spacer(modifier = Modifier.height(4.dp))
                    recipe.ingredients.forEach { item ->
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                        ) {
                            Text("• ${item.ingredient.name}", style = MaterialTheme.typography.bodySmall.copy(color = TextPrimary, fontSize = 11.sp))
                            Text("${item.amountOz} ${item.ingredient.unit}", style = MaterialTheme.typography.bodySmall.copy(color = AmberGold, fontWeight = FontWeight.Bold, fontSize = 11.sp))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Garnish: ${recipe.garnish}", style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Profile: ${recipe.description}", style = MaterialTheme.typography.bodySmall.copy(color = TextMuted, fontSize = 10.sp))
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("History: ${recipe.historyNotes}", style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.sp))

                    Spacer(modifier = Modifier.height(14.dp))

                    // Action buttons: Practice in Bar, Export PDF, Share
                    Button(
                        onClick = {
                            viewModel.setGlassType(recipe.glass)
                            viewModel.navigateTo(AppScreen.VIRTUAL_BAR)
                            selectedRecipeForDetail = null
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen, contentColor = Color.White),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("🍸 Craft in Virtual Bar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(
                            onClick = { viewModel.exportAndShareRecipePdf(context, recipe) },
                            colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.PictureAsPdf, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Export PDF", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                        OutlinedButton(
                            onClick = { ShareHelper.shareRecipeToSocialMedia(context, recipe, recipe.creatorName ?: "Student Bartender") },
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Share", fontSize = 11.sp)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { selectedRecipeForDetail = null }) { Text("Close", color = AmberGold) }
            }
        )
    }
}
