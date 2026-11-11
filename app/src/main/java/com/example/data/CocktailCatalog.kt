package com.example.data

import com.example.model.*

object CocktailCatalog {

    val ginCocktails: List<CocktailRecipe> get() = GinCocktails.list
    val whiskeyCocktails: List<CocktailRecipe> get() = WhiskeyCocktails.list
    val tequilaCocktails: List<CocktailRecipe> get() = TequilaMezcalCocktails.list
    val rumCocktails: List<CocktailRecipe> get() = RumTikiCocktails.list
    val vodkaCocktails: List<CocktailRecipe> get() = VodkaCocktails.list
    val aperitifCocktails: List<CocktailRecipe> get() = AperitifSpritzCocktails.list
    val brandyCocktails: List<CocktailRecipe> get() = BrandyCognacCocktails.list
    val wineBeerCocktails: List<CocktailRecipe> get() = WineBeerLowAbvCocktails.list

    /**
     * Master catalog of 300 classic, contemporary, and international cocktails
     * sourced from A Couple Cooks (https://www.acouplecooks.com/cocktail-names-list/)
     * and historical cocktail archives.
     */
    val allCocktails: List<CocktailRecipe> by lazy {
        ginCocktails +
                whiskeyCocktails +
                tequilaCocktails +
                rumCocktails +
                vodkaCocktails +
                aperitifCocktails +
                brandyCocktails +
                wineBeerCocktails
    }

    val totalRecipeCount: Int get() = allCocktails.size

    val allCategories: List<String> = listOf(
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

    fun findById(id: String): CocktailRecipe? = allCocktails.find { it.id == id }

    fun filterByCategory(category: String): List<CocktailRecipe> {
        return if (category == "All") {
            allCocktails
        } else {
            allCocktails.filter { it.category.equals(category, ignoreCase = true) }
        }
    }
}
