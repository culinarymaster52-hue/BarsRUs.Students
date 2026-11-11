package com.example.data

import com.example.model.*

object WhiskeyCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "old_fashioned",
            name = "Classic Old Fashioned",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Large ice sphere, flamed orange peel & Luxardo cherry",
            description = "The foundational American cocktail: rich bourbon, aromatics, and toasted orange oils over clear ice.",
            historyNotes = "Originated at the Pendennis Club in Louisville, Kentucky in the 1880s."
        ),
        CocktailRecipe(
            id = "smoked_old_fashioned",
            name = "Smoked Bourbon Old Fashioned",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.25f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("fee_whiskey_barrel_bitters"), 0.1f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.05f)
            ),
            garnish = "Charred bourbon barrel smoke cloche infusion with flamed orange twist",
            description = "Smoked under charred oak wood chips using a butane torch, imparting deep campfire vanilla notes.",
            historyNotes = "A signature modern craft bar technique celebrated across high-end speakeasies worldwide."
        ),
        CocktailRecipe(
            id = "manhattan",
            name = "Classic Manhattan",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Luxardo brandied cherry in base of coupe",
            description = "Rich, silky, peppery rye balanced by herbaceous Italian vermouth and spice bitters.",
            historyNotes = "Invented at the Manhattan Club in New York City in the early 1870s."
        ),
        CocktailRecipe(
            id = "black_manhattan",
            name = "Black Manhattan",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("cappelletti"), 1.0f),
                MeasuredIngredient(getIng("walnut_bitters"), 0.05f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Brandied cocktail cherry & orange twist",
            description = "Deep, velvety variation replacing vermouth with bittersweet amaro and black walnut bitters.",
            historyNotes = "Created in 2005 by bartender Todd Smith at Bourbon & Branch in San Francisco."
        ),
        CocktailRecipe(
            id = "smoked_black_manhattan",
            name = "Smoked Black Manhattan",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("cappelletti"), 1.0f),
                MeasuredIngredient(getIng("walnut_bitters"), 0.1f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.05f)
            ),
            garnish = "Hickory wood smoked glass with flamed orange peel",
            description = "Stirred to silky perfection and trapped inside an aromatic hickory smoke chamber.",
            historyNotes = "Contemporary craft evolution celebrating aromatic smoke with Italian bitter herbal profiles."
        ),
        CocktailRecipe(
            id = "whiskey_sour",
            name = "Classic Whiskey Sour",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Angostura bitters artfully dotted across foam with cherry",
            description = "Rich caramel bourbon balanced by tangy freshly pressed lemon juice and silky aeration.",
            historyNotes = "First recorded in the Waukesha Plaindealer in 1870 and popularized in Jerry Thomas's manuals."
        ),
        CocktailRecipe(
            id = "new_york_sour",
            name = "New York Sour",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("cabernet_sauvignon"), 0.5f)
            ),
            garnish = "Two-tone layered red wine crown over citrus sour",
            description = "Visually stunning sour featuring a ruby crown of dry Cabernet Sauvignon floated on top.",
            historyNotes = "Created in Chicago in the 1880s by a bartender calling it the Continental Sour; Manhattan bars made it iconic."
        ),
        CocktailRecipe(
            id = "mint_julep",
            name = "Classic Mint Julep",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f)
            ),
            garnish = "Huge frost-dusted bouquet of slapped fresh mint leaves",
            description = "Chilled to frosted perfection in a metal cup with crushed ice, sweet syrup, and Kentucky bourbon.",
            historyNotes = "The official libation of the Kentucky Derby at Churchill Downs since 1938."
        ),
        CocktailRecipe(
            id = "sazerac",
            name = "New Orleans Sazerac",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.15f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Lemon peel expressed over top and discarded",
            description = "Official cocktail of New Orleans: rye whiskey, red Peychaud's bitters, and an absinthe-washed glass.",
            historyNotes = "Created by Antoine Peychaud in the French Quarter during the mid-19th century."
        ),
        CocktailRecipe(
            id = "boulevardier",
            name = "The Boulevardier",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 1.25f),
                MeasuredIngredient(getIng("campari"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f)
            ),
            garnish = "Orange twist & cocktail cherry",
            description = "The rich American whiskey cousin of the Negroni, swapping gin for bold charred oak bourbon.",
            historyNotes = "Created in 1927 by Erskine Gwynne, American writer and editor of The Boulevardier magazine in Paris."
        ),
        CocktailRecipe(
            id = "paper_plane",
            name = "Paper Plane",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 0.75f),
                MeasuredIngredient(getIng("aperol"), 0.75f),
                MeasuredIngredient(getIng("nonino_aperitivo"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f)
            ),
            garnish = "Miniature folded paper airplane pegged to rim",
            description = "Equal parts modern triumph: bittersweet, citrusy, and herbaceous with rich bourbon warmth.",
            historyNotes = "Created in 2008 by Sam Ross for the Violet Hour in Chicago, named after M.I.A.'s hit song."
        ),
        CocktailRecipe(
            id = "penicillin",
            name = "The Penicillin",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 0.5f)
            ),
            garnish = "Candied ginger on a pick with aromatic peated scotch float",
            description = "Blended scotch sour enriched by spicy honey-ginger syrup, crowned with smoky single malt.",
            historyNotes = "Invented by Sam Ross in 2005 at Milk & Honey in New York, regarded as a modern classic worldwide."
        ),
        CocktailRecipe(
            id = "rob_roy",
            name = "Rob Roy",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Two Luxardo cherries on a bamboo spear",
            description = "The Scotch Manhattan: malted barley smokiness mingled with rich Italian vermouth.",
            historyNotes = "Created in 1894 by a bartender at the Waldorf Astoria in New York to promote an operetta."
        ),
        CocktailRecipe(
            id = "rusty_nail",
            name = "Rusty Nail",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 2.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("herbal_bitters"), 0.05f)
            ),
            garnish = "Expressed lemon peel twist",
            description = "Scotch whisky sweetened with spiced heather honey liqueur, delivering warmth and herbal smoke.",
            historyNotes = "Popularized in the 1960s by the Rat Pack at the 21 Club in New York."
        ),
        CocktailRecipe(
            id = "vieux_carre",
            name = "Vieux Carré",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 0.75f),
                MeasuredIngredient(getIng("hennessy"), 0.75f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.75f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.05f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Expressed lemon peel & brandied cherry",
            description = "New Orleans French Quarter gem weaving spicy rye, French cognac, and dual aromatic bitters.",
            historyNotes = "Created in 1938 by Walter Bergeron at the Carousel Bar inside the Hotel Monteleone."
        ),
        CocktailRecipe(
            id = "brown_derby",
            name = "Brown Derby",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 1.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.5f)
            ),
            garnish = "Grapefruit twist",
            description = "Bright tart ruby grapefruit tempered with rich honey syrup and assertive Kentucky bourbon.",
            historyNotes = "Invented at the Vendôme Club in Los Angeles in the 1930s, named for the famous hat-shaped diner."
        ),
        CocktailRecipe(
            id = "gold_rush",
            name = "Gold Rush",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f)
            ),
            garnish = "Lemon wheel",
            description = "Silky three-ingredient modern classic delivering luscious honey warmth and citrus zing.",
            historyNotes = "Created by T.J. Siegal at Milk & Honey in New York in the early 2000s."
        ),
        CocktailRecipe(
            id = "kentucky_mule",
            name = "Kentucky Mule",
            category = "Whiskey",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f)
            ),
            garnish = "Lime wheel & fresh mint sprig in a frosted copper mug",
            description = "Caramel oak bourbon mingling with zesty cold-pressed lime and fiery ginger bubbles.",
            historyNotes = "Southern twist on the 1941 Moscow Mule replacing Russian vodka with Kentucky straight bourbon."
        ),
        CocktailRecipe(
            id = "lynchburg_lemonade",
            name = "Lynchburg Lemonade",
            category = "Whiskey",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("jack_daniels"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel & cocktail cherry",
            description = "Southern porch cooler blending charcoal-mellowed Tennessee whiskey with lemonade effervescence.",
            historyNotes = "Created in 1980 by Alabama restaurateur Tony Mason, named after Lynchburg, Tennessee."
        ),
        CocktailRecipe(
            id = "hot_toddy",
            name = "Classic Hot Toddy",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f)
            ),
            garnish = "Cinnamon stick, lemon wheel studded with whole cloves",
            description = "Soothing winter warmer blending steaming spiced water with honey, citrus, and whiskey.",
            historyNotes = "Traditional 18th-century Scottish restorative widely prescribed as a soothing winter remedy."
        ),
        CocktailRecipe(
            id = "irish_coffee",
            name = "Traditional Irish Coffee",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("jameson"), 1.5f),
                MeasuredIngredient(getIng("kahlua"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Chilled hand-shaken heavy cream floated on surface with freshly grated nutmeg",
            description = "Hot rich coffee and Irish whiskey sipped through a velvety collar of cold fresh cream.",
            historyNotes = "Invented by Joe Sheridan in 1943 at Foynes flying boat base in Ireland for shivering passengers."
        ),
        CocktailRecipe(
            id = "blood_and_sand",
            name = "Blood and Sand",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 0.75f),
                MeasuredIngredient(getIng("cherry"), 0.75f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.75f),
                MeasuredIngredient(getIng("orange_juice"), 0.75f)
            ),
            garnish = "Flame orange peel",
            description = "Equal parts harmony named after Rudolph Valentino's 1922 bullfighter movie.",
            historyNotes = "Recorded in Harry Craddock's 1930 Savoy Cocktail Book."
        ),
        CocktailRecipe(
            id = "tipperary",
            name = "The Tipperary",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("jameson"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("herbal_bitters"), 0.1f)
            ),
            garnish = "Orange twist",
            description = "Rich Irish whiskey layered with herbal sweetness and fortified wine.",
            historyNotes = "Published in Hugo Ensslin's 1916 Recipes for Mixed Drinks, named after the popular wartime song."
        ),
        CocktailRecipe(
            id = "remember_the_maine",
            name = "Remember the Maine",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.75f),
                MeasuredIngredient(getIng("cherry"), 0.25f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Luxardo brandied cherry",
            description = "Spicy rye cocktail enhanced by cherry liqueur and a whisper of anise.",
            historyNotes = "Created in 1933 by Charles H. Baker Jr. in The Gentleman's Companion during the Cuban Revolution."
        ),
        CocktailRecipe(
            id = "brooklyn_cocktail",
            name = "The Brooklyn",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("noilly_prat"), 1.0f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Brandied cherry",
            description = "Dry vermouth and subtle maraschino cherry giving a sophisticated bone-dry bite to rye whiskey.",
            historyNotes = "Created by Jack Grohusko in 1908 at the Borough Hall lounge in Brooklyn, New York."
        ),
        CocktailRecipe(
            id = "ward_eight",
            name = "Ward Eight",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Speared maraschino cherry & orange slice",
            description = "Boston classic blending American rye with citrus duo and genuine pomegranate grenadine.",
            historyNotes = "Created in 1898 at Boston's Locke-Ober restaurant to celebrate Martin Lomasney's election in Ward 8."
        ),
        CocktailRecipe(
            id = "scofflaw",
            name = "The Scofflaw",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 1.5f),
                MeasuredIngredient(getIng("noilly_prat"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.75f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Expressed orange peel twist",
            description = "Tart and fruity rye sour invented to mock American Prohibitionists who coined 'scofflaw'.",
            historyNotes = "Invented on January 24, 1924, by Jock at Harry's New York Bar in Paris."
        ),
        CocktailRecipe(
            id = "revolver_cocktail",
            name = "The Revolver",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("kahlua"), 0.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.1f)
            ),
            garnish = "Flamed wide orange peel expressing oils across surface",
            description = "Warm Bulleit bourbon paired with rich roasted coffee liqueur and fragrant flamed orange oils.",
            historyNotes = "Created by Jon Santer in San Francisco around 2004."
        ),
        CocktailRecipe(
            id = "seelbach",
            name = "The Seelbach",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.1f),
                MeasuredIngredient(getIng("champagne_brut"), 3.0f)
            ),
            garnish = "Long spiral orange peel",
            description = "Heavily bittered bourbon sparkler topped with dry French champagne.",
            historyNotes = "Created at the Seelbach Hotel in Louisville, Kentucky, famous F. Scott Fitzgerald haunt."
        ),
        CocktailRecipe(
            id = "godfather",
            name = "The Godfather",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 2.0f),
                MeasuredIngredient(getIng("amaretto"), 0.75f)
            ),
            garnish = "Orange twist over a single solid ice block",
            description = "Silky marriage of smoky Scotch whisky and sweet Italian almond liqueur.",
            historyNotes = "Popularized in the 1970s following Francis Ford Coppola's iconic film starring Marlon Brando."
        ),
        CocktailRecipe(
            id = "whiskey_smash",
            name = "Whiskey Smash",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f)
            ),
            garnish = "Muddled lemon wedges & bushy mint bouquet over crushed ice",
            description = "A brisk, summery Julep-meets-Sour packed with freshly pressed lemon oils and bruised mint.",
            historyNotes = "Documented by Jerry Thomas in 1862; revived by Dale DeGroff in the 1990s."
        ),
        CocktailRecipe(
            id = "algonquin",
            name = "Algonquin Cocktail",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 1.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.75f),
                MeasuredIngredient(getIng("pineapple_juice"), 0.75f)
            ),
            garnish = "Dried pineapple wheel or cocktail cherry",
            description = "Unusual marriage of spicy rye whiskey, French dry vermouth, and frothy tropical pineapple.",
            historyNotes = "Named after the historic Algonquin Hotel in Manhattan, home to the Round Table literary salon."
        ),
        CocktailRecipe(
            id = "camerons_kick",
            name = "Cameron's Kick",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("jameson"), 1.0f),
                MeasuredIngredient(getIng("johnnie_walker"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Expressed lemon peel",
            description = "Harmonious duet between smooth Irish whiskey and smoky blended Scotch sweetened with almond syrup.",
            historyNotes = "Published in Harry MacElhone's 1927 Barflies and Cocktails."
        ),
        CocktailRecipe(
            id = "final_ward",
            name = "The Final Ward",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 0.75f),
                MeasuredIngredient(getIng("suze"), 0.75f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f)
            ),
            garnish = "Brandied cherry in the coupe",
            description = "A whiskey lover's riff on The Last Word substituting rye and lemon for gin and lime.",
            historyNotes = "Created in 2007 by Phil Ward at Death & Co in New York City."
        ),
        CocktailRecipe(
            id = "lions_tail",
            name = "Lion's Tail",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Grated nutmeg & flamed orange peel",
            description = "Uniquely pairs Kentucky bourbon with Caribbean allspice dram and fresh lime juice.",
            historyNotes = "Featured in the 1937 Café Royal Cocktail Book in London."
        ),
        CocktailRecipe(
            id = "blinker_cocktail",
            name = "The Blinker",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 1.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Grapefruit twist",
            description = "Tart, brisk sour marrying spicy rye with zesty grapefruit and rich pomegranate grenadine.",
            historyNotes = "Recorded in Patrick Gavin Duffy's 1934 Official Mixer's Manual."
        ),
        CocktailRecipe(
            id = "john_collins",
            name = "John Collins",
            category = "Whiskey",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Orange slice & maraschino cherry",
            description = "The bourbon whiskey cousin to the Tom Collins: refreshing, effervescent, and citrusy.",
            historyNotes = "Named after a head waiter at Limmer's Old House in London in the early 19th century."
        ),
        CocktailRecipe(
            id = "monte_carlo",
            name = "Monte Carlo",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.5f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.1f)
            ),
            garnish = "Lemon twist",
            description = "Rye Manhattan variation swapping vermouth for herbal Benedictine and New Orleans Peychaud's bitters.",
            historyNotes = "Published in David Embury's 1948 The Fine Art of Mixing Drinks."
        ),
        CocktailRecipe(
            id = "diamondback",
            name = "The Diamondback",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 1.5f),
                MeasuredIngredient(getIng("hennessy"), 0.75f),
                MeasuredIngredient(getIng("suze"), 0.75f)
            ),
            garnish = "Mint sprig or expressed lemon peel",
            description = "High-proof powerhouse pairing spicy rye whiskey with apple/grape brandy and herbal liqueur.",
            historyNotes = "Created at the Lord Baltimore Hotel in Maryland, named after its Diamondback Lounge."
        ),
        CocktailRecipe(
            id = "rattlesnake",
            name = "The Rattlesnake",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Silky white foam with Angostura bitters drops",
            description = "Whiskey sour made dangerous with an absinthe rinse that 'rattles' the palate.",
            historyNotes = "Created in 1930 at the Savoy Hotel in London."
        ),
        CocktailRecipe(
            id = "bourbon_renewal",
            name = "Bourbon Renewal",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Lemon wheel & fresh berries",
            description = "Modern classic berry sour brightening rich Kentucky bourbon with dark fruit and lemon.",
            historyNotes = "Created in 2001 by Jeffrey Morgenthaler at Clyde Common in Portland, Oregon."
        ),
        CocktailRecipe(
            id = "greenpoint",
            name = "The Greenpoint",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.5f),
                MeasuredIngredient(getIng("suze"), 0.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Expressed lemon twist",
            description = "Brooklyn-neighborhood Manhattan variation incorporating herbal alpine gentian liqueur.",
            historyNotes = "Invented by Michael McIlroy at Milk & Honey in New York in 2006."
        ),
        CocktailRecipe(
            id = "preakness",
            name = "Preakness Cocktail",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Lemon twist & brandied cherry",
            description = "Classic Manhattan variation honoring the famous Baltimore horse race.",
            historyNotes = "Created in the 1930s to celebrate the middle jewel of the American Triple Crown."
        ),
        CocktailRecipe(
            id = "japanese_highball",
            name = "Tokyo Highball (Suntory Kakubin)",
            category = "Whiskey",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("kakubin"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 5.0f)
            ),
            garnish = "Expressed lemon peel along the glass rim",
            description = "Japan's national serve: crystal spear ice, Suntory Kakubin whisky, and super-carbonated soda water.",
            historyNotes = "Perfected in Tokyo Izakayas and Ginza cocktail lounges for crisp food pairing."
        ),
        CocktailRecipe(
            id = "boilermaker",
            name = "Boilermaker (Shot and a Beer)",
            category = "Whiskey",
            glass = GlassType.BEER_PINT,
            iceRequired = false,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bourbon"), 1.5f),
                MeasuredIngredient(getIng("craft_ipa"), 8.0f)
            ),
            garnish = "Shot dropped directly into the beer or served on the side",
            description = "Working-class classic pairing a crisp craft IPA pint with a straight shot of Kentucky bourbon.",
            historyNotes = "Named for 19th-century locomotive boiler mechanics who drank it after grueling shifts."
        ),
        CocktailRecipe(
            id = "toronto_cocktail",
            name = "Toronto Cocktail",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 2.0f),
                MeasuredIngredient(getIng("fernet_branca"), 0.25f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Expressed orange peel twist",
            description = "Assertive Canadian/American rye fortified with inky, mentholated, bitter Fernet-Branca.",
            historyNotes = "First recorded in 1922 in Robert Vermeire's Cocktails: How to Mix Them."
        ),
        CocktailRecipe(
            id = "black_nail",
            name = "Black Nail",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("jameson"), 1.75f),
                MeasuredIngredient(getIng("kahlua"), 0.75f)
            ),
            garnish = "Dark roasted whole coffee beans",
            description = "Irish whiskey variation of the Black Russian pairing smooth Jameson with dark roasted coffee.",
            historyNotes = "Modern pub classic popular across Dublin and North America."
        ),
        CocktailRecipe(
            id = "bobby_burns",
            name = "Bobby Burns",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("johnnie_walker"), 2.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("honey_syrup"), 0.25f),
                MeasuredIngredient(getIng("peychauds_bitters"), 0.05f)
            ),
            garnish = "Shortbread cookie served on the side",
            description = "Rich, herbaceous Scottish classic honoring Scotland's national poet, Robert Burns.",
            historyNotes = "Documented in the 1900 Bishop and Babcock Co. Fancy Drinks guide."
        ),
        CocktailRecipe(
            id = "red_bank",
            name = "Red Bank",
            category = "Whiskey",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 1.5f),
                MeasuredIngredient(getIng("dubonnet"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Lemon wheel",
            description = "Spicy rye complemented by spiced French Dubonnet apéritif and fresh lemon.",
            historyNotes = "Classic East Coast cocktail named after Red Bank, New Jersey."
        ),
        CocktailRecipe(
            id = "smoked_oaxacan_rye",
            name = "Smoked Oaxacan Rye",
            category = "Whiskey",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("rye"), 1.5f),
                MeasuredIngredient(getIng("mezcal"), 0.75f),
                MeasuredIngredient(getIng("agave_nectar"), 0.25f),
                MeasuredIngredient(getIng("smoky_bitters"), 0.1f)
            ),
            garnish = "Charred applewood smoke infusion with flame orange coin",
            description = "Complex union of peppery American rye and artisanal mezcal enveloped in billowing aromatic smoke.",
            historyNotes = "Modern bar craft fusion honoring Oaxaca and Kentucky distilling heritage."
        )
    )
}
