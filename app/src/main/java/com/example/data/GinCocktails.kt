package com.example.data

import com.example.model.*

object GinCocktails {
    private fun getIng(id: String) = BarCraftData.getIngredient(id)

    val list: List<CocktailRecipe> = listOf(
        CocktailRecipe(
            id = "dry_martini",
            name = "Classic Dry Martini",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Spanish green olive or lemon twist",
            description = "The ultimate symbol of sophistication: crisp, crystalline, botanical and bone dry.",
            historyNotes = "Dating to the 1880s, popularized during the American Gilded Age and James Bond lore."
        ),
        CocktailRecipe(
            id = "dirty_martini",
            name = "Dirty Martini",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.5f),
                MeasuredIngredient(getIng("olive"), 0.5f)
            ),
            garnish = "Three Spanish olives & a splash of savory olive brine",
            description = "Savory, briny, silky cold version of the classic dry gin martini.",
            historyNotes = "Pioneered in New York City by John O'Connor in 1901 by muddling the bar's green olives."
        ),
        CocktailRecipe(
            id = "french_75",
            name = "French 75",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("prosecco"), 2.5f)
            ),
            garnish = "Long spiral lemon twist",
            description = "Effervescent, sparkling botanical drink with a potent hit of gin and crisp lemon.",
            historyNotes = "Named after the French 75mm field gun in WWI for its rapid and powerful kick."
        ),
        CocktailRecipe(
            id = "aviation",
            name = "Aviation",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.5f),
                MeasuredIngredient(getIng("lavender_bitters"), 0.05f)
            ),
            garnish = "Luxardo brandied cherry in base of glass",
            description = "A sky-blue floral pre-prohibition classic with delicate violet perfume.",
            historyNotes = "Created in 1916 by Hugo Ensslin at the Hotel Wallick in Times Square, New York."
        ),
        CocktailRecipe(
            id = "bees_knees",
            name = "Bee's Knees",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("honey_syrup"), 0.75f)
            ),
            garnish = "Lemon wheel or expressed peel",
            description = "Prohibition-era sour pairing sweet honey syrup with bright citrus to elevate aromatic gin.",
            historyNotes = "Invented during 1920s American Prohibition to smooth out bathtub gin."
        ),
        CocktailRecipe(
            id = "gimlet",
            name = "Classic Gimlet",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f)
            ),
            garnish = "Floating thin lime wheel",
            description = "Tart, brisk, and bracing lime sour that perfectly cuts through aromatic botanical gin.",
            historyNotes = "Originally formulated by British Royal Navy surgeon Sir Thomas Gimlette to stave off scurvy."
        ),
        CocktailRecipe(
            id = "ramos_gin_fizz",
            name = "Ramos Gin Fizz",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 2.0f)
            ),
            garnish = "Meringue foam crown rising above the glass rim",
            description = "New Orleans velvety wonder famous for its gravity-defying soufflé citrus foam.",
            historyNotes = "Invented in 1888 by Henry C. Ramos at the Imperial Cabinet Saloon in New Orleans."
        ),
        CocktailRecipe(
            id = "tom_collins",
            name = "Tom Collins",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 1.0f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 3.0f)
            ),
            garnish = "Lemon wheel & Maraschino cocktail cherry",
            description = "Sparkling lemonade cocktail with high effervescence and clean botanical bite.",
            historyNotes = "Documented by Jerry Thomas in 1876, linked to the Great Tom Collins Hoax of 1874."
        ),
        CocktailRecipe(
            id = "clover_club",
            name = "Clover Club",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("simple_syrup"), 0.25f)
            ),
            garnish = "Three fresh raspberries skewered on a bamboo pick",
            description = "Vibrant pink pre-prohibition sour with silky texture and berry undertones.",
            historyNotes = "Named after the historic Philadelphia men's club founded at the Bellevue-Stratford Hotel in 1896."
        ),
        CocktailRecipe(
            id = "corpse_reviver_no2",
            name = "Corpse Reviver No. 2",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 0.75f),
                MeasuredIngredient(getIng("lillet"), 0.75f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Lemon peel expressed over surface",
            description = "Equally proportioned masterwork with herbaceous complexity and an anise-perfumed rinse.",
            historyNotes = "Immortalized in Harry Craddock's 1930 Savoy Cocktail Book: 'Four of these taken in swift succession will unrevive the corpse again.'"
        ),
        CocktailRecipe(
            id = "last_word",
            name = "The Last Word",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 0.75f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("suze"), 0.75f)
            ),
            garnish = "Luxardo maraschino cherry at the bottom",
            description = "Equal parts herbaceous, bittersweet, and zesty sour with a chartreuse-colored hue.",
            historyNotes = "Created during Prohibition at the Detroit Athletic Club and revived in Seattle by Murray Stenson in 2004."
        ),
        CocktailRecipe(
            id = "southside",
            name = "Southside",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("mint_sprig"), 0.5f)
            ),
            garnish = "Single pristine mint leaf floated on the surface",
            description = "Bright, refreshing mint gin sour served up in a chilled coupe glass.",
            historyNotes = "A favorite of Al Capone's South Side Chicago gang, known as the gin counterpart to the Mojito."
        ),
        CocktailRecipe(
            id = "singapore_sling",
            name = "Singapore Sling",
            category = "Gin",
            glass = GlassType.HURRICANE,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("cherry"), 0.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.25f),
                MeasuredIngredient(getIng("pineapple_juice"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Pineapple wedge & brandied Luxardo cherry",
            description = "Legendary tropical sling combining fruit nectar, aromatic botanicals, and deep cherry.",
            historyNotes = "Formulated in 1915 by bartender Ngiam Tong Boon at the Long Bar in Raffles Hotel, Singapore."
        ),
        CocktailRecipe(
            id = "bramble",
            name = "The Bramble",
            category = "Gin",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f),
                MeasuredIngredient(getIng("grenadine"), 0.5f)
            ),
            garnish = "Fresh blackberries and a lemon slice",
            description = "Crisp gin sour crowned with a bleeding float of rich berry liqueur over crushed ice.",
            historyNotes = "Created in 1984 by legendary bartender Dick Bradsell at Fred's Club in Soho, London."
        ),
        CocktailRecipe(
            id = "hanky_panky",
            name = "Hanky Panky",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("fernet_branca"), 0.15f)
            ),
            garnish = "Expressed orange peel twist",
            description = "Stirred botanical masterpiece with bitter herbal alpine complexity from Fernet.",
            historyNotes = "Invented by Ada Coleman in 1903 at the Savoy American Bar for actor Charles Hawtrey."
        ),
        CocktailRecipe(
            id = "white_lady",
            name = "White Lady",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f)
            ),
            garnish = "Lemon twist",
            description = "Silky, citrus-forward European classic cousin to the Sidecar and Margarita.",
            historyNotes = "Created by Harry MacElhone in Paris at Harry's New York Bar in 1929."
        ),
        CocktailRecipe(
            id = "twentieth_century",
            name = "20th Century",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lillet"), 0.75f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("chocolate_bitters"), 0.05f)
            ),
            garnish = "Lemon peel",
            description = "Intriguing harmonious balance of crisp gin, apéritif wine, and subtle cacao.",
            historyNotes = "Created by British bartender C.A. Tuck in 1937, named after the 20th Century Limited passenger train."
        ),
        CocktailRecipe(
            id = "martinez",
            name = "The Martinez",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Orange twist & brandied cherry",
            description = "The rich, bittersweet grandfather of the Dry Martini, bridging whiskey and gin traditions.",
            historyNotes = "Documented by O.H. Byron in 1884, said to have originated in Martinez, California during the Gold Rush."
        ),
        CocktailRecipe(
            id = "monkey_gland",
            name = "Monkey Gland",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.5f),
                MeasuredIngredient(getIng("grenadine"), 0.25f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Orange twist",
            description = "Vibrant orange citrus profile with a subtle, exotic anise rinse from absinthe.",
            historyNotes = "Invented in the 1920s by Harry MacElhone at Harry's New York Bar in Paris."
        ),
        CocktailRecipe(
            id = "gin_and_tonic",
            name = "Gin and Tonic (Spanish Style)",
            category = "Gin",
            glass = GlassType.WINE,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("tonic_water"), 4.0f)
            ),
            garnish = "Grapefruit wedge, rosemary sprig, and crushed juniper berries",
            description = "Effervescent tonic water elevating gin's pine and citrus botanicals over crystal ice.",
            historyNotes = "Originated in British colonial India to make medicinal quinine palatable; elevated to culinary art in Spain."
        ),
        CocktailRecipe(
            id = "gin_basil_smash",
            name = "Gin Basil Smash",
            category = "Gin",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f)
            ),
            garnish = "Slapped fresh basil sprig bouquet",
            description = "Radiant green, herbaceous modern classic packed with fresh aromatic peppery basil.",
            historyNotes = "Invented in 2008 by Jörg Meyer at Le Lion in Hamburg, Germany."
        ),
        CocktailRecipe(
            id = "gin_rickey",
            name = "Gin Rickey",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("club_soda"), 4.0f)
            ),
            garnish = "Two spent lime shell halves dropped into the glass",
            description = "Bone-dry, zero-sugar sparkling highball with crisp carbonation and lime zest.",
            historyNotes = "Created at Shoomaker's in Washington D.C. in the 1880s, popular during F. Scott Fitzgerald's era."
        ),
        CocktailRecipe(
            id = "negroni",
            name = "Classic Negroni",
            category = "Gin",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("campari"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f)
            ),
            garnish = "Wide flame-expressed orange peel",
            description = "The undisputed king of Italian aperitifs: bold ruby red, bittersweet, and herbaceous.",
            historyNotes = "Created in 1919 at Caffè Casoni in Florence when Count Camillo Negroni requested gin instead of soda in his Americano."
        ),
        CocktailRecipe(
            id = "vesper_martini",
            name = "Vesper Martini",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("vodka"), 0.75f),
                MeasuredIngredient(getIng("lillet"), 0.5f)
            ),
            garnish = "Large, thin slice of lemon peel",
            description = "Potent, velvety smooth combination of botanical gin, neutral grain vodka, and aromatic wine.",
            historyNotes = "Invented by author Ian Fleming for James Bond in the 1953 novel Casino Royale."
        ),
        CocktailRecipe(
            id = "gin_mule",
            name = "London Mule (Gin Mule)",
            category = "Gin",
            glass = GlassType.COPPER_MUG,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("ginger_beer"), 4.0f)
            ),
            garnish = "Lime wheel & slapped mint sprig",
            description = "Zesty botanical twist on the mule, pairing piney juniper with spicy Jamaican ginger beer.",
            historyNotes = "Modern craft adaptation swapping Russian vodka for London dry gin in a frosty copper cup."
        ),
        CocktailRecipe(
            id = "floradora",
            name = "Floradora",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("grenadine"), 0.5f),
                MeasuredIngredient(getIng("ginger_beer"), 2.5f)
            ),
            garnish = "Fresh raspberries & lime wheel",
            description = "Pleasantly spicy, raspberry-tinged sparkling cooler named for a hit 1900 Broadway musical.",
            historyNotes = "Created in New York in 1901 honoring the Floradora chorus girls."
        ),
        CocktailRecipe(
            id = "greyhound_gin",
            name = "Greyhound (Gin)",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 4.0f)
            ),
            garnish = "Grapefruit half wheel",
            description = "Crisp, bittersweet refresher highlighting the marriage between grapefruit and gin botanicals.",
            historyNotes = "Recorded in Harry Craddock's 1930 Savoy Cocktail Book; named after Greyhound bus terminals."
        ),
        CocktailRecipe(
            id = "salty_dog_gin",
            name = "Salty Dog (Gin)",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("grapefruit_juice"), 4.0f)
            ),
            garnish = "Flaky coarse sea salt rim & grapefruit slice",
            description = "Bittersweet pink grapefruit highball balanced by a savory, mouthwatering salted rim.",
            historyNotes = "Evolved from the Greyhound in the 1950s by salting the rim to temper grapefruit bitterness."
        ),
        CocktailRecipe(
            id = "gin_sour",
            name = "Classic Gin Sour",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f)
            ),
            garnish = "Lemon wheel & Maraschino cherry",
            description = "The foundational gin sour showing perfect equilibrium between spirit, acid, and sugar.",
            historyNotes = "One of the cornerstones of Jerry Thomas's 1862 bartending guide."
        ),
        CocktailRecipe(
            id = "pink_gin",
            name = "Pink Gin",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.5f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.15f)
            ),
            garnish = "Lemon peel twist",
            description = "Bracing spirituous drink with dark aromatic bitters coloring chilled London dry gin pink.",
            historyNotes = "A staple of the British Royal Navy in the 19th century, pairing bitters for stomach ailments with gin rations."
        ),
        CocktailRecipe(
            id = "blackthorn",
            name = "Blackthorn Cocktail",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.5f),
                MeasuredIngredient(getIng("bokers_bitters"), 0.05f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Lemon peel",
            description = "Rich pre-prohibition cocktail layered with botanical depth and anise-scented bitters.",
            historyNotes = "Originated in New York's Hoffman House in the late 19th century."
        ),
        CocktailRecipe(
            id = "pegu_club",
            name = "Pegu Club",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("triple_sec"), 0.75f),
                MeasuredIngredient(getIng("lime_juice"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.05f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Lime wheel",
            description = "Exotic citrus sour with double bitters created for colonial officers in Burma.",
            historyNotes = "Signature cocktail of the Pegu Club in Rangoon, Burma, established in the 1880s."
        ),
        CocktailRecipe(
            id = "fitzgerald",
            name = "The Fitzgerald",
            category = "Gin",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.75f),
                MeasuredIngredient(getIng("angostura_bitters"), 0.1f)
            ),
            garnish = "Lemon wheel",
            description = "Crisp gin sour seasoned with an assertive hit of aromatic Angostura bitters.",
            historyNotes = "Created in the 1990s by modern cocktail pioneer Dale DeGroff at the Rainbow Room in New York."
        ),
        CocktailRecipe(
            id = "red_snapper",
            name = "Red Snapper (Gin Bloody Mary)",
            category = "Gin",
            glass = GlassType.HIGHBALL,
            iceRequired = true,
            technique = PrepTechnique.BUILT,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("tomato_juice"), 4.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("celery_bitters"), 0.05f)
            ),
            garnish = "Celery stalk, lemon wedge, and black pepper",
            description = "Savory, botanical spiced tomato cocktail with bold juniper notes cutting through tomato umami.",
            historyNotes = "Created at the King Cole Bar at the St. Regis New York when Fernand Petiot added gin to his spiced tomato mix."
        ),
        CocktailRecipe(
            id = "bronx_cocktail",
            name = "The Bronx",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.5f),
                MeasuredIngredient(getIng("sweet_vermouth"), 0.5f),
                MeasuredIngredient(getIng("noilly_prat"), 0.5f),
                MeasuredIngredient(getIng("orange_juice"), 1.0f)
            ),
            garnish = "Orange twist",
            description = "Perfect vermouth harmony with freshly squeezed orange juice and fragrant gin.",
            historyNotes = "Created around 1906 by Johnny Solon at the Waldorf-Astoria Hotel in New York."
        ),
        CocktailRecipe(
            id = "enzoni",
            name = "The Enzoni",
            category = "Gin",
            glass = GlassType.ROCKS,
            iceRequired = true,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("campari"), 1.0f),
                MeasuredIngredient(getIng("lemon_juice"), 0.75f),
                MeasuredIngredient(getIng("simple_syrup"), 0.5f)
            ),
            garnish = "Green seedless grapes on a cocktail pick",
            description = "Modern classic hybrid of a Gin Sour and Negroni with muddled white grapes.",
            historyNotes = "Created by Vincenzo Errico in the early 2000s at London's legendary Milk & Honey."
        ),
        CocktailRecipe(
            id = "casino_cocktail",
            name = "Casino Cocktail",
            category = "Gin",
            glass = GlassType.COUPE,
            iceRequired = false,
            technique = PrepTechnique.SHAKEN,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("lemon_juice"), 0.5f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Luxardo maraschino cherry & lemon peel",
            description = "Crisp Old Tom or London Dry gin sour sweetened with dry marasca cherry liqueur.",
            historyNotes = "Published in Hugo Ensslin's 1916 Recipes for Mixed Drinks."
        ),
        CocktailRecipe(
            id = "tuxedo_no2",
            name = "Tuxedo No. 2",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.0f),
                MeasuredIngredient(getIng("noilly_prat"), 1.0f),
                MeasuredIngredient(getIng("maraschino_liqueur"), 0.25f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f),
                MeasuredIngredient(getIng("absinthe"), 0.05f)
            ),
            garnish = "Lemon twist & brandied cherry",
            description = "Silky, aromatic dry martini variation laced with maraschino and an absinthe whisper.",
            historyNotes = "Named after the Tuxedo Club in Orange County, New York in the late 1880s."
        ),
        CocktailRecipe(
            id = "alaska_cocktail",
            name = "Alaska Cocktail",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 2.25f),
                MeasuredIngredient(getIng("suze"), 0.75f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Lemon twist",
            description = "Gold-hued herbal dry cocktail pairing botanical gin with herbal alpine gentian liqueur.",
            historyNotes = "Featured in the 1930 Savoy Cocktail Book, named in tribute to the 49th US state."
        ),
        CocktailRecipe(
            id = "bijou",
            name = "The Bijou",
            category = "Gin",
            glass = GlassType.MARTINI,
            iceRequired = false,
            technique = PrepTechnique.STIRRED,
            ingredients = listOf(
                MeasuredIngredient(getIng("gin"), 1.0f),
                MeasuredIngredient(getIng("sweet_vermouth"), 1.0f),
                MeasuredIngredient(getIng("suze"), 1.0f),
                MeasuredIngredient(getIng("orange_bitters"), 0.05f)
            ),
            garnish = "Luxardo cherry & expressed lemon peel",
            description = "Jewel of a cocktail: gin (diamond), sweet vermouth (ruby), and herbal yellow liqueur (topaz).",
            historyNotes = "Created in the 1890s by Harry Johnson, pioneer of modern American bartending."
        )
    )
}
