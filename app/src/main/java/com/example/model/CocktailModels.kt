package com.example.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.*

enum class IngredientType(val displayName: String) {
    SPIRIT("Spirits & Liqueurs"),
    APERITIF("Apéritifs & Vermouths"),
    BITTERS("Aromatic Bitters"),
    WINE("Fine Wine & Champagne"),
    BEER("Craft Beer & Cider"),
    MIXER("Mixers & Juices"),
    GARNISH("Garnishes"),
    ICE("Ice & Cooling")
}

data class Ingredient(
    val id: String,
    val name: String,
    val type: IngredientType,
    val abv: Float, // % alcohol by volume
    val color: Color,
    val standardPourOz: Float = 1.0f,
    val unit: String = "oz",
    val flavorNote: String = "",
    val maxCapacityOz: Float = 25.4f // 750ml standard bottle = ~25.4 oz
)

enum class GlassType(
    val displayName: String,
    val capacityOz: Float,
    val capacityMl: Int,
    val realHeightMm: Int,
    val realDiameterMm: Int,
    val description: String
) {
    HIGHBALL(
        displayName = "Highball / Collins",
        capacityOz = 12.0f,
        capacityMl = 355,
        realHeightMm = 155,
        realDiameterMm = 65,
        description = "Tall cylindrical glass ideal for effervescent cocktails with ample ice."
    ),
    COUPE(
        displayName = "Coupe Glass",
        capacityOz = 6.0f,
        capacityMl = 180,
        realHeightMm = 135,
        realDiameterMm = 90,
        description = "Shallow saucer bowl on an elegant stem for shaken or stirred drinks served 'up'."
    ),
    MARTINI(
        displayName = "Martini Glass",
        capacityOz = 7.0f,
        capacityMl = 200,
        realHeightMm = 150,
        realDiameterMm = 100,
        description = "Conical V-shaped stemware preventing hand warmth and aerating aromas."
    ),
    ROCKS(
        displayName = "Old Fashioned / Rocks",
        capacityOz = 10.0f,
        capacityMl = 300,
        realHeightMm = 95,
        realDiameterMm = 80,
        description = "Short tumbler with thick solid glass base for muddling and large ice."
    ),
    MARGARITA(
        displayName = "Margarita Glass",
        capacityOz = 12.0f,
        capacityMl = 350,
        realHeightMm = 160,
        realDiameterMm = 110,
        description = "Stepped-diameter bowl with wide salted rim tailored for agave cocktails."
    ),
    COPPER_MUG(
        displayName = "Moscow Mule Copper Mug",
        capacityOz = 14.0f,
        capacityMl = 415,
        realHeightMm = 100,
        realDiameterMm = 85,
        description = "Conductive copper vessel keeping carbonation brisk and frosty."
    ),
    HURRICANE(
        displayName = "Hurricane Glass",
        capacityOz = 15.0f,
        capacityMl = 440,
        realHeightMm = 175,
        realDiameterMm = 80,
        description = "Curved, lamp-shaped silhouette for exotic tropical & tiki libations."
    ),
    SHOT(
        displayName = "Standard Shot Glass",
        capacityOz = 1.5f,
        capacityMl = 44,
        realHeightMm = 60,
        realDiameterMm = 45,
        description = "Heavy-base glass for quick straight spirits or neat tasting."
    ),
    WINE(
        displayName = "Wine Goblet",
        capacityOz = 12.0f,
        capacityMl = 355,
        realHeightMm = 180,
        realDiameterMm = 75,
        description = "Stemmed tulip glass for Spritz, Sangria, or vermouth aperitifs."
    ),
    BEER_PINT(
        displayName = "American Pint Glass",
        capacityOz = 16.0f,
        capacityMl = 473,
        realHeightMm = 150,
        realDiameterMm = 88,
        description = "Standard conical pint glass for lagers, ales, and boilermakers."
    )
}

enum class PrepTechnique(val actionName: String, val toolDescription: String) {
    SHAKEN("Shaken", "Vigorously aerated in a 2-piece Boston shaker with ice"),
    STIRRED("Stirred", "Gently stirred in a mixing glass with a barspoon for silky clarity"),
    BUILT("Built in Glass", "Constructed directly over ice in the serving glass"),
    BLENDED("Blended", "Blitzed with crushed ice into a slush consistency")
}

data class MeasuredIngredient(
    val ingredient: Ingredient,
    val amountOz: Float
)

data class CocktailRecipe(
    val id: String,
    val name: String,
    val category: String, // IBA Unforgettables, Contemporary Classics, Modern, Custom
    val glass: GlassType,
    val iceRequired: Boolean,
    val technique: PrepTechnique,
    val ingredients: List<MeasuredIngredient>,
    val garnish: String,
    val description: String,
    val historyNotes: String,
    val isCustom: Boolean = false,
    val isFavorite: Boolean = false,
    val creatorName: String? = null,
    val creationTimestamp: Long = System.currentTimeMillis()
)

