package com.example.data

import com.example.model.*

object TequilaMezcalCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "margarita",
            name = "Classic Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f)
            ),
            garnish = "Half flaky sea salt rim & fresh lime wheel",
            description = "The world's most beloved agave cocktail: vibrant blue agave tequila, orange liqueur, and tart lime.",
            historyNotes = "Popularized in 1930s-40s Baja California, Mexico, named after the Spanish word for Daisy."
        ),
        CocktailRecipe(
            id = "tommys_margarita",
            name = "Tommy's Margarita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f)
            ),
            garnish = "Salt rim & lime wedge",
            description = "Pure agave expression replacing orange liqueur with organic Mexican blue agave nectar.",
            historyNotes = "Invented by Julio Bermejo in the early 1990s at Tommy's Mexican Restaurant in San Francisco."
        ),
        CocktailRecipe(
            id = "mezcal_margarita",
            name = "Smoky Mezcal Margarita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f)
            ),
            garnish = "Tajín chili-lime salt rim & charred lime wheel",
            description = "Earthy roasted agave smoke meeting citrus zest and piquant chili-lime rim dressing.",
            historyNotes = "Modern Oaxaca-inspired evolution bringing ancient earthen pit distillation to cocktail bars."
        ),
        CocktailRecipe(
            id = "paloma",
            name = "Classic Paloma",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Pink Himalayan sea salt rim & fresh grapefruit wedge",
            description = "Mexico's national favorite: sparkling pink grapefruit, zesty lime, and clean blanco tequila.",
            historyNotes = "Said to be created by Don Javier Delgado Corona, owner of La Capilla in Tequila, Mexico."
        ),
        CocktailRecipe(
            id = "spicy_margarita",
            name = "Spicy Jalapeño Margarita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f)
            ),
            garnish = "Chili powder / Tajín rim & two fresh jalapeño wheels",
            description = "Fiery capsicum kick mellowed by sweet agave nectar and cold tart lime juice.",
            historyNotes = "The quintessential 21st-century modern craft cocktail bar favorite."
        ),
        CocktailRecipe(
            id = "oaxaca_old_fashioned",
            name = "Oaxaca Old Fashioned",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("mezcal"), 0.5f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Flamed orange peel expressing oils across clear ice block",
            description = "Masterful Mexican spin on the Old Fashioned pairing reposado tequila with smoky artisanal mezcal.",
            historyNotes = "Created in 2007 by Phil Ward at Death & Co in New York City."
        ),
        CocktailRecipe(
            id = "smoked_oaxaca_old_fashioned",
            name = "Smoked Oaxaca Old Fashioned",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("mezcal"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.1f)
            ),
            garnish = "Cloche smoked with charred cherrywood chips & flamed orange twist",
            description = "Billowing charred cherrywood smoke elevates the roasted agave and caramel tequila notes.",
            historyNotes = "Sensory bar craft presentation popular across international mixology competitions."
        ),
        CocktailRecipe(
            id = "tequila_sunrise",
            name = "Tequila Sunrise",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 4.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Orange wheel & Maraschino cherry with sunset gradient",
            description = "Visual sunset gradient with dense red pomegranate grenadine sinking beneath golden orange juice.",
            historyNotes = "Invented in Sausalito, California at the Trident bar in the early 1970s; Mick Jagger's tour favorite."
        ),
        CocktailRecipe(
            id = "mexican_mule",
            name = "Mexican Mule",
            category = "Tequila",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f)
            ),
            garnish = "Lime wheel, jalapeño slice & fresh mint in copper mug",
            description = "Crisp, spicy highball pairing Jamaican ginger beer with earthy blue agave and lime.",
            historyNotes = "Popularized across North American craft bars as a vibrant agave alternative to the Moscow Mule."
        ),
        CocktailRecipe(
            id = "naked_and_famous",
            name = "Naked and Famous",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 0.75f),
                MeasuredIngredient(getIng("aperol"), 0.75f),
                MeasuredIngredient(getIng("suze"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f)
            ),
            garnish = "Thin expressed lime wheel",
            description = "Equal parts modern genius: smoky mezcal, bittersweet Italian Aperol, herbal liqueur, and tart lime.",
            historyNotes = "Created in 2011 by Joaquín Simó at Death & Co in New York City."
        ),
        CocktailRecipe(
            id = "division_bell",
            name = "Division Bell",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 1.0f),
                MeasuredIngredient(getIng("aperol"), 0.75f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f)
            ),
            garnish = "Grapefruit peel",
            description = "Smoky agave balanced with bittersweet orange Aperol, maraschino cherry, and lime.",
            historyNotes = "Created in 2009 by Phil Ward at Mayahuel in New York, named after the Pink Floyd album."
        ),
        CocktailRecipe(
            id = "siesta_cocktail",
            name = "The Siesta",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("campari"), 0.5f),
                MeasuredIngredient(getIng("grapefruit_juice"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Grapefruit twist",
            description = "Brilliant pink sour matching grassy tequila with bitter Campari and ruby grapefruit.",
            historyNotes = "Created in 2006 by Katie Stipe at the Flatiron Lounge in New York City."
        ),
        CocktailRecipe(
            id = "cantarito",
            name = "El Cantarito",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Chili-salt rim & trio of citrus wedges in a traditional clay cup",
            description = "Jalisco street-fair favorite blending freshly squeezed citrus trio, tequila, and sea salt.",
            historyNotes = "Traditional roadside refresher served in earthenware clay jugs across Amatitán and Tequila."
        ),
        CocktailRecipe(
            id = "batanga",
            name = "La Batanga",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("cola"), 4.0f)
            ),
            garnish = "Heavy coarse sea salt rim & lime wedge stirred with a big wooden knife",
            description = "Tequila, cola, and fresh lime transformed by a heavily salted rim and wooden knife stirring.",
            historyNotes = "Created in 1961 by Don Javier Delgado Corona at La Capilla in Tequila, Mexico."
        ),
        CocktailRecipe(
            id = "rosita",
            name = "The Rosita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.5f),
                MeasuredIngredient(getIng("campari"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Flame expressed grapefruit peel",
            description = "Complex agave Negroni using both sweet and dry vermouth with Campari and reposado tequila.",
            historyNotes = "Appeared in Mr. Boston's Official Bartender's Guide in 1988; restored to prominence by Gary Regan."
        ),
        CocktailRecipe(
            id = "matador_cocktail",
            name = "The Matador",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f)
            ),
            garnish = "Pineapple leaf & lime wheel",
            description = "Fruity, tangy tropical sour pairing peppery blanco tequila with frothy sweet pineapple.",
            historyNotes = "Popular 1970s Mexican resort classic celebrated across Acapulco."
        ),
        CocktailRecipe(
            id = "tequila_sour",
            name = "Classic Tequila Sour",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Bitters swirl on creamy foam with lime wheel",
            description = "Silky citrus sour highlighting the herbal and citrus aromatics of blue agave.",
            historyNotes = "Early 20th century variation of the traditional sour format."
        ),
        CocktailRecipe(
            id = "blood_orange_margarita",
            name = "Blood Orange Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f)
            ),
            garnish = "Blood orange wheel with black lava salt rim",
            description = "Crimson-hued margarita featuring bittersweet raspberry-citrus notes of Sicilian blood orange.",
            historyNotes = "Popular modern winter craft cocktail showcase."
        ),
        CocktailRecipe(
            id = "coconut_margarita",
            name = "Coconut Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Toasted shredded coconut rim & lime wheel",
            description = "Creamy tropical indulgence uniting crisp tequila with lush coconut and lime.",
            historyNotes = "Resort favorite born in Cabo San Lucas and the Mayan Riviera."
        ),
        CocktailRecipe(
            id = "cadillac_margarita",
            name = "Cadillac Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f)
            ),
            garnish = "Grand orange liqueur float & gold salt rim",
            description = "Top-shelf luxury margarita crowned with a decadent float of premium orange cognac liqueur.",
            historyNotes = "Originated in the 1980s as the ultimate premium cocktail status symbol."
        ),
        CocktailRecipe(
            id = "italian_margarita",
            name = "Italian Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("amaretto"), 0.5f)
            ),
            garnish = "Sugar & salt crystal rim with lime wheel",
            description = "Nutty, sweet marzipan almond notes rounding off zesty Mexican tequila and lime.",
            historyNotes = "A beloved Italian-American fusion favorite."
        ),
        CocktailRecipe(
            id = "skinny_margarita",
            name = "Skinny Margarita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("orange_juice"), 0.5f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f)
            ),
            garnish = "Flaky sea salt rim & two lime wheels",
            description = "Low-sugar, zero artificial mixer refresher powered solely by freshly squeezed citrus.",
            historyNotes = "Spawned during the early 2000s health and mindful drinking movement."
        ),
        CocktailRecipe(
            id = "watermelon_margarita",
            name = "Watermelon Margarita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Tajín chili rim & fresh watermelon wedge",
            description = "Hydrating summer favorite with sweet melon nectar, peppery tequila, and zesty chili salt.",
            historyNotes = "A staple of Mexican cantinas during peak summer watermelon harvest."
        ),
        CocktailRecipe(
            id = "mango_margarita",
            name = "Mango Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Tajín spice rim & fresh mango spear",
            description = "Lush, velvety mango nectar contrasting beautifully against crisp lime and tequila.",
            historyNotes = "Iconic Latin American tropical cocktail."
        ),
        CocktailRecipe(
            id = "strawberry_margarita",
            name = "Strawberry Margarita",
            category = "Tequila",
            glass = GlassType.MARGARITA,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Fresh ripe strawberry sliced on the rim with sugar-salt dusting",
            description = "Juicy red berry sweetness balancing out crisp tequila botanicals.",
            historyNotes = "Emerged in Baja California resorts in the 1950s."
        ),
        CocktailRecipe(
            id = "mezcal_mule",
            name = "Oaxacan Mezcal Mule",
            category = "Tequila",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f)
            ),
            garnish = "Candied ginger, lime wheel & slapped mint sprig",
            description = "Earthy pit-roasted agave smoke cutting brilliantly through fiery ginger carbonation.",
            historyNotes = "Modern craft speakeasy staple."
        ),
        CocktailRecipe(
            id = "mezcal_sour",
            name = "Mezcal Sour",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Angostura bitters drops across dense foam",
            description = "Velvety, smoky sour balancing rich roasted agave with citrus and spice.",
            historyNotes = "Craft cocktail staple highlighting artisan mezcal versatility."
        ),
        CocktailRecipe(
            id = "mezcal_negroni",
            name = "Mezcal Negroni",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 1.0f),
                MeasuredIngredient(getIng("campari"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f)
            ),
            garnish = "Flame expressed orange peel",
            description = "Bold smoky agave replaces botanical gin, creating rich interplay with bittersweet Campari.",
            historyNotes = "Pioneered in modern Mexico City cocktail lounges."
        ),
        CocktailRecipe(
            id = "mezcal_paloma",
            name = "Mezcal Paloma",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Smoked sea salt rim with grapefruit half wheel",
            description = "Bittersweet pink grapefruit infused with rich campfire smoke and effervescence.",
            historyNotes = "Oaxacan specialty celebrated across high-end cocktail bars."
        ),
        CocktailRecipe(
            id = "smokey_paloma",
            name = "Smokey Rosemary Paloma",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 1.5f),
                MeasuredIngredient(getIng("tequila"), 0.5f),
                MeasuredIngredient(getIng("grapefruit_juice"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 2.0f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.05f)
            ),
            garnish = "Torched fresh rosemary sprig smoking inside the highball",
            description = "Aromatic woodsy pine oils and dense smoke mingling with brisk grapefruit soda.",
            historyNotes = "Sensory craft cocktail pairing flamed aromatics with agave."
        ),
        CocktailRecipe(
            id = "tequila_collins",
            name = "Tequila Collins",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("agave_nectar"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel & cocktail cherry",
            description = "Highball effervescence highlighting clean agave and sunny lemon.",
            historyNotes = "Latin American highball adaptation from the mid-20th century."
        ),
        CocktailRecipe(
            id = "tequila_daisy",
            name = "Tequila Daisy (Pre-Margarita)",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 0.5f)
            ),
            garnish = "Yellow lemon wheel",
            description = "The historical ancestor of the Margarita: tequila, lemon, and real pomegranate syrup.",
            historyNotes = "Served in Tijuana, Mexico, in the 1930s; 'Margarita' is the Spanish translation of Daisy."
        ),
        CocktailRecipe(
            id = "guadalajara_cocktail",
            name = "Guadalajara Cocktail",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("cappelletti"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f)
            ),
            garnish = "Orange twist",
            description = "Herbal, bittersweet Italian-Mexican fusion highlighting agave and gentian.",
            historyNotes = "Named after Jalisco's vibrant cultural capital."
        ),
        CocktailRecipe(
            id = "torito_cocktail",
            name = "El Torito",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("tomato_juice"), 3.0f),
                MeasuredIngredient(getIng("mexican_lager"), 3.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Tajín chili-salt rim & lime wedge",
            description = "Spicy Michelada-Tequila powerhouse bringing savory tomato, cold beer, and agave together.",
            historyNotes = "Traditional Veracruz cantina favorite."
        ),
        CocktailRecipe(
            id = "mexican_firing_squad",
            name = "Mexican Firing Squad",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.15f)
            ),
            garnish = "Lime wheel & brandied cherry",
            description = "Ruby red, tart, and deeply spiced with heavy Angostura bitters and tart pomegranate.",
            historyNotes = "Created in 1937 at the La Cucaracha Club in Mexico City by Charles H. Baker Jr."
        ),
        CocktailRecipe(
            id = "chimayo_cocktail",
            name = "Chimayó Cocktail",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("hard_cider"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Apple slice dusted with cinnamon",
            description = "New Mexican autumn classic marrying crisp heritage apple cider with reposado tequila.",
            historyNotes = "Created by Arturo Jaramillo in 1965 at Rancho de Chimayó in New Mexico."
        ),
        CocktailRecipe(
            id = "brave_bull",
            name = "Brave Bull",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("kahlua"), 1.0f)
            ),
            garnish = "Lemon twist over solid ice",
            description = "The Black Russian's Mexican brother: robust agave tequila paired with dark coffee liqueur.",
            historyNotes = "Created in Mexico in the 1950s."
        ),
        CocktailRecipe(
            id = "charro_negro",
            name = "Charro Negro",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("cola"), 4.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Salt rim & lime wedge",
            description = "Mexico's classic highball named after the mythical black-clad Mexican horseman.",
            historyNotes = "Everyday cantina staple across Mexico."
        ),
        CocktailRecipe(
            id = "bandera_mexicana",
            name = "La Bandera (Tequila Trio)",
            category = "Tequila",
            glass = GlassType.SHOT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("tequila"), 1.0f),
                MeasuredIngredient(getIng("tomato_juice"), 1.0f)
            ),
            garnish = "Served in three distinct shot glasses mirroring the green, white, and red Mexican flag",
            description = "A culinary ritual: sip lime (green), sip blanco tequila (white), sip spicy sangrita (red).",
            historyNotes = "The most traditional and patriotic way to savor authentic tequila in Jalisco."
        ),
        CocktailRecipe(
            id = "submarino",
            name = "Submarino Mexicano",
            category = "Tequila",
            glass = GlassType.BEER_PINT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("mexican_lager"), 10.0f)
            ),
            garnish = "Salted rim and lime wedge on the pint glass",
            description = "An inverted shot of tequila submerged inside a frosty pint of Mexican lager.",
            historyNotes = "Classic Mexican cantina drinking custom."
        ),
        CocktailRecipe(
            id = "el_diablo",
            name = "El Diablo",
            category = "Tequila",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("ginger_beer"), 3.0f)
            ),
            garnish = "Lime wheel & fresh blackberry float",
            description = "Dark berry and fiery Jamaican ginger beer crowning crisp agave tequila.",
            historyNotes = "First published by Trader Vic in his 1946 Book of Food and Drink."
        ),
        CocktailRecipe(
            id = "oaxacan_dead",
            name = "Oaxacan Dead",
            category = "Tequila",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 1.0f),
                MeasuredIngredient(getIng("tequila"), 1.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Flamed cinnamon stick & pineapple fronds",
            description = "Tiki powerhouse meeting Oaxacan smoke with tropical juices and bold agave.",
            historyNotes = "Agave-forward modern evolution of Don the Beachcomber's Zombie."
        ),
        CocktailRecipe(
            id = "twenty_first_century",
            name = "21st Century",
            category = "Tequila",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("tequila"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("chocolate_bitters"), 0.1f)
            ),
            garnish = "Cocoa nib dusting on surface",
            description = "Modern riff on the 20th Century cocktail pairing blanco tequila with delicate chocolate notes.",
            historyNotes = "Created in 2007 by Jim Meehan at PDT in New York City."
        ),
        CocktailRecipe(
            id = "smoky_mezcalita",
            name = "Smoky Mezcalita",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f)
            ),
            garnish = "Tajín chili rim & dehydrated orange wheel",
            description = "Earthy smoky mezcal paired with sweet fresh orange and tart lime.",
            historyNotes = "Iconic Oaxaca cantina favorite."
        ),
        CocktailRecipe(
            id = "smoked_rosemary_mezcal_sour",
            name = "Smoked Rosemary Mezcal Sour",
            category = "Tequila",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("mezcal"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.5f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.1f)
            ),
            garnish = "Torched rosemary sprig smoking aromatically over the foam",
            description = "Herbal pine resin smoke from torched rosemary mingling with earthy artisanal mezcal.",
            historyNotes = "Craft cocktail bar sensory masterpiece."
        )
    )
}
