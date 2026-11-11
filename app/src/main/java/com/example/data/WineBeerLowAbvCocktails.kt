package com.example.data

import com.example.model.*

object WineBeerLowAbvCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "kir_royale",
            name = "Kir Royale",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("champagne_brut"), 4.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Fresh blackberry or raspberry dropped into the flute",
            description = "Burgundy elegance: dark blackcurrant crème de cassis crowned with sparkling French Champagne.",
            historyNotes = "Popularized in Burgundy after WWII by Canon Félix Kir, Mayor of Dijon and French Resistance hero."
        ),
        CocktailRecipe(
            id = "classic_mimosa",
            name = "Classic Mimosa",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("champagne_brut"), 3.0f),
                MeasuredIngredient(getIng("orange_juice"), 3.0f)
            ),
            garnish = "Fresh orange wheel & strawberry on the flute rim",
            description = "The undisputed queen of brunch: equal parts chilled dry Champagne and freshly squeezed orange juice.",
            historyNotes = "Invented around 1925 by Frank Meier at the Ritz Hotel in Paris, named for the yellow mimosa flower."
        ),
        CocktailRecipe(
            id = "bellini",
            name = "Venetian Bellini",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Fresh white peach slice floating in the flute",
            description = "Fragrant white peach puree enlivened with sparkling Venetian Prosecco.",
            historyNotes = "Created in 1948 by Giuseppe Cipriani at Harry's Bar in Venice, named for the painter Giovanni Bellini."
        ),
        CocktailRecipe(
            id = "champagne_cocktail",
            name = "Classic Champagne Cocktail",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("champagne_brut"), 4.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f),
                MeasuredIngredient(getIng("hennessy"), 0.5f)
            ),
            garnish = "Raw cane sugar cube at the bottom releasing a fountain of effervescent bubbles & lemon twist",
            description = "Hypnotic fountain of bubbles rising through aromatic Angostura bitters and French champagne.",
            historyNotes = "Documented by Jerry Thomas in 1862; a fixture of European royal society."
        ),
        CocktailRecipe(
            id = "rossini",
            name = "Rossini",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.5f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.25f)
            ),
            garnish = "Fresh strawberry halved on the rim",
            description = "Italian sparkling cocktail pairing sweet red strawberry puree with crisp Prosecco bubbles.",
            historyNotes = "Named after the 19th-century Italian composer Gioachino Rossini."
        ),
        CocktailRecipe(
            id = "death_in_the_afternoon",
            name = "Death in the Afternoon",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("absinthe"), 1.0f),
                MeasuredIngredient(getIng("champagne_brut"), 4.0f)
            ),
            garnish = "Milky opalescent green louche clouding the glass",
            description = "Ernest Hemingway's personal invention: green fairy absinthe blossoming into a milky cloud when hit with iced Champagne.",
            historyNotes = "Contributed by Hemingway to the 1935 cocktail book So Red the Nose: 'Drink three to five of these slowly.'"
        ),
        CocktailRecipe(
            id = "black_velvet",
            name = "Black Velvet",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("guinness_stout"), 3.0f),
                MeasuredIngredient(getIng("champagne_brut"), 3.0f)
            ),
            garnish = "Two distinct layered bands: creamy dark stout meeting sparkling golden champagne",
            description = "Dramatic luxury contrast: rich roasted barley dry stout gently layered with crisp French champagne.",
            historyNotes = "Created in 1861 at Brook's Club in London to mourn the death of Prince Albert, Queen Victoria's Prince Consort."
        ),
        CocktailRecipe(
            id = "red_sangria",
            name = "Traditional Spanish Red Sangria",
            category = "Wine & Beer",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("cabernet_sauvignon"), 4.0f),
                MeasuredIngredient(getIng("hennessy"), 0.75f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Diced apples, oranges, lemons, and cinnamon stick steeped in wine",
            description = "Spain's festive communal punch: full-bodied Spanish red wine infused with brandied citrus and spices.",
            historyNotes = "Traditional Iberian punch dating to the Roman era; officially introduced to the US at the 1964 NY World's Fair."
        ),
        CocktailRecipe(
            id = "white_sangria",
            name = "Sangria Blanca (White Wine Sangria)",
            category = "Wine & Beer",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("sauvignon_blanc"), 4.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Green apple slices, white peach halves, and mint sprig",
            description = "Crisp, floral white wine punch brightened with orange liqueur and crisp soda bubbles.",
            historyNotes = "A modern Spanish summer staple along coastal Costa Brava."
        ),
        CocktailRecipe(
            id = "rose_sangria",
            name = "Provençal Rosé Sangria",
            category = "Wine & Beer",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("rose_wine"), 4.0f),
                MeasuredIngredient(getIng("lillet"), 1.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 1.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Fresh strawberries, raspberries & pink grapefruit wheels",
            description = "Pastel pink French Riviera punch combining bone-dry rosé with Lillet and wild berries.",
            historyNotes = "Summer staple across Saint-Tropez and Cannes beach clubs."
        ),
        CocktailRecipe(
            id = "kalimotxo",
            name = "Basque Kalimotxo (Calimocho)",
            category = "Wine & Beer",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("cabernet_sauvignon"), 3.0f),
                MeasuredIngredient(getIng("cola"), 3.0f)
            ),
            garnish = "Lemon slice squeezed over ice",
            description = "Basque Country phenomenon: equal parts dry red wine and Mexican cola.",
            historyNotes = "Invented at the 1972 Puerto Viejo festival in Algorta, Spain; a beloved Basque festival tradition."
        ),
        CocktailRecipe(
            id = "tinto_de_verano",
            name = "Tinto de Verano",
            category = "Wine & Beer",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("cabernet_sauvignon"), 3.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel and orange half-moon",
            description = "Spain's authentic summer drink: red wine lightened with fizzy lemon soda.",
            historyNotes = "Created in the early 20th century in Córdoba, Spain; the true everyday drink of Spanish locals."
        ),
        CocktailRecipe(
            id = "mulled_wine",
            name = "Glühwein (Spiced Mulled Wine)",
            category = "Wine & Beer",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("cabernet_sauvignon"), 5.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Whole cinnamon stick, star anise pod, and clove-studded orange peel",
            description = "Steaming German Christmas market staple: warm spiced red wine layered with cinnamon, citrus, and cloves.",
            historyNotes = "Traditional European winter warmer documented in Germany as early as 1420."
        ),
        CocktailRecipe(
            id = "michelada",
            name = "Authentic Mexican Michelada",
            category = "Wine & Beer",
            glass = GlassType.BEER_PINT,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("mexican_lager"), 8.0f),
                MeasuredIngredient(getIng("tomato_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f),
                MeasuredIngredient(getIng("celery_bitters"), 0.05f)
            ),
            garnish = "Heavy Tajín chili-lime salt rim, lime wedge & savory Worcestershire drops",
            description = "Mexico's favorite beer cocktail: crisp lager, spiced savory tomato, lime, and piquant chili rim.",
            historyNotes = "Named after 'Mi Chela Helada' (My Ice Cold Beer); originated in San Luis Potosí cantinas."
        ),
        CocktailRecipe(
            id = "chelada",
            name = "Classic Chelada",
            category = "Wine & Beer",
            glass = GlassType.BEER_PINT,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("mexican_lager"), 10.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f)
            ),
            garnish = "Coarse flaky sea salt rim & spent lime wedge dropped into the beer",
            description = "Pure Mexican refreshment: cold draft lager on the rocks with fresh lime juice and a salted rim.",
            historyNotes = "Everyday staple across Mexican beach palapas and taquerias."
        ),
        CocktailRecipe(
            id = "shandy",
            name = "Traditional Shandy (Radler)",
            category = "Wine & Beer",
            glass = GlassType.BEER_PINT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("pilsner"), 6.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Thick lemon wheel",
            description = "Crisp, low-alcohol thirst-quencher combining crisp Czech pilsner with tart sparkling lemonade.",
            historyNotes = "Invented in Bavaria in 1922 for cyclists ('Radler' means cyclist in German)."
        ),
        CocktailRecipe(
            id = "cider_black_and_tan",
            name = "Cider Black and Tan (Black Adder)",
            category = "Wine & Beer",
            glass = GlassType.BEER_PINT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("hard_cider"), 6.0f),
                MeasuredIngredient(getIng("guinness_stout"), 6.0f)
            ),
            garnish = "Layered over a bent spoon to maintain a sharp horizontal boundary between gold and black",
            description = "Crisp sparkling apple cider topped with a floating black crown of velvety Irish stout.",
            historyNotes = "British pub classic combining orchard cider with roasted stout."
        ),
        CocktailRecipe(
            id = "snakebite",
            name = "The Snakebite",
            category = "Wine & Beer",
            glass = GlassType.BEER_PINT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("pilsner"), 6.0f),
                MeasuredIngredient(getIng("hard_cider"), 6.0f)
            ),
            garnish = "Dash of sweet blackcurrant grenadine",
            description = "Potent British pub favorite mixing crisp lager and hard fermented cider.",
            historyNotes = "Gained notoriety in 1980s UK pubs; a legendary student and concert staple."
        ),
        CocktailRecipe(
            id = "bishop_cocktail",
            name = "The Bishop Cocktail",
            category = "Wine & Beer",
            glass = GlassType.WINE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("ruby_port"), 2.0f),
                MeasuredIngredient(getIng("cabernet_sauvignon"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Roasted orange slice studded with whole cloves",
            description = "Historic European university punch pairing fortified Port with dry red wine and citrus.",
            historyNotes = "Mentioned in Charles Dickens's works and 18th-century Oxford university texts."
        ),
        CocktailRecipe(
            id = "french_75_champagne",
            name = "Royal French 75 (Grand Champagne)",
            category = "Wine & Beer",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("champagne_brut"), 3.0f)
            ),
            garnish = "Long lemon spiral & cocktail cherry",
            description = "The ultimate celebratory sparkler combining dry French champagne with crisp London gin.",
            historyNotes = "Named after the French 75mm field gun in WWI for its remarkable kick and effervescence."
        )
    )
}
