package com.example.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cocktail_recipes")
data class CocktailEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val glassType: String,
    val iceRequired: Boolean,
    val technique: String,
    val ingredientsJson: String,
    val garnish: String,
    val description: String,
    val historyNotes: String,
    val isCustom: Boolean = false,
    val isFavorite: Boolean = false,
    val creatorName: String? = null,
    val creationTimestamp: Long = System.currentTimeMillis()
)
