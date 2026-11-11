package com.example.repository

import com.example.data.BarCraftData
import com.example.database.CocktailDao
import com.example.database.CocktailEntity
import com.example.model.*
import com.example.ui.theme.AmberGold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

class CocktailRepository(private val cocktailDao: CocktailDao) {

    val allCocktails: Flow<List<CocktailRecipe>> = cocktailDao.getAllCocktails().map { entities ->
        entities.map { it.toRecipe() }
    }

    val favoriteCocktails: Flow<List<CocktailRecipe>> = cocktailDao.getFavoriteCocktails().map { entities ->
        entities.map { it.toRecipe() }
    }

    val allCategories: Flow<List<String>> = cocktailDao.getAllCategories()

    fun getCocktailsByCategory(category: String): Flow<List<CocktailRecipe>> {
        return cocktailDao.getCocktailsByCategory(category).map { entities ->
            entities.map { it.toRecipe() }
        }
    }

    fun searchCocktails(query: String): Flow<List<CocktailRecipe>> {
        val trimmed = query.trim()
        return if (trimmed.isEmpty()) {
            allCocktails
        } else {
            cocktailDao.searchCocktails(trimmed).map { entities ->
                entities.map { it.toRecipe() }
            }
        }
    }

    suspend fun getCocktailById(id: String): CocktailRecipe? = withContext(Dispatchers.IO) {
        cocktailDao.getCocktailById(id)?.toRecipe()
    }

    suspend fun setFavorite(id: String, isFavorite: Boolean) = withContext(Dispatchers.IO) {
        cocktailDao.updateFavorite(id, isFavorite)
    }

    suspend fun insertCocktail(recipe: CocktailRecipe) = withContext(Dispatchers.IO) {
        cocktailDao.insertCocktail(recipe.toEntity())
    }

    suspend fun deleteCocktailById(id: String) = withContext(Dispatchers.IO) {
        cocktailDao.deleteCocktailById(id)
    }

    suspend fun getCocktailCount(): Int = withContext(Dispatchers.IO) {
        cocktailDao.getCocktailCount()
    }

    /**
     * Seeds the Room database on first launch if empty or updates to the full 300 cocktail catalog.
     */
    suspend fun ensurePopulated(catalogRecipes: List<CocktailRecipe>) = withContext(Dispatchers.IO) {
        val currentCount = cocktailDao.getCocktailCount()
        if (currentCount < catalogRecipes.size) {
            val entities = catalogRecipes.map { it.toEntity() }
            cocktailDao.insertAll(entities)
        }
    }

    companion object {
        fun CocktailEntity.toRecipe(): CocktailRecipe {
            val parsedGlass = try {
                GlassType.valueOf(glassType)
            } catch (_: Exception) {
                GlassType.ROCKS
            }

            val parsedTechnique = try {
                PrepTechnique.valueOf(technique)
            } catch (_: Exception) {
                PrepTechnique.SHAKEN
            }

            val measuredIngredients = mutableListOf<MeasuredIngredient>()
            if (ingredientsJson.isNotBlank()) {
                try {
                    val arr = JSONArray(ingredientsJson)
                    for (i in 0 until arr.length()) {
                        val obj = arr.getJSONObject(i)
                        val ingId = obj.optString("id", "")
                        val amountOz = obj.optDouble("amountOz", 1.0).toFloat()
                        val known = BarCraftData.getIngredient(ingId)
                        val finalIng = if (known.id == "unknown" && obj.has("name")) {
                            val name = obj.optString("name", "Ingredient")
                            val typeStr = obj.optString("type", IngredientType.SPIRIT.name)
                            val type = try {
                                IngredientType.valueOf(typeStr)
                            } catch (_: Exception) {
                                IngredientType.SPIRIT
                            }
                            val abv = obj.optDouble("abv", 40.0).toFloat()
                            Ingredient(
                                id = ingId.ifEmpty { "custom_$i" },
                                name = name,
                                type = type,
                                abv = abv,
                                color = AmberGold
                            )
                        } else {
                            known
                        }
                        measuredIngredients.add(MeasuredIngredient(finalIng, amountOz))
                    }
                } catch (_: Exception) {
                    // Fallback to empty ingredients on corrupted JSON
                }
            }

            return CocktailRecipe(
                id = id,
                name = name,
                category = category,
                glass = parsedGlass,
                iceRequired = iceRequired,
                technique = parsedTechnique,
                ingredients = measuredIngredients,
                garnish = garnish,
                description = description,
                historyNotes = historyNotes,
                isCustom = isCustom,
                isFavorite = isFavorite,
                creatorName = creatorName,
                creationTimestamp = creationTimestamp
            )
        }

        fun CocktailRecipe.toEntity(): CocktailEntity {
            val jsonArr = JSONArray()
            ingredients.forEach { m ->
                val obj = JSONObject()
                obj.put("id", m.ingredient.id)
                obj.put("name", m.ingredient.name)
                obj.put("type", m.ingredient.type.name)
                obj.put("abv", m.ingredient.abv.toDouble())
                obj.put("amountOz", m.amountOz.toDouble())
                jsonArr.put(obj)
            }

            return CocktailEntity(
                id = id,
                name = name,
                category = category,
                glassType = glass.name,
                iceRequired = iceRequired,
                technique = technique.name,
                ingredientsJson = jsonArr.toString(),
                garnish = garnish,
                description = description,
                historyNotes = historyNotes,
                isCustom = isCustom,
                isFavorite = isFavorite,
                creatorName = creatorName,
                creationTimestamp = creationTimestamp
            )
        }
    }
}
