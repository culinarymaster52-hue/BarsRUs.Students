package com.example.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {
    @Query("SELECT * FROM cocktail_recipes ORDER BY name ASC")
    fun getAllCocktails(): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail_recipes WHERE id = :id")
    suspend fun getCocktailById(id: String): CocktailEntity?

    @Query("SELECT * FROM cocktail_recipes WHERE category = :category ORDER BY name ASC")
    fun getCocktailsByCategory(category: String): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail_recipes WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteCocktails(): Flow<List<CocktailEntity>>

    @Query("SELECT * FROM cocktail_recipes WHERE name LIKE '%' || :query || '%' OR ingredientsJson LIKE '%' || :query || '%' OR category LIKE '%' || :query || '%' ORDER BY name ASC")
    fun searchCocktails(query: String): Flow<List<CocktailEntity>>

    @Query("SELECT DISTINCT category FROM cocktail_recipes ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>

    @Query("SELECT COUNT(*) FROM cocktail_recipes")
    suspend fun getCocktailCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCocktail(cocktail: CocktailEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cocktails: List<CocktailEntity>)

    @Update
    suspend fun updateCocktail(cocktail: CocktailEntity)

    @Query("UPDATE cocktail_recipes SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavorite(id: String, isFavorite: Boolean)

    @Query("DELETE FROM cocktail_recipes WHERE id = :id")
    suspend fun deleteCocktailById(id: String)
}
