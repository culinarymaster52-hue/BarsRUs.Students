package com.example.data

import com.example.model.*

object BrandyCognacCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "sidecar",
            name = "Classic French Sidecar",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f)
            ),
            garnish = "Half fine white sugar-crusted rim & lemon twist",
            description = "The gold standard of brandy cocktails: French oak cognac, crisp orange liqueur, and tart lemon.",
            historyNotes = "Invented around 1918 in Paris at Harry's New York Bar, named for an American army captain's motorcycle sidecar."
        ),
        CocktailRecipe(
            id = "brandy_alexander",
            name = "Brandy Alexander",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.5f),
                MeasuredIngredient(getIng("kahlua"), 1.0f),
                MeasuredIngredient(getIng("baileys"), 1.0f)
            ),
            garnish = "Generously dusted with freshly grated nutmeg",
            description = "Decadent, creamy dessert libation uniting oak-aged French cognac with roasted cocoa and cream.",
            historyNotes = "Evolved from the gin-based Alexander in 1920s New York; John Lennon's favorite drink during his 'lost weekend'."
        ),
        CocktailRecipe(
            id = "original_cognac_sazerac",
            name = "Historic Cognac Sazerac (1850)",
            category = "Brandy",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.15f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Lemon peel expressed over the rim and discarded",
            description = "The original 1850 New Orleans recipe made with imported French cognac before phylloxera decimated French vineyards.",
            historyNotes = "Served at the Sazerac Coffee House in New Orleans using Sazerac de Forge et Fils Cognac."
        ),
        CocktailRecipe(
            id = "french_connection",
            name = "French Connection",
            category = "Brandy",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.5f),
                MeasuredIngredient(getIng("amaretto"), 1.5f)
            ),
            garnish = "Orange twist over a single large solid ice cube",
            description = "Equal parts warmth and silky sweetness: rich French grape cognac and Italian almond liqueur.",
            historyNotes = "Named after the 1971 film starring Gene Hackman; 1970s bar classic."
        ),
        CocktailRecipe(
            id = "metropolitan_brandy",
            name = "Metropolitan Cocktail",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Brandied cocktail cherry",
            description = "The Brandy Manhattan: rich fortified red vermouth rounding off deep French grape distillate.",
            historyNotes = "Originated in the late 19th century in New York City."
        ),
        CocktailRecipe(
            id = "stinger",
            name = "The Stinger",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Slapped fresh mint leaf floating on top",
            description = "Aristocratic digestif blending robust cognac with crisp cooling menthol mint.",
            historyNotes = "The high-society nightcap of New York's Gilded Age elite, famously sipped in High Society (1956)."
        ),
        CocktailRecipe(
            id = "corpse_reviver_no1",
            name = "Corpse Reviver No. 1",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.5f),
                MeasuredIngredient(getIng("hard_cider"), 0.75f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.75f)
            ),
            garnish = "Lemon twist",
            description = "Warm, spirituous French restorative highlighting apple and grape brandies.",
            historyNotes = "Documented by Harry Craddock in the 1930 Savoy Cocktail Book: 'To be taken before 11 a.m.'"
        ),
        CocktailRecipe(
            id = "japanese_cocktail",
            name = "Japanese Cocktail",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("bokers_bitters"), 0.1f)
            ),
            garnish = "Wide expressed lemon peel",
            description = "Silky French cognac flavored with rich toasted almond orgeat syrup and aromatic bitters.",
            historyNotes = "Created in 1860 by Jerry Thomas at the Metropolitan Hotel in NYC to honor the first Japanese diplomatic mission."
        ),
        CocktailRecipe(
            id = "champs_elysees",
            name = "Champs-Élysées",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("suze"), 0.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Expressed lemon peel",
            description = "Sophisticated herbal cognac sour named after Paris's grandest avenue.",
            historyNotes = "Published in Harry Craddock's 1930 Savoy Cocktail Book."
        ),
        CocktailRecipe(
            id = "pisco_sour",
            name = "Classic Peruvian Pisco Sour",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("antioqueno"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Three drops of Angostura bitters placed atop the dense white foam",
            description = "Peru's national icon: aromatic grape pisco, tart Key lime, and a frothy foam crown.",
            historyNotes = "Invented in the early 1920s by American expatriate Victor Morris at the Morris' Bar in Lima, Peru."
        ),
        CocktailRecipe(
            id = "chilcano",
            name = "El Chilcano de Pisco",
            category = "Brandy",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("antioqueno"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("ginger_ale"), 4.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Lime wheel & dash of Angostura bitters on top",
            description = "Peru's everyday highball favorite: aromatic grape brandy lifted with zesty lime and effervescent ginger.",
            historyNotes = "A cultural staple across Peruvian coastal cities dating to the early 20th century."
        ),
        CocktailRecipe(
            id = "piscola",
            name = "La Piscola (Chilean Highball)",
            category = "Brandy",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("antioqueno"), 2.0f),
                MeasuredIngredient(getIng("cola"), 4.0f)
            ),
            garnish = "Lemon slice over plenty of crystalline ice",
            description = "Chile's beloved highball combining grape pisco with cola.",
            historyNotes = "Celebrated nationwide in Chile with an official National Piscola Day on February 8."
        ),
        CocktailRecipe(
            id = "el_capitan",
            name = "El Capitán",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("antioqueno"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Green olive or cocktail cherry",
            description = "The Pisco Manhattan: floral South American grape brandy paired with rich sweet vermouth.",
            historyNotes = "Created in the 1920s in the Peruvian Andes for military captains."
        ),
        CocktailRecipe(
            id = "brandy_crusta",
            name = "Brandy Crusta",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.25f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("bokers_bitters"), 0.05f)
            ),
            garnish = "Complete wide sugar-crusted rim with the entire inside lined by an intact lemon peel",
            description = "The direct evolutionary ancestor of the Sidecar: rich cognac, cherry liqueur, and citrus in a sugared glass.",
            historyNotes = "Invented in the 1850s by Joseph Santini at the City Exchange in New Orleans."
        ),
        CocktailRecipe(
            id = "saratoga_cocktail",
            name = "Saratoga Cocktail",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.0f),
                MeasuredIngredient(getIng("rye"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Brandied cocktail cherry & lemon twist",
            description = "Potent, luxurious split-base Manhattan uniting French cognac and American rye whiskey.",
            historyNotes = "Originated in the 1880s at Saratoga Springs, New York, favorite resort of the Gilded Age elite."
        ),
        CocktailRecipe(
            id = "horses_neck",
            name = "Horse's Neck with a Kick",
            category = "Brandy",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("ginger_ale"), 4.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Continuous long spiral of lemon peel draped over the rim like a horse's mane",
            description = "Brandy and ginger highball crowned with a dramatic whole-lemon spiral peel.",
            historyNotes = "Began in the 1890s as a non-alcoholic drink; the addition of brandy gave it 'the kick'."
        ),
        CocktailRecipe(
            id = "b_and_b",
            name = "B&B (Bénédictine and Brandy)",
            category = "Brandy",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.5f),
                MeasuredIngredient(getIng("honey_syrup"), 1.0f),
                MeasuredIngredient(getIng("herbal_bitters"), 0.05f)
            ),
            garnish = "Served neat in a brandy snifter or over a single ice cube",
            description = "Rich, warming digestif combining French oak cognac with herbal monastic liqueur.",
            historyNotes = "Created in the 1930s at the 21 Club in New York City."
        ),
        CocktailRecipe(
            id = "olympic_cocktail",
            name = "The Olympic",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 1.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f)
            ),
            garnish = "Orange twist",
            description = "Equal-parts citrus delight highlighting cognac and sun-ripened orange.",
            historyNotes = "Created in 1924 by Harry MacElhone in Paris to celebrate the VIII Olympic Games."
        ),
        CocktailRecipe(
            id = "harvard_cocktail",
            name = "Harvard Cocktail",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f),
                MeasuredIngredient(getIng("club_soda"), 0.5f)
            ),
            garnish = "Lemon peel",
            description = "Brandy Manhattan variant honoring the Ivy League institution, lightened with a splash of soda.",
            historyNotes = "Documented by George J. Kappeler in 1895 Modern American Drinks."
        ),
        CocktailRecipe(
            id = "brandy_manhattan",
            name = "Wisconsin Brandy Manhattan",
            category = "Brandy",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Two bright red maraschino cherries with sweet cherry syrup",
            description = "The state drink of Wisconsin: mellow grape brandy taking the place of whiskey in a classic Manhattan.",
            historyNotes = "Wisconsin famously consumes more brandy per capita than any other state in America."
        ),
        CocktailRecipe(
            id = "brandy_daisy",
            name = "Classic Brandy Daisy",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("club_soda"), 0.5f)
            ),
            garnish = "Fresh berries & yellow lemon wheel",
            description = "19th-century daisy format pairing French grape brandy with citrus and bubbles.",
            historyNotes = "First recorded by Jerry Thomas in 1876."
        ),
        CocktailRecipe(
            id = "brandy_sour",
            name = "Cypriot Brandy Sour",
            category = "Brandy",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Lemon slice & fresh mint",
            description = "National cocktail of Cyprus: grape brandy, lemon, aromatic bitters, and effervescence.",
            historyNotes = "Created in the 1930s at the Forest Park Hotel in Platres, Cyprus for King Farouk of Egypt."
        ),
        CocktailRecipe(
            id = "east_india_cocktail",
            name = "East India Cocktail",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.25f),
                MeasuredIngredient(getIng("pineapple_juice"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Maraschino cherry & lemon twist",
            description = "Colonial classic pairing oak-aged brandy with tart tropical pineapple and orange liqueur.",
            historyNotes = "Published in Harry Johnson's 1882 Bartenders' Manual."
        ),
        CocktailRecipe(
            id = "brandy_flip",
            name = "Classic Brandy Flip",
            category = "Brandy",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("baileys"), 0.5f)
            ),
            garnish = "Velvety head heavily dusted with fresh nutmeg",
            description = "Rich, custard-like winter indulgence featuring cognac and whole egg aeration.",
            historyNotes = "Traditional Anglo-American tavern drink dating back to the late 17th century."
        ),
        CocktailRecipe(
            id = "smoked_brandy_toddy",
            name = "Smoked Spiced Brandy Toddy",
            category = "Brandy",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hennessy"), 2.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Charred cinnamon stick & applewood smoke cloche infusion",
            description = "Hot spiced brandy infused with aromatic applewood smoke and winter spices.",
            historyNotes = "Modern sensory fireside winter restorative."
        )
    )
}
