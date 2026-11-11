package com.example.data

import com.example.model.*

object RumTikiCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "mojito",
            name = "Classic Cuban Mojito",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Slapped fresh mint bouquet, lime wheel, and raw cane sugar",
            description = "Cuba's national treasure: fresh spearmint gently pressed with raw cane sugar, tart lime, and white rum.",
            historyNotes = "Dating to 16th-century Havana as 'El Draque'; later beloved by Ernest Hemingway at La Bodeguita del Medio."
        ),
        CocktailRecipe(
            id = "daiquiri",
            name = "Classic Daiquiri",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f)
            ),
            garnish = "Lime wheel floating on top",
            description = "The gold standard of cocktail balance: crisp Cuban sugarcane rum, freshly pressed lime, and fine sugar.",
            historyNotes = "Invented around 1898 by American mining engineer Jennings Cox in the Cuban town of Daiquirí."
        ),
        CocktailRecipe(
            id = "hemingway_daiquiri",
            name = "Hemingway Daiquiri (Papa Doble)",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.5f)
            ),
            garnish = "Grapefruit twist & Luxardo cherry",
            description = "Crisp, bone-dry citrus sour favored by Ernest Hemingway at El Floridita bar in Havana.",
            historyNotes = "Crafted by Constante Ribalaigua Vert in Havana for Hemingway, who preferred it without sugar."
        ),
        CocktailRecipe(
            id = "pina_colada",
            name = "Traditional Piña Colada",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.BLENDED,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 3.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Fresh pineapple wedge, maraschino cherry & tiki paper umbrella",
            description = "Puerto Rico's official national libation: luscious coconut cream, sweet pineapple, and Caribbean rum.",
            historyNotes = "Created in 1954 by Ramón 'Monchito' Marrero at the Caribe Hilton in San Juan, Puerto Rico."
        ),
        CocktailRecipe(
            id = "mai_tai",
            name = "1944 Trader Vic's Mai Tai",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Spent lime shell half & fresh mint bouquet (symbolizing a tropical island & palm tree)",
            description = "The pinnacle of Tiki culture: aged Caribbean rums, toasted almond orgeat, orange curaçao, and lime.",
            historyNotes = "Invented in 1944 by Victor 'Trader Vic' Bergeron in Oakland, California; a guest exclaimed 'Mai Tai-Roa Aé!' (Out of this world!)."
        ),
        CocktailRecipe(
            id = "dark_n_stormy",
            name = "Dark 'n Stormy",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Spent lime wedge & floating dark rum storm cloud",
            description = "Visual spectacle of deep molasses rum floating like an ominous thunderhead over fiery ginger beer.",
            historyNotes = "Trademarked national cocktail of Bermuda, born in British Royal Navy officer clubs after WWI."
        ),
        CocktailRecipe(
            id = "hurricane",
            name = "New Orleans Hurricane",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Orange half-wheel & brandied cherry",
            description = "New Orleans Bourbon Street powerhouse bursting with tropical fruit punch and heavy rum rater.",
            historyNotes = "Invented at Pat O'Brien's bar in New Orleans during WWII to move excess rum stocks."
        ),
        CocktailRecipe(
            id = "zombie",
            name = "Original 1934 Zombie",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grapefruit_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("absinthe"), 0.05f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Mint sprig dusted with powdered sugar & cinnamon stick",
            description = "Potent Polynesian tiki icon blending multiple rums with spices and a drop of herbal absinthe.",
            historyNotes = "Formulated by Don the Beachcomber in 1934 Hollywood, with a famous limit of two per customer."
        ),
        CocktailRecipe(
            id = "painkiller",
            name = "Original British Virgin Islands Painkiller",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 4.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f)
            ),
            garnish = "Heavily freshly grated nutmeg over crushed ice & pineapple wedge",
            description = "Lush Caribbean cure-all combining Demerara rum with sweet citrus and fragrant grated nutmeg.",
            historyNotes = "Created in the 1970s at the Soggy Dollar Bar on Jost Van Dyke in the British Virgin Islands."
        ),
        CocktailRecipe(
            id = "jungle_bird",
            name = "The Jungle Bird",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("campari"), 0.75f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Pineapple fronds fanned out like exotic bird plumage",
            description = "Unforgettable crossover uniting rich Caribbean dark rum and tropical pineapple with bitter Italian Campari.",
            historyNotes = "Created in 1973 at the Aviary Bar in the Kuala Lumpur Hilton, Malaysia."
        ),
        CocktailRecipe(
            id = "navy_grog",
            name = "Navy Grog",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grapefruit_juice"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Hand-carved ice cone with drinking straw through center",
            description = "Stalwart Polynesian blend of dark Demerara rum, light rum, tart grapefruit, and honey.",
            historyNotes = "Created by Don the Beachcomber in 1941; famously Richard Nixon's favorite drink at Trader Vic's."
        ),
        CocktailRecipe(
            id = "rum_punch",
            name = "Classic Caribbean Rum Punch",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Grated nutmeg, orange wheel, lime slice & cocktail cherry",
            description = "Follows the classic rhyme: 'One of sour, two of sweet, three of strong, four of weak.'",
            historyNotes = "Centuries-old sailor punch tradition across Barbados, Jamaica, and the West Indies."
        ),
        CocktailRecipe(
            id = "cuba_libre",
            name = "Classic Cuba Libre",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("cola"), 4.0f)
            ),
            garnish = "Two spent lime wedges dropped into the glass",
            description = "More than just rum and cola: the freshly expressed lime juice is essential for authenticity.",
            historyNotes = "Invented in Havana around 1900 during the Spanish-American War celebrating Cuban liberation."
        ),
        CocktailRecipe(
            id = "el_presidente",
            name = "El Presidente",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.75f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Expressed orange peel twist & Luxardo cherry",
            description = "Aristocratic Cuban classic often called the 'Manhattan of Havana'.",
            historyNotes = "Created in Havana during 1920s American Prohibition in honor of President Gerardo Machado."
        ),
        CocktailRecipe(
            id = "cable_car",
            name = "The Cable Car",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("captain_morgan"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Cinnamon sugar crusted rim & lemon spiral",
            description = "Spiced rum Sidecar variant enriched with fragrant cinnamon sugar rim.",
            historyNotes = "Created in 1996 by Tony Abou-Ganim at the Starlight Room in San Francisco."
        ),
        CocktailRecipe(
            id = "planters_punch",
            name = "Planter's Punch",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Mint sprig & orange slice",
            description = "Jamaican pot-still dark rum punch packed with aromatic spices and citrus.",
            historyNotes = "Originated in Charleston, South Carolina and Jamaica in the late 19th century."
        ),
        CocktailRecipe(
            id = "mary_pickford",
            name = "Mary Pickford",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Luxardo brandied cherry",
            description = "Silky pink tropical sour created in Havana for 'America's Sweetheart'.",
            historyNotes = "Created in the 1920s by Fred Kaufman or Eddie Woelke at the Hotel Nacional in Cuba."
        ),
        CocktailRecipe(
            id = "fog_cutter",
            name = "Fog Cutter",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("hennessy"), 0.5f),
                MeasuredIngredient(getIng("gin"), 0.5f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("oloroso_sherry"), 0.5f)
            ),
            garnish = "Mint sprig with aromatic sherry floated on top",
            description = "Multifaceted tiki powerhouse combining rum, gin, and cognac topped with nutty Spanish sherry.",
            historyNotes = "Created by Victor Bergeron in the late 1940s at Trader Vic's."
        ),
        CocktailRecipe(
            id = "blue_hawaii",
            name = "Classic Blue Hawaii",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("vodka"), 1.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 3.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Pineapple wedge, cocktail cherry & paper umbrella",
            description = "Vibrant electric blue tropical highball capturing the pacific ocean surf.",
            historyNotes = "Created in 1957 by Harry Yee at the Kaiser Hawaiian Village Hotel in Waikiki."
        ),
        CocktailRecipe(
            id = "scorpion_bowl",
            name = "Scorpion Bowl",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("hennessy"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Edible orchid & long bamboo straws",
            description = "Communal Polynesian bowl cocktail delivering citrus and rich Caribbean spirits.",
            historyNotes = "Originated in Hawaii and immortalized by Trader Vic in the 1940s."
        ),
        CocktailRecipe(
            id = "saturn_cocktail",
            name = "The Saturn",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.25f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("white_rum"), 0.5f)
            ),
            garnish = "Round orange peel ring surrounding a cherry like Saturn's rings",
            description = "Award-winning Space-Age tropical drink that uniquely highlights gin and rum together.",
            historyNotes = "Created by J. 'Popo' Galsini in 1967, winning the International Bartender's Association competition."
        ),
        CocktailRecipe(
            id = "ti_punch",
            name = "Ti' Punch",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Lime disc peel pressed into the bottom of the glass",
            description = "Martinique & Guadeloupe national ritual: fresh sugarcane juice rum stirred with lime and cane syrup.",
            historyNotes = "French Caribbean tradition: 'Chacun prépare sa propre mort' (Each prepares their own death/portion)."
        ),
        CocktailRecipe(
            id = "caipirinha",
            name = "Classic Brazilian Caipirinha",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("cachaca_51"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f)
            ),
            garnish = "Muddled lime wedges & raw sugar grains in the glass",
            description = "Brazil's national cocktail: fresh whole lime wedges muddled with sugar and grassy cachaça.",
            historyNotes = "Born in the São Paulo countryside around 1918 as a traditional folk remedy."
        ),
        CocktailRecipe(
            id = "airmail_cocktail",
            name = "The Airmail",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("prosecco"), 2.0f)
            ),
            garnish = "Mint sprig with postal stamp motif on the rim",
            description = "Sophisticated effervescent rum sour topped with crisp Italian sparkling wine.",
            historyNotes = "Appeared in Bacardi promotional booklets in 1930 commemorating airmail postal routes."
        ),
        CocktailRecipe(
            id = "coquito",
            name = "Puerto Rican Coquito",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Cinnamon stick & freshly ground nutmeg",
            description = "Puerto Rico's beloved holiday coconut eggnog with spiced rum and creamy warmth.",
            historyNotes = "Cherished Caribbean holiday tradition passed down through generations."
        ),
        CocktailRecipe(
            id = "hotel_nacional",
            name = "Hotel Nacional Special",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Lime wheel",
            description = "Silky frothy Cuban sour showcasing fresh pineapple and white rum.",
            historyNotes = "Created in the 1930s by Wil P. Taylor at Havana's illustrious Hotel Nacional."
        ),
        CocktailRecipe(
            id = "knickerbocker",
            name = "Knickerbocker Cocktail",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Berries & lemon slice",
            description = "19th-century rum sour boasting rich berry and orange notes over aged rum.",
            historyNotes = "Recorded in Jerry Thomas's 1862 How to Mix Drinks."
        ),
        CocktailRecipe(
            id = "queens_park_swizzle",
            name = "Queen's Park Swizzle",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.2f)
            ),
            garnish = "Swizzled crushed ice with red crown of Angostura bitters & mint bouquet",
            description = "Trinidad's premier libation: aged rum swizzled with mint and crowned with aromatic bitters.",
            historyNotes = "Created in the 1920s at the Queen's Park Hotel in Port of Spain, Trinidad."
        ),
        CocktailRecipe(
            id = "bermuda_swizzle",
            name = "Bermuda Rum Swizzle",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("white_rum"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 2.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Orange slice, cherry, and fresh mint over crushed ice",
            description = "Bermuda's national drink crafted using a multi-pronged wooden swizzle stick.",
            historyNotes = "Served since the 1930s at the Swizzle Inn in Bailey's Bay, Bermuda: 'Swizzle Inn, Swagger Out'."
        ),
        CocktailRecipe(
            id = "corn_n_oil",
            name = "Corn 'n' Oil",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Spent lime half & dark rum layer over crushed ice",
            description = "Barbados specialty where viscous dark rum floats like oil over spiced citrus.",
            historyNotes = "A staple of Barbadian rum shops for over a century."
        ),
        CocktailRecipe(
            id = "fish_house_punch",
            name = "Philadelphia Fish House Punch",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("hennessy"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Lemon slice & peach wedge",
            description = "America's oldest documented cocktail punch: robust dark rum, French cognac, and citrus.",
            historyNotes = "Created in 1732 by the State in Schuylkill fishing club in Pennsylvania; favored by George Washington."
        ),
        CocktailRecipe(
            id = "hot_buttered_rum",
            name = "Classic Hot Buttered Rum",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.5f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Pats of sweet cream butter, cinnamon stick & ground nutmeg",
            description = "Colonial warming winter drink combining aged dark rum with spiced butter and steaming water.",
            historyNotes = "Popular in New England dating back to the 1650s."
        ),
        CocktailRecipe(
            id = "brass_monkey",
            name = "Brass Monkey",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("vodka"), 1.0f),
                MeasuredIngredient(getIng("orange_juice"), 4.0f)
            ),
            garnish = "Orange slice",
            description = "Simple, drinkable 1970s and 80s citrus highball made world-famous by the Beastie Boys.",
            historyNotes = "Popularized by Heublein premixed spirits in the 1970s and 1980s hip-hop culture."
        ),
        CocktailRecipe(
            id = "yellow_bird",
            name = "Yellow Bird",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Banana leaf or lime twist",
            description = "Sunny Caribbean sour named after the popular calypso folk song.",
            historyNotes = "Developed in the West Indies in the 1960s."
        ),
        CocktailRecipe(
            id = "cuban_manhattan",
            name = "Cuban Manhattan",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Cocktail cherry & flamed orange peel",
            description = "The Manhattan reimagined with aged Caribbean dark rum in place of American whiskey.",
            historyNotes = "Havana hotel bar classic from the golden age of Cuban cocktails."
        ),
        CocktailRecipe(
            id = "barracuda_cocktail",
            name = "The Barracuda",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("prosecco"), 2.0f)
            ),
            garnish = "Pineapple spear & cherry",
            description = "IBA modern sparkling tropical cocktail combining dark rum, pineapple, and prosecco.",
            historyNotes = "Invented by Benito Cuppari in Genoa, Italy in the late 1960s."
        ),
        CocktailRecipe(
            id = "between_the_sheets",
            name = "Between the Sheets",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("hennessy"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f)
            ),
            garnish = "Lemon twist & flamed orange oil",
            description = "Prohibition-era Sidecar variation featuring equal parts white rum, French cognac, and Cointreau.",
            historyNotes = "Created in the 1930s by Harry MacElhone at Harry's New York Bar in Paris."
        ),
        CocktailRecipe(
            id = "royal_bermuda_yacht_club",
            name = "Royal Bermuda Yacht Club",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Lime wheel",
            description = "Bermuda's sophisticated maritime sour with rich spiced notes.",
            historyNotes = "Formulated at the prestigious Royal Bermuda Yacht Club in Hamilton."
        ),
        CocktailRecipe(
            id = "sharks_tooth",
            name = "Shark's Tooth",
            category = "Rum",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("pineapple_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Carved shark tooth lime wedge",
            description = "Don the Beachcomber tropical refresher pairing layered rums with carbonation.",
            historyNotes = "Created in 1937 at Don the Beachcomber in Hollywood."
        ),
        CocktailRecipe(
            id = "ancient_mariner",
            name = "The Ancient Mariner",
            category = "Rum",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.05f)
            ),
            garnish = "Mint sprig & lime wheel",
            description = "Tiki tribute to Samuel Taylor Coleridge's epic poem with citrus and allspice.",
            historyNotes = "Created in 1994 by Jeff 'Beachbum' Berry."
        ),
        CocktailRecipe(
            id = "rum_runner",
            name = "Florida Keys Rum Runner",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.BLENDED,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 2.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Orange wedge & cocktail cherry with dark rum float",
            description = "Islamorada holiday drink blending fruit juices with light and dark rums over ice.",
            historyNotes = "Created in the late 1950s at the Holiday Isle Tiki Bar in Islamorada, Florida."
        ),
        CocktailRecipe(
            id = "banana_daiquiri",
            name = "Tropical Banana Daiquiri",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Caramelized banana chip & lime wheel",
            description = "Rich tropical daiquiri with banana aromatics and crisp lime.",
            historyNotes = "Popular Caribbean resort cocktail since the 1960s."
        ),
        CocktailRecipe(
            id = "strawberry_daiquiri",
            name = "Strawberry Daiquiri",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("white_rum"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Fresh sliced strawberry & lime wheel",
            description = "Bright crimson berry cocktail highlighting sweet summer strawberries and white rum.",
            historyNotes = "1970s bar phenomenon popularized across global tropical destinations."
        ),
        CocktailRecipe(
            id = "tradewinds",
            name = "Tradewinds",
            category = "Rum",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.0f),
                MeasuredIngredient(getIng("white_rum"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Fresh mint bouquet & nutmeg dusting",
            description = "Lush, creamy tiki potion pairing coconut, apricot, and dual rums.",
            historyNotes = "Created around 1970, recorded by Martin Cate in Smuggler's Cove."
        ),
        CocktailRecipe(
            id = "three_dots_and_a_dash",
            name = "Three Dots and a Dash",
            category = "Rum",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("dark_rum"), 1.5f),
                MeasuredIngredient(getIng("white_rum"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 0.5f),
                MeasuredIngredient(getIng("honey_syrup"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Three maraschino cherries and a pineapple spear (Morse code for 'V' for Victory: ••• —)",
            description = "Legendary WWII Polynesian victory cocktail loaded with Demerara rum, spices, and honey.",
            historyNotes = "Created by Don the Beachcomber to celebrate the Allied victory in WWII."
        )
    )
}