enum class BarStation(val displayName: String, val iconEmoji: String) {
    SPEED_RAIL("Spirits & Rail", "🍾"),
    TOOLS("Tools & Jigger", "🛠️"),
    MIXERS("Mixers & Juices", "🧃"),
    GARNISH_BOARD("Garnish Board", "🍋"),
    ICE_WELL("Ice Well & Tongs", "🧊"),
    RIMMER("3-Tier Rimmer", "🧂"),
    BEER_AND_WINE("Cellar & Taps", "🍷"),
    GLASS_RACK("Glassware Rack", "🍸")
}

enum class BarTool(
    val id: String,
    val displayName: String,
    val iconEmoji: String,
    val category: String,
    val actionLabel: String,
    val description: String
) {
    JIGGER(
        "jigger",
        "Japanese Precision Jigger",
        "📐",
        "Measurement",
        "Set Measure",
        "Double-ended stainless jigger with 0.5, 0.75, 1.0, 1.5, and 2.0 oz steps for laboratory-precise liquid volume calibration."
    ),
    BAR_SPOON(
        "bar_spoon",
        "Twisted Bar Spoon",
        "🥄",
        "Stirring",
        "Stir Shaker",
        "30cm spiral stem tear-drop bar spoon designed for laminar circular stirring without chipping ice or clouding clear spirituous libations."
    ),
    HAWTHORNE(
        "hawthorne",
        "Hawthorne Strainer",
        "🌀",
        "Straining",
        "Equip Strainer",
        "Flexible wire spring coil snugly gripping shaker rims to trap crushed ice chips and citrus pulp during pouring."
    ),
    JULEP(
        "julep",
        "Julep Strainer",
        "🥏",
        "Straining",
        "Equip Strainer",
        "Perforated scalloped bowl strainer tailored for mixing glasses to deliver velvety, un-aerated stirred cocktails."
    ),
    FINE_MESH(
        "fine_mesh",
        "Fine Mesh Conical Strainer",
        "🕸️",
        "Straining",
        "Double-Strain",
        "Held directly under the primary strainer for double-straining drinks 'straight up' into stemware with zero ice shards."
    ),
    MUDDLER(
        "muddler",
        "Hardwood Muddler",
        "🪵",
        "Prep",
        "Muddle Ingredients",
        "Textured hardwood teeth to gently press fresh herbs, release mint essential oils, bruise citrus peels, and crush cane sugar."
    ),
    PARING_KNIFE(
        "knife",
        "Paring Knife & Peeler",
        "🔪",
        "Garnish",
        "Express Peel / Slice",
        "German steel blade for paring citrus peels, carving lime wheels, trimming cocktail spears, and expressing citrus essential oils."
    ),
    ICE_TONGS(
        "tongs",
        "Sanitary Ice Tongs",
        "🥢",
        "Sanitary",
        "Grip & Add Ice",
        "Hygienic stainless tongs ensuring zero direct hand contact with ice cubes per ServSafe food safety mandates."
    ),
    GLASS_RIMMER(
        "rimmer",
        "3-Tier Rotating Rimmer",
        "🧂",
        "Rim Dressing",
        "Dress Glass Rim",
        "Fold-out triple tier station containing lime juice moistening sponge, flaky sea salt, and raw cane sugar."
    ),
    CITRUS_SQUEEZER(
        "squeezer",
        "Citrus Elbow Press",
        "🍋",
        "Extraction",
        "Press Fresh Juice",
        "Cast-metal lever press delivering cold-pressed fresh lime and lemon juice directly into your jigger."
    ),
    FLAME_TORCH(
        "torch",
        "Culinary Flame Torch",
        "🔥",
        "Aroma",
        "Flame Citrus Oil",
        "Butane torch to roast cinnamon sticks, smoke rosemary sprigs, and toast expressed orange oils across the surface."
    ),
    COCKTAIL_SMOKER(
        "smoker",
        "Cocktail Smoke Chimney & Cloche",
        "💨",
        "Infusion",
        "Smoke Cocktail",
        "Hardwood chimney with charred oak & cherrywood chips ignited by torch to infuse rich aromatic smoke into Old Fashioneds."
    ),
    BAR_TOWEL(
        "towel",
        "Sanitary Bar Towel",
        "🧻",
        "Sanitation",
        "Wipe & Sanitize",
        "Quat-sanitizer dampened microfiber towel to wipe condensation rings, polish glassware, and sanitize work surfaces."
    )
}

