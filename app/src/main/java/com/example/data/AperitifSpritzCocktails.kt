package com.example.data

import com.example.model.*

object AperitifSpritzCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "aperol_spritz",
            name = "Classic Aperol Spritz (Veneziano)",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("aperol"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Sun-ripened orange half-wheel & green olive in a bulbous wine glass",
            description = "The official 3-2-1 formula: sparkling Italian Prosecco, bittersweet orange Aperol, and soda.",
            historyNotes = "Originated in the Veneto region in the 1920s; now a worldwide symbol of Italian aperitivo culture."
        ),
        CocktailRecipe(
            id = "campari_spritz",
            name = "Campari Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("campari"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Orange slice & green Castelvetrano olive",
            description = "Bold ruby-red cousin of the Aperol Spritz: drier, bolder, and more assertively herbal and bitter.",
            historyNotes = "A staple of Milanese and Venetian cafés for over half a century."
        ),
        CocktailRecipe(
            id = "americano",
            name = "Classic Americano",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Orange half-wheel & lemon peel",
            description = "The historic foundation of the Negroni: equal parts Campari and sweet vermouth lengthened with crisp soda.",
            historyNotes = "Created in the 1860s at Gaspare Campari's bar in Milan; James Bond's very first drink in Casino Royale."
        ),
        CocktailRecipe(
            id = "negroni_sbagliato",
            name = "Negroni Sbagliato (Broken Negroni)",
            category = "Apéritif",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("prosecco"), 2.0f)
            ),
            garnish = "Orange slice inside a rocks tumbler",
            description = "A famous happy accident where sparkling wine replaces gin, yielding a lighter, fizzy bittersweet libation.",
            historyNotes = "Created in 1972 by Mirko Stocchetto at Bar Basso in Milan when he grabbed Prosecco instead of gin."
        ),
        CocktailRecipe(
            id = "hugo_spritz",
            name = "Hugo Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f)
            ),
            garnish = "Clustered slapped mint leaves and fresh lime wheels",
            description = "Brisk Alpine aperitif brimming with delicate floral elderflower, effervescent bubbles, and cool mint.",
            historyNotes = "Invented in 2005 by Roland Gruber in the South Tyrol region of the Italian Alps."
        ),
        CocktailRecipe(
            id = "limoncello_spritz",
            name = "Limoncello Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Meyer lemon wheels & fragrant fresh basil sprig",
            description = "Amalfi Coast sunshine in a glass: sweet lemon peel aromatics dancing with crisp Italian sparkling wine.",
            historyNotes = "Iconic Mediterranean aperitivo inspired by southern Italian coastal citrus groves."
        ),
        CocktailRecipe(
            id = "garibaldi",
            name = "The Garibaldi",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 4.0f)
            ),
            garnish = "Orange wedge dipped in Campari on the rim",
            description = "High-speed aerated 'fluffy' fresh orange juice poured over ruby Campari, creating velvety cloud layers.",
            historyNotes = "Named for General Giuseppe Garibaldi who unified Northern (Campari) and Southern (Oranges) Italy in 1861."
        ),
        CocktailRecipe(
            id = "bicicletta",
            name = "La Bicicletta",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.5f),
                MeasuredIngredient(getIng("sauvignon_blanc"), 3.0f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Lemon wheel and orange half-moon",
            description = "Northern Italian café tradition: dry white wine, Campari, and soda water.",
            historyNotes = "Named after the elderly Italian gentlemen who drank them in piazza cafes before wobbling home on bicycles."
        ),
        CocktailRecipe(
            id = "lillet_spritz",
            name = "Lillet Blanc Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("lillet"), 2.0f),
                MeasuredIngredient(getIng("tonic_water"), 3.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Thin cucumber ribbon, fresh mint sprig & strawberry slice",
            description = "Elegant Bordeaux wine apéritif infused with sweet orange peel, quinquina, and crisp effervescence.",
            historyNotes = "Bordeaux apéritif wine crafted in Podensac since 1872; a Parisian garden party fixture."
        ),
        CocktailRecipe(
            id = "suze_tonic",
            name = "Suze & Tonic",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("suze"), 1.5f),
                MeasuredIngredient(getIng("tonic_water"), 4.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.25f)
            ),
            garnish = "Lemon wheel & fresh rosemary sprig",
            description = "Earthy, bittersweet yellow gentian root French apéritif balanced with sparkling quinine tonic.",
            historyNotes = "Suze was created in 1889 by Fernand Moureaux in Switzerland and Paris; immortalized by Picasso."
        ),
        CocktailRecipe(
            id = "dubonnet_cocktail",
            name = "The Dubonnet Cocktail",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("dubonnet"), 2.0f)
            ),
            garnish = "Expressed lemon twist dropped into the glass",
            description = "Rich, aromatized ruby French wine with herbs, spices, and quinine balanced by crisp dry gin.",
            historyNotes = "The lifelong daily before-lunch aperitif of both Queen Elizabeth II and Queen Elizabeth the Queen Mother."
        ),
        CocktailRecipe(
            id = "bonal_highball",
            name = "Bonal Highball",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("bonal"), 2.5f),
                MeasuredIngredient(getIng("club_soda"), 4.0f)
            ),
            garnish = "Expressed orange twist and brandied cherry",
            description = "French mistelle fortified with gentian root and cinchona bark, delivering herbal plum notes.",
            historyNotes = "Created in 1865 by monk-physician Brother Raphaël in the French Chartreuse mountains: 'Ouvre l'appétit'."
        ),
        CocktailRecipe(
            id = "vermouth_cassis",
            name = "Vermouth Cassis",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("noilly_prat"), 2.0f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon peel twist",
            description = "Crisp Mediterranean dry vermouth kissed with dark blackcurrant berry and effervescent soda.",
            historyNotes = "Historic French café staple dating to the late 19th century, celebrated in Hemingway's novels."
        ),
        CocktailRecipe(
            id = "bamboo_cocktail",
            name = "The Bamboo Cocktail",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("oloroso_sherry"), 1.5f),
                MeasuredIngredient(getIng("noilly_prat"), 1.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Expressed lemon twist",
            description = "Dry, nutty, low-ABV masterwork weaving Spanish sherry and French dry vermouth.",
            historyNotes = "Created in the 1890s by Louis Eppinger at the Grand Hotel in Yokohama, Japan."
        ),
        CocktailRecipe(
            id = "adonis_cocktail",
            name = "The Adonis",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("oloroso_sherry"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.1f)
            ),
            garnish = "Flame expressed orange peel",
            description = "Nutty Andalusian sherry softened with sweet herbal Italian vermouth and bright orange bitters.",
            historyNotes = "Created in 1884 to honor the record-breaking 500th performance of the Broadway musical Adonis."
        ),
        CocktailRecipe(
            id = "rose_cocktail",
            name = "The Rose Cocktail",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("noilly_prat"), 2.0f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f)
            ),
            garnish = "Red maraschino cherry in the center",
            description = "Delicate pink Parisian cocktail that dominated fashionable French café society.",
            historyNotes = "Created in the 1920s by Johnny Mitta at the Chatham Hotel in Paris."
        ),
        CocktailRecipe(
            id = "cardinale",
            name = "The Cardinale",
            category = "Apéritif",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("campari"), 0.75f),
                MeasuredIngredient(getIng("noilly_prat"), 0.75f)
            ),
            garnish = "Lemon peel twist",
            description = "A drier, crisper Roman variation of the Negroni substituting dry French vermouth for sweet vermouth.",
            historyNotes = "Created in 1950 by Giovanni Raimondo at the Hotel Excelsior in Rome for a German Cardinal."
        ),
        CocktailRecipe(
            id = "milano_torino",
            name = "Milano-Torino (Mi-To)",
            category = "Apéritif",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f)
            ),
            garnish = "Orange twist and green olive",
            description = "The historic Italian original: equal parts Milan's Campari and Turin's sweet red vermouth.",
            historyNotes = "Served since the 1860s at Gaspare Campari's Camparino bar in the Galleria Vittorio Emanuele II."
        ),
        CocktailRecipe(
            id = "pimms_cup",
            name = "Pimm's No. 1 Cup",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("cappelletti"), 1.0f),
                MeasuredIngredient(getIng("ginger_beer"), 3.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f)
            ),
            garnish = "Cucumber spear, strawberry halves, mint sprig, and orange wheel",
            description = "The quintessential British garden party refresher: botanicals, herbal spice, and garden vegetables.",
            historyNotes = "Originated in London in the 1840s by James Pimm; the iconic drink of the Wimbledon tennis championship."
        ),
        CocktailRecipe(
            id = "cynar_spritz",
            name = "Cynar Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("fernet_branca"), 1.5f),
                MeasuredIngredient(getIng("club_soda"), 1.5f)
            ),
            garnish = "Castelvetrano olive & fresh orange slice",
            description = "Savory, bittersweet amaro spritz layered with caramel sweetness and herbal earthiness.",
            historyNotes = "A beloved Venetian bacaro tradition for afternoon cicchetti nibbling."
        ),
        CocktailRecipe(
            id = "select_spritz",
            name = "Venetian Select Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("select_aperitivo"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Single large green Venetian olive skewered on a pick",
            description = "The authentic Venetian Spritz born in Venice with 30 botanical extracts and an olive.",
            historyNotes = "Select was founded in 1920 in the historic Castello district of Venice; the true local choice."
        ),
        CocktailRecipe(
            id = "cappelletti_spritz",
            name = "Cappelletti Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("cappelletti"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Grapefruit wedge & rosemary sprig",
            description = "Artisanal wine-based red aperitivo from the Italian Dolomites with earthy gentian complexity.",
            historyNotes = "Produced since 1909 by the Cappelletti family in Aldeno, Trentino."
        ),
        CocktailRecipe(
            id = "nonino_spritz",
            name = "Nonino Botanical Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("nonino_aperitivo"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Lemon slice & fresh mint",
            description = "Grappa-based northern Italian aperitivo infused with sun-dried botanicals, gentian, and citrus.",
            historyNotes = "Crafted in Friuli by the pioneering Nonino family distillery."
        ),
        CocktailRecipe(
            id = "galliano_spritz",
            name = "Galliano L'Aperitivo Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("galliano_aperitivo"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 1.0f)
            ),
            garnish = "Pink grapefruit slice & basil sprig",
            description = "Bright crimson Italian bitter brimming with Mediterranean citrus, bergamot, chinotto, and orange.",
            historyNotes = "Artisanal infusion using 50 distinct herbs and spices from Livorno, Italy."
        ),
        CocktailRecipe(
            id = "campari_soda",
            name = "Campari & Soda",
            category = "Apéritif",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 2.0f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Expressed orange peel twist over ice",
            description = "Simplicity at its purest: pristine carbonation cutting through intense bitter gentian and orange peel.",
            historyNotes = "First pre-bottled cocktail in history (1932), famously packaged in Fortunato Depero's conical bottle."
        ),
        CocktailRecipe(
            id = "aperol_sour",
            name = "Aperol Sour",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("aperol"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Orange twist & Angostura bitters swirl on foam",
            description = "Low-ABV velvet sour transforming bittersweet rhubarb-orange into a silky cocktail.",
            historyNotes = "Contemporary craft bar favorite for mindful drinkers."
        ),
        CocktailRecipe(
            id = "french_blonde",
            name = "The French Blonde",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("lillet"), 2.0f),
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.25f)
            ),
            garnish = "Thin grapefruit peel spiral",
            description = "Lush, fragrant aperitif with bright pink grapefruit and French apéritif wine.",
            historyNotes = "Modern classic that gained worldwide acclaim as Taylor Swift's favorite cocktail."
        ),
        CocktailRecipe(
            id = "white_negroni",
            name = "White Negroni",
            category = "Apéritif",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("suze"), 1.0f),
                MeasuredIngredient(getIng("lillet"), 1.0f)
            ),
            garnish = "Expressed lemon peel coin",
            description = "Pale golden modern masterpiece matching gin with French gentian liqueur and apéritif wine.",
            historyNotes = "Created in 2001 by British bartender Wayne Collins at Vinexpo in Bordeaux."
        ),
        CocktailRecipe(
            id = "fernet_and_coke",
            name = "Fernet con Coca (Fernet & Coke)",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("fernet_branca"), 2.0f),
                MeasuredIngredient(getIng("cola"), 4.0f)
            ),
            garnish = "Frothy crown with lemon wedge",
            description = "Argentina's beloved national drink: inky mentholated bitter amaro mingling with caramel cola.",
            historyNotes = "Born in Córdoba, Argentina; over 75% of all Fernet-Branca produced worldwide is consumed in Argentina."
        ),
        CocktailRecipe(
            id = "amaro_sour",
            name = "Classic Amaro Sour",
            category = "Apéritif",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("cappelletti"), 1.5f),
                MeasuredIngredient(getIng("bourbon"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Brandied cocktail cherry & lemon wheel",
            description = "Herbal, velvety sour where bitter Italian amaro takes center stage supported by Kentucky bourbon.",
            historyNotes = "Pioneered by modern craft bartenders in the late 2000s."
        ),
        CocktailRecipe(
            id = "porto_tonico",
            name = "Porto Tónico",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("ruby_port"), 2.0f),
                MeasuredIngredient(getIng("tonic_water"), 4.0f)
            ),
            garnish = "Fresh mint sprig, orange wheel, and cracked pink peppercorns",
            description = "Portugal's refreshing summer staple: fortified Port wine lifted with sparkling quinine tonic.",
            historyNotes = "Everyday late-afternoon ritual along the Douro riverbank in Porto."
        ),
        CocktailRecipe(
            id = "suze_gimlet",
            name = "Suze Alpine Gimlet",
            category = "Apéritif",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("suze"), 1.5f),
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Lime wheel floating in green-gold liquid",
            description = "Earthy gentian bitterness married to tart lime and crisp gin.",
            historyNotes = "Modern Parisian cocktail bar favorite."
        ),
        CocktailRecipe(
            id = "dubonnet_highball",
            name = "Dubonnet Highball",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("dubonnet"), 2.5f),
                MeasuredIngredient(getIng("ginger_beer"), 3.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.25f)
            ),
            garnish = "Lemon slice & fresh mint",
            description = "Deep spiced French wine highball with fiery ginger carbonation.",
            historyNotes = "Classic European warm-weather refresher."
        ),
        CocktailRecipe(
            id = "spiced_bitters_spritz",
            name = "Spiced Bitters Blood Orange Spritz",
            category = "Apéritif",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("campari"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f),
                MeasuredIngredient(getIng("prosecco"), 3.0f),
                MeasuredIngredient(getIng("spiced_bitters"), 0.1f)
            ),
            garnish = "Dehydrated orange wheel & cinnamon stick",
            description = "Festive winter spritz layering cinnamon and clove bitters with bitter orange bubbles.",
            historyNotes = "Modern holiday aperitivo tradition."
        ),
        CocktailRecipe(
            id = "suze_lemon_fizz",
            name = "Suze Lemon Fizz",
            category = "Apéritif",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("suze"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel & slapped mint",
            description = "Sunny yellow fizz balancing gentian roots with effervescent lemonade.",
            historyNotes = "French bistro terrace classic."
        )
    )
}
