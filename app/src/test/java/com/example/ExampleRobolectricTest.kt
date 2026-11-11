package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.BarCraftData
import com.example.model.GlassType
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Bars R Us", appName)
  }

  @Test
  fun `verify classic cocktail database integrity`() {
    val cocktails = BarCraftData.classicCocktails
    assertEquals(300, cocktails.size)
    val oldFashioned = cocktails.find { it.id == "old_fashioned" }
    assertNotNull(oldFashioned)
    assertEquals(GlassType.ROCKS, oldFashioned?.glass)
    assertTrue(oldFashioned?.ingredients?.isNotEmpty() == true)
  }

  @Test
  fun `verify 300 cocktail recipes count and categories`() {
    val cocktails = com.example.data.CocktailCatalog.allCocktails
    assertEquals("Total cocktail count must be exactly 300", 300, cocktails.size)
    val categories = cocktails.map { it.category }.distinct()
    assertTrue(categories.contains("Gin"))
    assertTrue(categories.contains("Whiskey"))
    assertTrue(categories.contains("Tequila"))
    assertTrue(categories.contains("Rum"))
    assertTrue(categories.contains("Vodka"))
    assertTrue(categories.contains("Apéritif"))
    assertTrue(categories.contains("Brandy"))
    assertTrue(categories.contains("Wine & Beer"))
  }

  @Test
  fun `verify glassware physical specs`() {
    val highball = GlassType.HIGHBALL
    assertEquals(12.0f, highball.capacityOz)
    assertEquals(355, highball.capacityMl)
  }
}
