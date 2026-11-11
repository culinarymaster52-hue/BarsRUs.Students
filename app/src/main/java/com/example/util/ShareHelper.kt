package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.example.model.CocktailRecipe

object ShareHelper {

    fun shareRecipeToSocialMedia(context: Context, recipe: CocktailRecipe, studentName: String) {
        val ingredientsText = recipe.ingredients.joinToString("\n") { 
            "• ${it.amountOz} ${it.ingredient.unit} ${it.ingredient.name}" 
        }
        val shareBody = """
            🍸 Check out this cocktail from Bars R Us Mixology Academy!
            
            Name: ${recipe.name}
            ${if (recipe.isCustom) "Original Creation by: $studentName ✨" else "Category: ${recipe.category}"}
            Glassware: ${recipe.glass.displayName} (${recipe.glass.capacityOz} oz)
            Technique: ${recipe.technique.actionName}
            
            Ingredients:
            $ingredientsText
            
            Garnish: ${recipe.garnish}
            
            Created & Mastered on Bars R Us Bartending Academy App 🍹 #BarsRUs #Mixology #CocktailRecipe #BartenderLife
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareBody)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share Cocktail Recipe to...")
        context.startActivity(shareIntent)
    }

    fun shareLeaderboardScore(context: Context, studentName: String, score: Int, rankTitle: String) {
        val shareBody = """
            🏆 I just scored $score points in Bars R Us Academy's Speed Rush!
            Current Rank: $rankTitle 🍹
            
            Can you mix cocktails faster and cleaner? Download Bars R Us and challenge my score! #BarsRUs #SpeedBartending #MixologyChallenge
        """.trimIndent()

        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareBody)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share High Score to...")
        context.startActivity(shareIntent)
    }

    fun openWebUrl(context: Context, url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    const val URL_SERVSAFE_ALCOHOL = "https://www.servsafe.com/ServSafe-Alcohol"
    const val URL_TIPS_CERTIFICATION = "https://www.gettips.com"
    const val URL_USPTO_TRADEMARK = "https://www.uspto.gov/trademarks"
    const val URL_COPYRIGHT_GOV = "https://www.copyright.gov"
}
