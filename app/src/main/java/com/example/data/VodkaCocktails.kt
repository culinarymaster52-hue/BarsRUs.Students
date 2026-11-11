package com.example.data

import com.example.model.*

object VodkaCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "espresso_martini",
            name = "Classic Espresso Martini",
            category = "Vodka",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("kahlua"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Three floating coffee beans (representing health, wealth, and happiness)",
            description = "Rich, dark, and crowned with a thick crema foam: chilled fresh espresso, coffee liqueur, and clean vodka.",
            historyNotes = "Created in 1983 by Dick Bradsell at Fred's Club in London for a model who asked for something to 'wake me up and mess me up'."
        ),
        CocktailRecipe(
            id = "moscow_mule",
            name = "Classic Moscow Mule",
            category = "Vodka",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f)
            ),
            garnish = "Frosty copper mug with lime wheel & fresh slapped mint sprig",
            description = "High effervescence, spicy ginger root heat, and tart lime served in an ice-cold copper vessel.",
            historyNotes = "Created in 1941 at the Cock 'n Bull in Hollywood by John Martin and Jack Morgan to popularize Smirnoff vodka in America."
        ),
        CocktailRecipe(
            id = "cosmopolitan",
            name = "The Cosmopolitan",
            category = "Vodka",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("cranberry_juice"), 0.5f)
            ),
            garnish = "Flame-expressed orange peel coin over the surface",
            description = "Pale pastel pink, crisp, and tart: premium vodka, orange liqueur, fresh lime, and just enough cranberry for color.",
            historyNotes = "Perfected by Toby Cecchini in 1988 at the Odeon in Tribeca, New York, made world-famous by Sex and the City."
        ),
        CocktailRecipe(
            id = "bloody_mary",
            name = "Classic Bloody Mary",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("tomato_juice"), 4.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("celery_bitters"), 0.05f)
            ),
            garnish = "Crisp leafy celery stalk, lemon wedge, queen olive, and freshly cracked black pepper",
            description = "The ultimate savory brunch restorative: rich seasoned tomato juice, horseradish, citrus, and cold vodka.",
            historyNotes = "Claimed by Fernand Petiot at Harry's New York Bar in Paris in the 1920s; perfected at New York's St. Regis."
        ),
        CocktailRecipe(
            id = "white_russian",
            name = "White Russian",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("kahlua"), 0.75f),
                MeasuredIngredient(getIng("baileys"), 0.75f)
            ),
            garnish = "Cream swirls floating decadently over dark coffee liqueur",
            description = "Decadent dessert cocktail marrying clean vodka with dark roasted coffee liqueur and cold dairy cream.",
            historyNotes = "Evolved in the 1960s from the Black Russian; forever immortalized by 'The Dude' in The Big Lebowski (1998)."
        ),
        CocktailRecipe(
            id = "black_russian",
            name = "Black Russian",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("kahlua"), 1.0f)
            ),
            garnish = "Luxardo maraschino cherry over solid ice cubes",
            description = "Deep, robust, two-ingredient classic highlighting bittersweet roasted coffee beans and crisp vodka.",
            historyNotes = "Created in 1949 by Gustave Tops at the Hotel Metropole in Brussels for the American ambassador."
        ),
        CocktailRecipe(
            id = "vodka_martini",
            name = "Classic Vodka Martini (Kangaroo)",
            category = "Vodka",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.5f)
            ),
            garnish = "Three Spanish queen olives on a pick or lemon twist",
            description = "Crystalline, pristine, and razor-sharp: ultra-pure vodka with dry French vermouth.",
            historyNotes = "Popularized in post-WWII America as vodka overtook gin in national popularity."
        ),
        CocktailRecipe(
            id = "dirty_vodka_martini",
            name = "Dirty Vodka Martini",
            category = "Vodka",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.25f),
                MeasuredIngredient(getIng("olive"), 0.5f)
            ),
            garnish = "Three blue cheese stuffed olives",
            description = "Chilled to ice-shard perfection with savory olive brine giving cloudy, salty richness.",
            historyNotes = "A modern staple across American steakhouses."
        ),
        CocktailRecipe(
            id = "appletini",
            name = "Sour Apple Martini (Appletini)",
            category = "Vodka",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Thin granny smith green apple fan sliced into the glass",
            description = "Electric green, sweet and tart neon sour that dominated the late 1990s cocktail landscape.",
            historyNotes = "Created in 1996 at Lola's in West Hollywood, California; a pop culture sensation."
        ),
        CocktailRecipe(
            id = "lemon_drop_martini",
            name = "Lemon Drop Martini",
            category = "Vodka",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Sparkling crystalline sugar-crusted rim & lemon wheel",
            description = "Sweet-tart confection tasting precisely like the classic hard lemon candy.",
            historyNotes = "Invented by Norman Jay Hobday at Henry Africa's fern bar in San Francisco during the 1970s."
        ),
        CocktailRecipe(
            id = "sea_breeze",
            name = "Sea Breeze",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("cranberry_juice"), 3.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 1.5f)
            ),
            garnish = "Lime wheel floating on top",
            description = "Tart, bittersweet pink cooler combining ruby cranberry and grapefruit with clean vodka.",
            historyNotes = "Popularized in the 1970s and 80s by the Ocean Spray cranberry marketing campaigns."
        ),
        CocktailRecipe(
            id = "bay_breeze",
            name = "Bay Breeze (Hawaiian Sea Breeze)",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("cranberry_juice"), 2.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 2.5f)
            ),
            garnish = "Pineapple wedge & cocktail cherry",
            description = "Lush tropical highball softening tart cranberry with golden sweet pineapple juice.",
            historyNotes = "A staple of East Coast beach clubs and summer resort bars."
        ),
        CocktailRecipe(
            id = "cape_codder",
            name = "Cape Codder (Vodka Cranberry)",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("cranberry_juice"), 4.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.25f)
            ),
            garnish = "Lime wedge squeezed and dropped into the drink",
            description = "Crisp, uncomplicated bar staple named after Massachusetts' cranberry bogs.",
            historyNotes = "Formulated in 1945 in Massachusetts to promote cranberry agriculture."
        ),
        CocktailRecipe(
            id = "screwdriver",
            name = "Classic Screwdriver",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("orange_juice"), 4.0f)
            ),
            garnish = "Fresh orange wheel",
            description = "The classic two-ingredient morning refresher: freshly pressed orange juice with neutral grain vodka.",
            historyNotes = "Coined by American oil workers in the Persian Gulf in the 1940s who stirred vodka into their juice with tools."
        ),
        CocktailRecipe(
            id = "sex_on_the_beach",
            name = "Sex on the Beach",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 2.0f),
                MeasuredIngredient(getIng("cranberry_juice"), 2.0f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Orange slice & maraschino cherry",
            description = "Fruity, two-tone layered holiday drink with peach notes, orange, and cranberry.",
            historyNotes = "Created in 1987 in Florida by bartender Ted Pizio during Spring Break."
        ),
        CocktailRecipe(
            id = "harvey_wallbanger",
            name = "Harvey Wallbanger",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 4.0f),
                MeasuredIngredient(getIng("galliano_aperitivo"), 0.5f)
            ),
            garnish = "Orange wheel & brandied cherry with yellow herbal float",
            description = "Screwdriver elevated by an aromatic float of vanilla-anise herbal Italian liqueur.",
            historyNotes = "Created in the 1950s by Duke Antone at the Blackwatch Bar in Sunset Boulevard, Hollywood."
        ),
        CocktailRecipe(
            id = "greyhound_vodka",
            name = "Greyhound (Vodka)",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 4.0f)
            ),
            garnish = "Grapefruit wedge",
            description = "Clean, bitter-tart simplicity with pink ruby grapefruit and chilled vodka.",
            historyNotes = "A staple across mid-century American lounges."
        ),
        CocktailRecipe(
            id = "salty_dog_vodka",
            name = "Salty Dog (Vodka)",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 4.0f)
            ),
            garnish = "Coarse sea salt rim & grapefruit half-wheel",
            description = "Bittersweet pink grapefruit juice tempered with an intensely savory salted crust.",
            historyNotes = "Popularized in the 1960s across Western American diners and bars."
        ),
        CocktailRecipe(
            id = "blue_lagoon",
            name = "Blue Lagoon",
            category = "Vodka",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Lemon slice & bright cocktail cherry",
            description = "Electric neon blue citrus highball designed for poolside sunshine and beach parties.",
            historyNotes = "Created in the 1960s by Andy MacElhone at Harry's New York Bar in Paris."
        ),
        CocktailRecipe(
            id = "kamikaze",
            name = "The Kamikaze",
            category = "Vodka",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.0f),
                MeasuredIngredient(getIng("triple_sec"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 1.0f)
            ),
            garnish = "Lime wedge on rim",
            description = "Equal-parts sharp and bracing citrus sour served straight up or as a shooter.",
            historyNotes = "Originated on American military bases in Japan following WWII; exploded in 1970s discos."
        ),
        CocktailRecipe(
            id = "woo_woo",
            name = "Woo Woo",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("cranberry_juice"), 3.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Lime wedge",
            description = "Sweet, drinkable 1980s party drink pairing peach sweetness with tart cranberry.",
            historyNotes = "Legend says bartenders would shout 'Woo Woo!' each time someone ordered it in 1980s New York."
        ),
        CocktailRecipe(
            id = "french_martini",
            name = "French Martini",
            category = "Vodka",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("pineapple_juice"), 1.5f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Frothy crown with fresh raspberry garnish",
            description = "Decadent berry and pineapple cocktail creating a luscious velvety head of foam.",
            historyNotes = "Created in the late 1980s at one of Keith McNally's New York City bars."
        ),
        CocktailRecipe(
            id = "pornstar_martini",
            name = "Pornstar Martini (Passionfruit)",
            category = "Vodka",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("prosecco"), 2.0f)
            ),
            garnish = "Half a fresh passion fruit floating in the coupe with a shot of Prosecco on the side",
            description = "Modern global phenomenon: sweet tropical passionfruit sour served with an effervescent champagne sidecar.",
            historyNotes = "Created in 2002 by Douglas Ankrah at the Townhouse bar in Knightsbridge, London."
        ),
        CocktailRecipe(
            id = "transfusion",
            name = "The Transfusion (Golf Club Classic)",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("ginger_ale"), 3.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.25f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Frozen purple grapes & lime wheel",
            description = "The undisputed official drink of American golf country clubs: rich Concord grape and ginger ale.",
            historyNotes = "Country club legend famously favored by President Dwight D. Eisenhower."
        ),
        CocktailRecipe(
            id = "vodka_gimlet",
            name = "Vodka Gimlet",
            category = "Vodka",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f)
            ),
            garnish = "Lime wheel floating on top",
            description = "Ultra-clean variation of the British Gimlet allowing lime cordial and citrus to shine pure.",
            historyNotes = "Gained fame in mid-century American dining rooms."
        ),
        CocktailRecipe(
            id = "vodka_collins",
            name = "Vodka Collins",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel & Maraschino cherry",
            description = "Sparkling hard lemonade highlighting crisp neutral vodka.",
            historyNotes = "Popular 1960s summer highball."
        ),
        CocktailRecipe(
            id = "vodka_sour",
            name = "Classic Vodka Sour",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Angostura bitters drop on silky foam & lemon wheel",
            description = "Bright, refreshing citrus sour highlighting silky texture and bitter aromatics.",
            historyNotes = "Essential classic cocktail training standard."
        ),
        CocktailRecipe(
            id = "colorado_bulldog",
            name = "Colorado Bulldog",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.0f),
                MeasuredIngredient(getIng("kahlua"), 1.0f),
                MeasuredIngredient(getIng("baileys"), 1.0f),
                MeasuredIngredient(getIng("cola"), 1.5f)
            ),
            garnish = "Swirled cream head with fizzy bubbles",
            description = "White Russian charged with an effervescent splash of sweet caramel cola.",
            historyNotes = "Popularized in ski lodge bars across the Colorado Rockies."
        ),
        CocktailRecipe(
            id = "black_cow",
            name = "Black Cow Cocktail",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("kahlua"), 1.0f),
                MeasuredIngredient(getIng("cola"), 4.0f)
            ),
            garnish = "Cherry & drinking straw",
            description = "Adult coffee-cola float with rich vanilla and espresso notes.",
            historyNotes = "Mid-century American soda fountain inspired cocktail."
        ),
        CocktailRecipe(
            id = "chi_chi",
            name = "The Chi-Chi",
            category = "Vodka",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.BLENDED,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("malibu"), 1.0f),
                MeasuredIngredient(getIng("pineapple_juice"), 3.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Pineapple wedge & cocktail umbrella",
            description = "Tiki classic substituting vodka for rum in a frothy coconut-pineapple blend.",
            historyNotes = "Created in 1950s Hawaii, beloved by beachgoers who preferred clean vodka."
        ),
        CocktailRecipe(
            id = "godmother",
            name = "The Godmother",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("amaretto"), 1.0f)
            ),
            garnish = "Orange twist over a single solid rock",
            description = "Smooth pairing of sweet Italian almond liqueur and neutral vodka.",
            historyNotes = "Sister cocktail to the Godfather, born during the 1970s film era."
        ),
        CocktailRecipe(
            id = "white_gummy_bear",
            name = "White Gummy Bear",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Three gummy bears speared on a cocktail pick",
            description = "Candy-flavored sweet cocktail that tastes identical to the white pineapple gummy bear.",
            historyNotes = "Modern college campus and nightclub sensation."
        ),
        CocktailRecipe(
            id = "green_tea_shot",
            name = "Green Tea Cocktail",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("jameson"), 1.0f),
                MeasuredIngredient(getIng("vodka"), 0.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Lemon slice",
            description = "Tastes remarkably like sweetened green tea despite containing zero tea.",
            historyNotes = "Modern American bar phenomenon invented by Jameson brand ambassadors."
        ),
        CocktailRecipe(
            id = "mind_eraser",
            name = "The Mind Eraser",
            category = "Vodka",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.0f),
                MeasuredIngredient(getIng("kahlua"), 1.0f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Two fat straws for rapid drinking through layered bands",
            description = "Layered shooter sipped rapidly from bottom to top: coffee liqueur, vodka, then fizzy club soda.",
            historyNotes = "Late 20th century high-energy party cocktail."
        ),
        CocktailRecipe(
            id = "dirty_shirley",
            name = "The Dirty Shirley",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("grenadine"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 3.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.25f)
            ),
            garnish = "Three bright red Maraschino cherries and a lime wheel",
            description = "The nostalgic childhood mocktail spiked with clean vodka and tart lime.",
            historyNotes = "Named the official drink of the summer in 2022 by modern beverage media."
        ),
        CocktailRecipe(
            id = "vodka_tonic",
            name = "Vodka Tonic",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("tonic_water"), 4.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.25f)
            ),
            garnish = "Lime wedge squeezed and dropped in",
            description = "Bone-dry, refreshing highball where bitter quinine shines against clean spirits.",
            historyNotes = "Mid-century staple across golf clubs and international airlines."
        ),
        CocktailRecipe(
            id = "apple_cider_mule",
            name = "Apple Cider Mule",
            category = "Vodka",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("hard_cider"), 2.5f),
                MeasuredIngredient(getIng("ginger_beer"), 2.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f)
            ),
            garnish = "Cinnamon stick, apple fan & rosemary sprig in copper mug",
            description = "Crisp autumn mule combining spiced apple orchard cider with fiery ginger beer.",
            historyNotes = "Seasonal craft bar favorite celebrating orchard harvest."
        ),
        CocktailRecipe(
            id = "strawberry_vodka_lemonade",
            name = "Strawberry Vodka Lemonade",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Sliced fresh strawberries and lemon wheel",
            description = "Sweet, sun-ripened strawberry puree and fresh lemonade with crisp vodka.",
            historyNotes = "Warm-weather porch cooler popular across American summer patios."
        ),
        CocktailRecipe(
            id = "blackberry_vodka_spritz",
            name = "Blackberry Vodka Spritz",
            category = "Vodka",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 1.5f),
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Fresh blackberries, mint sprig & lemon twist",
            description = "Sparkling wine spritz enlivened with fresh blackberries and aromatic mint.",
            historyNotes = "Modern European-inspired aperitif for warm-weather garden gatherings."
        ),
        CocktailRecipe(
            id = "vodka_rickey",
            name = "Vodka Rickey",
            category = "Vodka",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("vodka"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 4.0f)
            ),
            garnish = "Two spent lime shells in the highball glass",
            description = "Zero sugar, bone dry highball delivering pure effervescence and fresh cold-pressed lime.",
            historyNotes = "Washington D.C. classic adaptation from the 1890s."
        )
    )
}
