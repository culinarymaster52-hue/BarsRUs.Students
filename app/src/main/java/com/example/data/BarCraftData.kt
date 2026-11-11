package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.model.*
import com.example.ui.theme.*

object BarCraftData {

    // === INGREDIENTS ===
    private val coreIngredients: List<Ingredient> = listOf(
        // === SPIRITS & LIQUEURS ===
        Ingredient("bourbon", "Bourbon Whiskey", IngredientType.SPIRIT, 45.0f, LiquorAmber, 2.0f, "oz", "Rich caramel, charred oak, and vanilla", 25.4f),
        Ingredient("rye", "Rye Whiskey", IngredientType.SPIRIT, 50.0f, LiquorAmber, 2.0f, "oz", "Peppery spice, cinnamon, and toasted grain", 25.4f),
        Ingredient("jack_daniels", "Jack Daniel's Whiskey", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "Charcoal mellowed Tennessee whiskey with maple sweetness", 25.4f),
        Ingredient("johnnie_walker", "Johnnie Walker Scotch", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "Peaty smoke, malt barley, and heather honey", 25.4f),
        Ingredient("jameson", "Jameson Irish Whiskey", IngredientType.SPIRIT, 40.0f, LiquorGold, 1.5f, "oz", "Triple-distilled, velvety smooth with green apple notes", 25.4f),
        Ingredient("crown_royal", "Crown Royal Canadian", IngredientType.SPIRIT, 40.0f, LiquorGold, 1.5f, "oz", "Blended Canadian whisky with creamy vanilla finish", 25.4f),
        Ingredient("hennessy", "Hennessy Cognac", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "French oak, toasted almond, and dried apricot", 25.4f),
        Ingredient("soju_jinro", "Jinro Chamisul Soju", IngredientType.SPIRIT, 20.0f, LiquorClear, 2.0f, "oz", "Korea's clean, neutral spirit filtered through bamboo charcoal", 25.4f),
        Ingredient("vodka", "Vodka (Grey Goose / Absolut)", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Clean, neutral, crisp, and velvety smooth", 25.4f),
        Ingredient("gin", "London Dry Gin (Bombay / Tanqueray)", IngredientType.SPIRIT, 47.0f, LiquorClear, 1.5f, "oz", "Sharp piney juniper, coriander, and citrus peel", 25.4f),
        Ingredient("white_rum", "Bacardi White Rum", IngredientType.SPIRIT, 40.0f, LiquorClear, 2.0f, "oz", "Fresh sugarcane, light tropical ester, and floral", 25.4f),
        Ingredient("dark_rum", "Dark Demerara Rum", IngredientType.SPIRIT, 40.0f, LiquorDarkBrown, 2.0f, "oz", "Deep molasses, dried fig, allspice, and cocoa", 25.4f),
        Ingredient("captain_morgan", "Captain Morgan Spiced Rum", IngredientType.SPIRIT, 35.0f, LiquorAmber, 2.0f, "oz", "Caribbean rum infused with natural vanilla and spices", 25.4f),
        Ingredient("cachaca_51", "Cachaça 51", IngredientType.SPIRIT, 40.0f, LiquorClear, 2.0f, "oz", "Brazilian sugarcane distillate, vegetal and grassy", 25.4f),
        Ingredient("tequila", "Patrón Tequila Blanco", IngredientType.SPIRIT, 40.0f, LiquorClear, 2.0f, "oz", "Bright blue agave, white pepper, and citrus", 25.4f),
        Ingredient("triple_sec", "Triple Sec / Cointreau", IngredientType.SPIRIT, 40.0f, LiquorTripleSec, 0.75f, "oz", "Sweet and bitter orange peel liqueur", 25.4f),
        Ingredient("jagermeister", "Jägermeister Herbal Liqueur", IngredientType.SPIRIT, 35.0f, Color(0xF02A180E), 1.0f, "oz", "56 natural herbs, blossoms, roots and fruits", 25.4f),
        Ingredient("fernet_branca", "Fernet-Branca Amaro", IngredientType.SPIRIT, 39.0f, Color(0xF51E140C), 1.0f, "oz", "Milanese inky amaro with intense mint, gentian and myrrh", 25.4f),
        Ingredient("kahlua", "Coffee Liqueur (Kahlúa)", IngredientType.SPIRIT, 20.0f, LiquorDarkBrown, 1.0f, "oz", "Dark roasted Arabica coffee beans and sugarcane", 25.4f),
        Ingredient("baileys", "Baileys Irish Cream", IngredientType.SPIRIT, 17.0f, Color(0xFFD7C7B2), 1.5f, "oz", "Irish whiskey homogenized with fresh dairy cream and cocoa", 25.4f),
        Ingredient("malibu", "Malibu Coconut Rum", IngredientType.SPIRIT, 21.0f, LiquorClear, 1.5f, "oz", "Sweet Caribbean rum with aromatic coconut aroma", 25.4f),
        Ingredient("amaretto", "Disaronno Amaretto Liqueur", IngredientType.SPIRIT, 28.0f, Color(0xD99E4717), 1.5f, "oz", "Italian almond & apricot pit sweet liqueur", 25.4f),
        Ingredient("mezcal", "Del Maguey Espadín Mezcal", IngredientType.SPIRIT, 45.0f, LiquorClear, 1.5f, "oz", "Artisanal smoky roasted agave distillate from Oaxaca", 25.4f),
        Ingredient("maraschino_liqueur", "Luxardo Maraschino Liqueur", IngredientType.SPIRIT, 32.0f, LiquorClear, 0.5f, "oz", "Distilled Marasca sour cherry dry liqueur", 25.4f),
        Ingredient("absinthe", "Absinthe Verte", IngredientType.SPIRIT, 60.0f, Color(0xCC6B8E23), 0.1f, "dash", "High-proof wormwood, anise, and fennel green spirit", 25.4f),

        // === GLOBAL TOP 100 SPIRITS & LIQUEURS ===
        Ingredient("smirnoff", "Smirnoff No. 21 Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #5: Triple-distilled, 10x charcoal-filtered classic Russian-heritage vodka", 25.4f),
        Ingredient("absolut", "Absolut Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #18: Swedish winter wheat vodka with smooth, full-bodied grain character", 25.4f),
        Ingredient("grey_goose", "Grey Goose French Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #50: Soft winter wheat from Picardy & spring water from Gensac-la-Pallue", 25.4f),
        Ingredient("ketel_one", "Ketel One Dutch Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #95: Distilled in copper pot stills by the Nolet family in Schiedam", 25.4f),
        Ingredient("ciroc", "Cîroc Snap Frost Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #85: Cold-fermented French Ugni Blanc and Mauzac Blanc grapes", 25.4f),
        Ingredient("skyy", "Skyy Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #52: Quadruple-distilled American grain vodka with clean profile", 25.4f),
        Ingredient("stolichnaya", "Stolichnaya (Stoli) Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #67: Quintessential Latvian wheat & rye vodka with light pepper finish", 25.4f),
        Ingredient("finlandia", "Finlandia Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #73: Six-row barley fermented with untreated glacial spring water", 25.4f),
        Ingredient("russian_standard", "Russian Standard Vodka", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #76: St. Petersburg winter wheat and glacial waters of Lake Ladoga", 25.4f),
        Ingredient("chum_churum", "Chum-Churum Soju", IngredientType.SPIRIT, 17.5f, LiquorClear, 2.0f, "oz", "Global #4: Korean alkaline water soju with smooth, light sweetness", 25.4f),
        Ingredient("tanqueray", "Tanqueray London Dry Gin", IngredientType.SPIRIT, 47.3f, LiquorClear, 1.5f, "oz", "Global #92: Four-botanical timeless gin: juniper, coriander, angelica, licorice", 25.4f),
        Ingredient("bombay_sapphire", "Bombay Sapphire Gin", IngredientType.SPIRIT, 47.0f, LiquorClear, 1.5f, "oz", "Global #77: Ten precious botanicals infused via vapor distillation basket", 25.4f),
        Ingredient("beefeater", "Beefeater London Dry Gin", IngredientType.SPIRIT, 44.0f, LiquorClear, 1.5f, "oz", "Global #89: Nine steeped botanicals including Seville orange peel and lemon", 25.4f),
        Ingredient("gordons", "Gordon's London Dry Gin", IngredientType.SPIRIT, 37.5f, LiquorClear, 1.5f, "oz", "Global #59: Alexander Gordon's 1769 recipe with handpicked Tuscan juniper", 25.4f),
        Ingredient("ginebra_san_miguel", "Ginebra San Miguel Gin", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #8: Heavy juniper Dutch-style gin distilled in the Philippines since 1834", 25.4f),
        Ingredient("jim_beam", "Jim Beam Kentucky Bourbon", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "Global #27: America's benchmark bourbon aged 4 years in newly charred white oak", 25.4f),
        Ingredient("ballantines", "Ballantine's Blended Scotch", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "Global #33: Complex blend of 50 single malts led by Miltonduff and Glenburgie", 25.4f),
        Ingredient("chivas_regal", "Chivas Regal 12 Year", IngredientType.SPIRIT, 40.0f, LiquorAmber, 1.5f, "oz", "Global #42: Strathisla Speyside malt backbone with honey and orchard apples", 25.4f),
        Ingredient("kakubin", "Suntory Kakubin Japanese Whisky", IngredientType.SPIRIT, 40.0f, LiquorGold, 1.5f, "oz", "Global #72: The yellow-label icon of Tokyo highball bars with sweet vanilla", 25.4f),
        Ingredient("officers_choice", "Officer's Choice Whisky", IngredientType.SPIRIT, 42.8f, LiquorAmber, 1.5f, "oz", "Global #3: Top Indian grain whisky crafted from Indian malt and grain neutral spirits", 25.4f),
        Ingredient("royal_stag", "Royal Stag Indian Whisky", IngredientType.SPIRIT, 42.8f, LiquorAmber, 1.5f, "oz", "Global #14: Blend of imported Scotch malts and selected Indian grain spirits", 25.4f),
        Ingredient("imperial_blue", "Imperial Blue Grain Whisky", IngredientType.SPIRIT, 42.8f, LiquorAmber, 1.5f, "oz", "Global #15: Smooth Indian blend of imported Scotch malts and local grains", 25.4f),
        Ingredient("black_velvet", "Black Velvet Canadian Whisky", IngredientType.SPIRIT, 40.0f, LiquorGold, 1.5f, "oz", "Global #96: Canadian whisky blended at birth for velvet-smooth delivery", 25.4f),
        Ingredient("tanduay", "Tanduay Asian Dark Rum", IngredientType.SPIRIT, 40.0f, LiquorDarkBrown, 1.5f, "oz", "Global #12: Philippine sugarcane rum aged in charred bourbon barrels", 25.4f),
        Ingredient("havana_club", "Havana Club 3 Años Rum", IngredientType.SPIRIT, 40.0f, Color(0xEED9B76C), 2.0f, "oz", "Global #49: Authentic Cuban sugarcane rum aged 3 years for crisp Mojitos", 25.4f),
        Ingredient("ron_barcelo", "Ron Barceló Imperial Rum", IngredientType.SPIRIT, 38.0f, LiquorAmber, 1.5f, "oz", "Global #99: Dominican rum made from 100% estate-grown sugarcane juice", 25.4f),
        Ingredient("pitu_cachaca", "Pitú Cachaça", IngredientType.SPIRIT, 40.0f, LiquorClear, 2.0f, "oz", "Global #19: Brazilian pure sugarcane spirit from Pernambuco", 25.4f),
        Ingredient("velho_barreiro", "Velho Barreiro Cachaça", IngredientType.SPIRIT, 39.0f, Color(0xD9E9D7A5), 2.0f, "oz", "Global #23: Brazilian cachaça aged in large native Jequitibá-Rosa wooden vats", 25.4f),
        Ingredient("sauza_tequila", "Sauza Silver Tequila", IngredientType.SPIRIT, 40.0f, LiquorClear, 1.5f, "oz", "Global #57: Double-distilled fresh blue agave tequila from Tequila, Jalisco", 25.4f),
        Ingredient("emperador_brandy", "Emperador Solera Brandy", IngredientType.SPIRIT, 37.5f, LiquorAmber, 1.5f, "oz", "Global #2: World's best-selling brandy, crafted in Spanish solera barrels", 25.4f),
        Ingredient("mcdowells_brandy", "McDowell's No. 1 Brandy", IngredientType.SPIRIT, 42.8f, LiquorAmber, 1.5f, "oz", "Global #26: Rich Indian grape brandy infused with warm honey notes", 25.4f),
        Ingredient("luzhou_laojiao", "Luzhou Laojiao Baijiu", IngredientType.SPIRIT, 52.0f, LiquorClear, 1.0f, "oz", "Global #7: Historic Chinese baijiu fermented in Ming dynasty pits since 1573", 25.4f),
        Ingredient("iichiko_shochu", "Iichiko Silhouette Shochū", IngredientType.SPIRIT, 25.0f, LiquorClear, 2.0f, "oz", "Global #25: Honkaku barley shochu from Oita, Japan; clean and aromatic", 25.4f),
        Ingredient("muginoka_shochu", "Muginoka Barley Shochū", IngredientType.SPIRIT, 25.0f, LiquorClear, 2.0f, "oz", "Global #100: Mellow Japanese barley shochu with toasted grain notes", 25.4f),
        Ingredient("zubrowka", "Żubrówka Bison Grass Vodka", IngredientType.SPIRIT, 40.0f, Color(0x99DCEDC8), 1.5f, "oz", "Global #37: Infused with handpicked wild bison grass from Białowieża forest", 25.4f),
        Ingredient("ricard_pastis", "Ricard Pastis de Marseille", IngredientType.SPIRIT, 45.0f, Color(0xD9E6C265), 1.0f, "oz", "Global #39: Star anise, Mediterranean licorice root, and Provençal herbs", 25.4f),
        Ingredient("yeni_raki", "Yeni Rakı", IngredientType.SPIRIT, 45.0f, LiquorClear, 1.5f, "oz", "Global #62: Twice-distilled Turkish grape spirit with fragrant Çeşme aniseed", 25.4f),
        Ingredient("antioqueno", "Antioqueño Aguardiente", IngredientType.SPIRIT, 29.0f, LiquorClear, 1.5f, "oz", "Global #51: Colombia's beloved sugarcane spirit flavored with pure green anise", 25.4f),

        // === APÉRITIFS & VERMOUTHS ===
        Ingredient("lillet", "Lillet Blanc", IngredientType.APERITIF, 17.0f, Color(0x99F4EDBE), 1.5f, "oz", "French apéritif wine from Bordeaux made with a blend of regional wines and fruit liqueurs", 25.4f),
        Ingredient("suze", "Suze Gentian Apéritif", IngredientType.APERITIF, 20.0f, Color(0xD9F9E024), 1.0f, "oz", "A bright yellow, gentian root-based French apéritif with earthy and bittersweet notes", 25.4f),
        Ingredient("dubonnet", "Dubonnet Rouge", IngredientType.APERITIF, 14.8f, Color(0xD9781B26), 1.5f, "oz", "A sweet, aromatized wine-based apéritif enhanced with herbs and spices", 25.4f),
        Ingredient("bonal", "Bonal Gentiane-Quina", IngredientType.APERITIF, 16.0f, Color(0xD96C2E1F), 1.5f, "oz", "A mistelle (unfermented grape juice and alcohol) base infused with gentian, cinchona, and herbs", 25.4f),
        Ingredient("noilly_prat", "Noilly Prat Dry Vermouth", IngredientType.APERITIF, 18.0f, LiquorVermouthDry, 0.75f, "oz", "A historic French dry and extra-dry vermouth from Mediterranean botanicals", 25.4f),
        Ingredient("campari", "Campari Milano", IngredientType.APERITIF, 25.0f, LiquorRuby, 1.0f, "oz", "A bold, iconic ruby-red bittersweet herbal liqueur created in Milan", 25.4f),
        Ingredient("aperol", "Aperol", IngredientType.APERITIF, 11.0f, Color(0xD9FF5722), 2.0f, "oz", "A lighter, sweeter, lower-ABV orange-flavored sibling to Campari, famous for Aperol Spritz", 25.4f),
        Ingredient("cappelletti", "Cappelletti Aperitivo", IngredientType.APERITIF, 17.0f, Color(0xD9C62828), 1.5f, "oz", "A traditional wine-based, bittersweet, herbal red apéritif from Italy", 25.4f),
        Ingredient("select_aperitivo", "Select Aperitivo", IngredientType.APERITIF, 17.5f, Color(0xD9D32F2F), 1.5f, "oz", "A classic Venetian apéritif known for a balanced citrus and bittersweet profile", 25.4f),
        Ingredient("nonino", "Nonino L'Aperitivo", IngredientType.APERITIF, 21.0f, Color(0xD9E5A83C), 1.5f, "oz", "A botanical-infused, grappa-based apéritif from the Nonino family", 25.4f),
        Ingredient("galliano_aperitivo", "Galliano L'Aperitivo", IngredientType.APERITIF, 24.0f, Color(0xD9F5A623), 1.0f, "oz", "A vibrant, citrus-forward and herbal Italian bitter", 25.4f),
        Ingredient("carpano_antica", "Carpano Antica Formula", IngredientType.APERITIF, 16.5f, Color(0xE65D1A1F), 1.0f, "oz", "Historic Italian vermouth and bittersweet chinato-style vermouth with rich vanilla", 25.4f),
        Ingredient("cocchi_americano", "Cocchi Americano", IngredientType.APERITIF, 16.5f, Color(0x99E8D080), 1.5f, "oz", "Piedmontese wine-based Americano and vermouth classics with cinchona bark", 25.4f),
        Ingredient("martini_sweet", "Martini & Rossi Sweet Vermouth", IngredientType.APERITIF, 16.5f, LiquorVermouthSweet, 1.0f, "oz", "Globally recognized Italian sweet red vermouth with floral herbs and caramel", 25.4f),
        Ingredient("lo_fi", "Lo-Fi Gentian Aperitif", IngredientType.APERITIF, 16.5f, Color(0xD9B73A45), 1.5f, "oz", "A modern Californian maker of gentian-infused amari and vermouths", 25.4f),
        Ingredient("gran_classico", "Tempus Fugit Gran Classico", IngredientType.APERITIF, 28.0f, Color(0xD99E2A2B), 1.0f, "oz", "A Swiss-style historic bitter apéritif based on a 19th-century recipe", 25.4f),

        // === 15 BITTERS EVERY BARTENDER SHOULD KNOW ===
        Ingredient("angostura", "Angostura Bitters", IngredientType.BITTERS, 44.7f, BittersAngostura, 0.05f, "dashes", "1. Concentrated aromatic bitters made with herbs and spices (clove & gentian)", 4.0f),
        Ingredient("orange_bitters", "Orange Bitters", IngredientType.BITTERS, 9.0f, BittersOrange, 0.05f, "dashes", "2. Citrus-based bitters made with orange peel and botanicals", 4.0f),
        Ingredient("peychaud", "Peychaud's Bitters", IngredientType.BITTERS, 35.0f, BittersPeychaud, 0.05f, "dashes", "3. New Orleans-style aromatic bitters known for sweet anise and cherry notes", 4.0f),
        Ingredient("bokers_bitters", "Boker's Bitters", IngredientType.BITTERS, 31.5f, Color(0xE64E2A12), 0.05f, "dashes", "4. Historic aromatic cocktail bitters with cardamom and herbal bark", 4.0f),
        Ingredient("scrappys_bitters", "Scrappy's Bitters", IngredientType.BITTERS, 46.6f, Color(0xE66D3217), 0.05f, "dashes", "5. Craft aromatic bitters providing warm spice, herbal depth and balanced bitterness", 4.0f),
        Ingredient("fee_whiskey_barrel", "Fee Brothers (Whiskey Barrel)", IngredientType.BITTERS, 17.5f, Color(0xD9A45823), 0.05f, "dashes", "6. Bitters aged in freshly emptied whiskey oak barrels with warming spices", 4.0f),
        Ingredient("rhubarb_bitters", "Rhubarb Bitters", IngredientType.BITTERS, 4.5f, Color(0xD9E04A6A), 0.05f, "dashes", "7. Fruit-based bitters featuring the tart, tangy character of rhubarb", 4.0f),
        Ingredient("chocolate_bitters", "Chocolate Bitters", IngredientType.BITTERS, 44.7f, Color(0xE63D1F14), 0.05f, "dashes", "8. Bitters flavoured with cacao and chocolate-inspired dark botanicals", 4.0f),
        Ingredient("celery_bitters", "Celery Bitters", IngredientType.BITTERS, 1.3f, Color(0x998FA348), 0.05f, "dashes", "9. Savoury bitters made with celery seed, bringing vegetal herbal character", 4.0f),
        Ingredient("herbal_bitters", "Herbal Amaro Bitters", IngredientType.BITTERS, 38.0f, Color(0xE63E502B), 0.05f, "dashes", "10. Broad category of bitters made from roots, herbs and forest botanicals", 4.0f),
        Ingredient("lavender_bitters", "Lavender Bitters", IngredientType.BITTERS, 50.8f, Color(0xD97E57C2), 0.05f, "dashes", "11. Floral bitters infused with lavender botanicals, adding delicate fragrant notes", 4.0f),
        Ingredient("cucumber_bitters", "Cucumber Bitters", IngredientType.BITTERS, 35.0f, Color(0x80A5D6A7), 0.05f, "dashes", "12. Bitters featuring cucumber's fresh, green and refreshing crisp character", 4.0f),
        Ingredient("spiced_bitters", "Spiced Winter Bitters", IngredientType.BITTERS, 35.0f, Color(0xE67C2619), 0.05f, "dashes", "13. Bitters built around warming spices: cinnamon, clove, cardamom and allspice", 4.0f),
        Ingredient("walnut_bitters", "Black Walnut Bitters", IngredientType.BITTERS, 6.4f, Color(0xE63E2723), 0.05f, "dashes", "14. Nutty bitters featuring the earthy, woody character of black walnut", 4.0f),
        Ingredient("smoky_bitters", "Smoky Charred Bitters", IngredientType.BITTERS, 35.0f, Color(0xD9795548), 0.05f, "dashes", "15. Bitters designed to add smoky, roasted or charred depth to cocktails", 4.0f),

        // === MIXERS ===
        Ingredient("lime_juice", "Fresh Lime Juice", IngredientType.MIXER, 0.0f, MixerLime, 0.75f, "oz", "Tart citric acid and fresh key lime aroma", 16.0f),
        Ingredient("lemon_juice", "Fresh Lemon Juice", IngredientType.MIXER, 0.0f, MixerLemon, 0.75f, "oz", "Sharp sunny citrus and aromatic zest", 16.0f),
        Ingredient("simple_syrup", "Rich Simple Syrup", IngredientType.MIXER, 0.0f, MixerSimpleSyrup, 0.5f, "oz", "Pure refined cane sugar dissolved 1:1 in water", 16.0f),
        Ingredient("purified_water", "Purified Water", IngredientType.MIXER, 0.0f, Color(0x33B2EBF2), 1.0f, "oz", "Pure neutral filtered water for proof dilution and crystal clarity", 32.0f),
        Ingredient("sweet_sour_mix", "Sweet and Sour Mix", IngredientType.MIXER, 0.0f, Color(0xFFFDD835), 1.0f, "oz", "Balanced house blend of fresh lemon, lime juice and pure cane sugar syrup", 32.0f),
        Ingredient("olive_water", "Olive Water (Dirty Brine)", IngredientType.MIXER, 0.0f, Color(0xFFC0CA33), 0.5f, "oz", "Savory Mediterranean olive brine for authentic Dirty Martinis", 16.0f),
        Ingredient("strawberry_puree", "Strawberry Puree", IngredientType.MIXER, 0.0f, Color(0xFFE53935), 1.0f, "oz", "Rich ripe strawberry coulis for craft Daiquiris & Margaritas", 25.4f),
        Ingredient("passion_fruit_puree", "Passion Fruit Puree", IngredientType.MIXER, 0.0f, Color(0xFFFFB300), 0.75f, "oz", "Tart, exotic tropical puree for Pornstar Martinis & Tiki", 25.4f),
        Ingredient("cream_of_coconut", "Cream of Coconut (Coco López)", IngredientType.MIXER, 0.0f, Color(0xFFFFFDE7), 1.0f, "oz", "Rich velvety sweetened coconut cream for Piña Coladas", 25.4f),
        Ingredient("coconut_puree", "Coconut Puree", IngredientType.MIXER, 0.0f, Color(0xFFFAFAFA), 1.0f, "oz", "Silky natural unsweetened coconut cream and pulp", 25.4f),
        Ingredient("cranberry_juice", "Cranberry Juice", IngredientType.MIXER, 0.0f, MixerCranberry, 1.0f, "oz", "Crisp tart ruby cranberry juice for Cosmopolitans & Breezes", 32.0f),
        Ingredient("grenadine", "Real Pomegranate Grenadine", IngredientType.MIXER, 0.0f, Color(0xFFC2185B), 0.5f, "oz", "Tart and rich ruby pomegranate reduction syrup", 12.0f),
        Ingredient("ginger_beer", "Spicy Ginger Beer", IngredientType.MIXER, 0.0f, MixerGingerBeer, 3.5f, "oz", "Fiery Jamaican ginger extract and soda carbonation", 12.0f),
        Ingredient("sprite", "Sprite Lemon-Lime Soda", IngredientType.MIXER, 0.0f, Color(0x33A5D6A7), 3.0f, "oz", "Crisp, sweet lemon-lime sparkling soda", 12.0f),
        Ingredient("club_soda", "Chilled Club Soda", IngredientType.MIXER, 0.0f, MixerClubSoda, 2.0f, "oz", "High-effervescence carbonated mineral water", 12.0f),
        Ingredient("tonic_water", "Tonic Water", IngredientType.MIXER, 0.0f, MixerClubSoda, 3.0f, "oz", "Quinine bark bitterness with fine bubbles", 12.0f),
        Ingredient("cola", "Classic Cola", IngredientType.MIXER, 0.0f, MixerCola, 3.0f, "oz", "Kola nut, caramel, and vanilla soda", 12.0f),
        Ingredient("grapefruit_juice", "Ruby Red Grapefruit Juice", IngredientType.MIXER, 0.0f, Color(0xFFFF8A80), 1.5f, "oz", "Bittersweet, zesty pink citrus juice", 16.0f),
        Ingredient("orange_juice", "Fresh Squeezed Orange Juice", IngredientType.MIXER, 0.0f, Color(0xFFFFA726), 2.0f, "oz", "Sun-ripened sweet citrus juice with light pulp", 16.0f),
        Ingredient("pineapple_juice", "Pineapple Juice", IngredientType.MIXER, 0.0f, Color(0xFFFFEE58), 2.0f, "oz", "Tropical golden pineapple juice creating natural foam", 16.0f),
        Ingredient("agave_nectar", "Organic Blue Agave Nectar", IngredientType.MIXER, 0.0f, Color(0xFFD4A373), 0.5f, "oz", "Pure low-glycemic nectar for authentic margaritas", 16.0f),
        Ingredient("honey_syrup", "Wildflower Honey Syrup (3:1)", IngredientType.MIXER, 0.0f, Color(0xFFF9A825), 0.75f, "oz", "Aromatic floral honey loosened with warm water", 16.0f),
        Ingredient("tomato_juice", "Spiced Tomato Juice Mix", IngredientType.MIXER, 0.0f, Color(0xFFD32F2F), 4.0f, "oz", "Savory tomato puree with Worcestershire and horseradish", 32.0f),
        Ingredient("apple_liqueur", "Sour Apple Liqueur (Pucker)", IngredientType.MIXER, 15.0f, Color(0xFF76FF03), 1.0f, "oz", "Electric green crisp tart sour apple schnapps liqueur for Appletinis & craft highballs", 25.4f),

        // === GARNISHES ===
        Ingredient("lime_wheel", "Fresh Lime Wheel", IngredientType.GARNISH, 0.0f, MixerLime, 1.0f, "wheel", "Crisp juicy lime wheel slotted over the glass rim"),
        Ingredient("lemon_wheel", "Fresh Lemon Wheel", IngredientType.GARNISH, 0.0f, MixerLemon, 1.0f, "wheel", "Vibrant sun-yellow lemon wheel clipped on rim"),
        Ingredient("orange_wheel", "Fresh Orange Wheel", IngredientType.GARNISH, 0.0f, Color(0xFFFF9800), 1.0f, "wheel", "Dehydrated or freshly sliced citrus wheel floated or pegged"),
        Ingredient("orange_twist", "Expressed Orange Twist", IngredientType.GARNISH, 0.0f, Color(0xFFE65100), 1.0f, "twist", "Long curling orange peel ribbon with expressed aromatic oils"),
        Ingredient("lemon_twist", "Curled Lemon Twist", IngredientType.GARNISH, 0.0f, Color(0xFFFFEE58), 1.0f, "twist", "Polished spiral lemon peel ribbon draped over the rim"),
        Ingredient("orange_slice", "Flame Orange Peel", IngredientType.GARNISH, 0.0f, AmberGold, 1.0f, "peel", "Toasted citrus oils on the surface"),
        Ingredient("mint_sprig", "Bouquet of Fresh Mint", IngredientType.GARNISH, 0.0f, LiquorGreen, 1.0f, "sprig", "Slapped to awaken aromatic menthol oils"),
        Ingredient("cherry", "Luxardo Maraschino Cherry", IngredientType.GARNISH, 0.0f, LiquorRuby, 1.0f, "cherry", "Dense candied black cherry with stem soaked in liqueur"),
        Ingredient("three_olives", "Three Olives on a Spear", IngredientType.GARNISH, 0.0f, Color(0xFF558B2F), 3.0f, "olives", "Trinity of Spanish Queen pimiento-stuffed olives on a cocktail pick"),
        Ingredient("olive", "Spanish Queen Green Olive", IngredientType.GARNISH, 0.0f, Color(0xFF558B2F), 1.0f, "olive", "Briny savory green olive on a cocktail pick"),
        Ingredient("coffee_beans", "Three Coffee Beans", IngredientType.GARNISH, 0.0f, Color(0xFF3E2723), 3.0f, "beans", "Roasted espresso bean trinity representing health, wealth & happiness"),
        Ingredient("celery_stalk", "Crisp Celery Stalk", IngredientType.GARNISH, 0.0f, Color(0xFF689F38), 1.0f, "stalk", "Tall crunchy stalk with leafy top for savory libations"),
        Ingredient("salt_rim", "Flaky Sea Salt Rim", IngredientType.GARNISH, 0.0f, Color.White, 1.0f, "rim", "Crusted along the outer circumference"),
        Ingredient("sugar_rim", "Pure Cane Sugar Rim", IngredientType.GARNISH, 0.0f, Color(0xFFFFF9C4), 1.0f, "rim", "Sweet sparkling crust for Sidecars & Lemon Drops"),
        Ingredient("tajin_rim", "Tajín Chili-Lime Salt Rim", IngredientType.GARNISH, 0.0f, Color(0xFFBF360C), 1.0f, "rim", "Zesty chili peppers and dehydrated lime crystals"),

        // === ICE ===
        Ingredient("ice_cubes", "Pure Crystalline Ice", IngredientType.ICE, 0.0f, IceColor, 1.0f, "scoop", "Dense 1-inch solid cold cubes for thermal mass")
    )

    val ingredients: List<Ingredient> = (coreIngredients + WineCatalog.allWines + BeerCatalog.allBeers).distinctBy { it.id }

    fun getIngredient(id: String): Ingredient = ingredients.find { it.id == id } ?: ingredients[0]

    val wineList: List<Ingredient> get() = WineCatalog.allWines
    val beerList: List<Ingredient> get() = BeerCatalog.allBeers

    // === MASTER COCKTAILS CATALOG ===
    val classicCocktails: List<CocktailRecipe> get() = CocktailCatalog.allCocktails

    // === ALCOHOL LAWS & RBS REGULATIONS ===
    val stateLaws: List<AlcoholLaw> = listOf(
        AlcoholLaw(
            stateCode = "CA",
            stateName = "California",
            legalAge = 21,
            servingHours = "6:00 AM to 2:00 AM daily. Any patron possessing alcohol after 2:00 AM is a misdemeanor.",
            dramShopLiability = "Limited Liability: Licensees generally not liable for drunk driving damages unless alcohol was knowingly served to an obviously intoxicated minor under Business & Professions Code § 25602.1.",
            fakeIdPenalties = "Class B Misdemeanor, fines up to $1,000, 32-40 hours community service, and driver's license suspension up to 1 year.",
            rbsMandatory = true,
            rbsDescription = "AB 1221 mandates that ALL on-premise alcohol servers and managers must earn state RBS certification within 60 days of hire."
        ),
        AlcoholLaw(
            stateCode = "NY",
            stateName = "New York",
            legalAge = 21,
            servingHours = "Monday-Saturday 8:00 AM to 4:00 AM. Sunday 10:00 AM to 4:00 AM (varies by county).",
            dramShopLiability = "Strict Dram Shop Act (General Obligations Law § 11-101): Establishments are civilly liable for damages caused by unlawful sales to visibly intoxicated patrons or minors.",
            fakeIdPenalties = "Up to $500 fine and 90-day driver's license revocation. Unlawful possession of forged government ID is a felony.",
            rbsMandatory = false,
            rbsDescription = "ATAP (Alcohol Training Awareness Program) is voluntary but provides an affirmative defense against SLA license revocation."
        ),
        AlcoholLaw(
            stateCode = "TX",
            stateName = "Texas",
            legalAge = 21,
            servingHours = "Monday-Friday 7:00 AM to midnight. Saturday 7:00 AM to 1:00 AM. Sunday 10:00 AM (with food) to midnight. Extended hours permits allow service until 2:00 AM.",
            dramShopLiability = "Texas Alcoholic Beverage Code § 106.14: Safe Harbor protects bars if employees hold TABC certification and owners do not encourage over-service.",
            fakeIdPenalties = "Class C Misdemeanor with up to $500 fine, 8-12 hours alcohol education, and driver's license suspension.",
            rbsMandatory = false,
            rbsDescription = "TABC Server Certification provides legal Safe Harbor protection against severe dram shop liability."
        ),
        AlcoholLaw(
            stateCode = "FL",
            stateName = "Florida",
            legalAge = 21,
            servingHours = "Default state hours 7:00 AM to midnight unless local county ordinances extend service to 2:00 AM or 4:00 AM.",
            dramShopLiability = "Fla. Stat. § 768.125: Extremely strict bar protection. Bar is ONLY liable if serving a minor or an individual known to be habitually addicted to alcohol.",
            fakeIdPenalties = "Second-degree misdemeanor with up to 60 days in jail and $500 fine.",
            rbsMandatory = false,
            rbsDescription = "Responsible Vendor Training qualifies bar owners for significant insurance discounts and mitigates administrative fines."
        ),
        AlcoholLaw(
            stateCode = "IL",
            stateName = "Illinois",
            legalAge = 21,
            servingHours = "Regulated by municipality (Chicago allows up to 2:00 AM or 4:00 AM for late-hour licenses).",
            dramShopLiability = "Strict No-Fault Dram Shop Act (235 ILCS 5/6-21): Pure strict liability for injuries caused by intoxicated patrons up to statutory damage caps without needing to prove visible intoxication.",
            fakeIdPenalties = "Class A Misdemeanor, fines up to $2,500 and 1-year jail sentence for fraudulent driver's licenses.",
            rbsMandatory = true,
            rbsDescription = "BASSET (Beverage Alcohol Sellers and Servers Education and Training) certification is mandatory statewide for all servers and security."
        )
    )

    fun getLawForState(code: String): AlcoholLaw = stateLaws.find { it.stateCode.equals(code, ignoreCase = true) } ?: stateLaws[0]

    // === QUIZ QUESTIONS ===
    val quizQuestions: List<QuizQuestion> = listOf(
        QuizQuestion(
            id = "q1",
            type = QuizType.RATIO_CHALLENGE,
            title = "Whiskey Sour Balance",
            prompt = "What is the standard classic ratio (oz) of Bourbon : Lemon Juice : Simple Syrup in a Whiskey Sour?",
            options = listOf(
                "2.0 oz Bourbon : 0.75 oz Lemon : 0.75 oz Syrup",
                "1.5 oz Bourbon : 1.5 oz Lemon : 1.0 oz Syrup",
                "3.0 oz Bourbon : 0.5 oz Lemon : 0.25 oz Syrup",
                "2.0 oz Bourbon : 1.5 oz Lemon : 1.5 oz Syrup"
            ),
            correctIndex = 0,
            explanation = "A Whiskey Sour follows the golden sour ratio of 2 oz spirit to 0.75 oz citrus and 0.75 oz sweet modifier, creating the perfect harmonious balance."
        ),
        QuizQuestion(
            id = "q2",
            type = QuizType.GLASS_MATCH,
            title = "Glassware Precision: Moscow Mule",
            prompt = "Which vessel is traditionally and functionally required for a Moscow Mule to preserve brisk temperature and effervescence?",
            options = listOf(
                "Moscow Mule Solid Copper Mug (14 oz)",
                "Martini Conical Stem (7 oz)",
                "American Pint Glass (16 oz)",
                "Tall Hurricane Flute (15 oz)"
            ),
            correctIndex = 0,
            explanation = "The conductive copper mug rapidly chills to freezing contact temperatures, frost-sheathing the vessel and keeping the spicy ginger beer fizzy."
        ),
        QuizQuestion(
            id = "q3",
            type = QuizType.GARNISH_TECHNIQUE,
            title = "Prep Technique: Negroni",
            prompt = "Why is a classic Negroni stirred with ice rather than shaken in a cocktail shaker?",
            options = listOf(
                "Stirring preserves crystal-clear brilliance and velvety texture without clouding or over-diluting all-spirit ingredients",
                "Shaking would break the glass",
                "Campari loses its red color if shaken",
                "Stirring makes the drink much sweeter"
            ),
            correctIndex = 0,
            explanation = "As a rule of mixology: Cocktails with only spirits and fortified wines (like Negroni and Manhattan) are stirred to avoid aeration bubbles, preserving a silky mouthfeel and jewel-like clarity."
        ),
        QuizQuestion(
            id = "q4",
            type = QuizType.RATIO_CHALLENGE,
            title = "Margarita Spec Check",
            prompt = "According to international bartending standards, what are the proportions for a classic 3:2:1 or modern 2:1:1 Margarita?",
            options = listOf(
                "2.0 oz Tequila Blanco : 1.0 oz Triple Sec : 1.0 oz Fresh Lime Juice",
                "1.0 oz Tequila : 2.0 oz Sweet & Sour mix : 0.5 oz Triple Sec",
                "2.5 oz Tequila : 0.5 oz Lime : 2.0 oz Orange Juice",
                "1.5 oz Tequila : 1.5 oz Lime : 1.5 oz Simple Syrup"
            ),
            correctIndex = 0,
            explanation = "The classic spec uses 2 oz 100% blue agave tequila, 1 oz triple sec (like Cointreau), and 1 oz freshly squeezed lime juice with a delicate outer salt rim."
        ),
        QuizQuestion(
            id = "q5",
            type = QuizType.SERVSAFE_LAW,
            title = "Responsible Service: Standard Drink",
            prompt = "In alcohol awareness certification (ServSafe / TIPS), how much pure ethanol is contained in one 'Standard Drink'?",
            options = listOf(
                "Approximately 14 grams (0.6 fl oz) of pure alcohol",
                "25 grams of pure alcohol",
                "5 grams of pure alcohol",
                "50 grams of pure alcohol"
            ),
            correctIndex = 0,
            explanation = "One standard US drink equals 14g (0.6 oz) pure alcohol: equivalent to 12 oz beer at 5%, 5 oz wine at 12%, or 1.5 oz 80-proof distilled spirits."
        ),
        QuizQuestion(
            id = "q6",
            type = QuizType.SERVSAFE_LAW,
            title = "Refusal of Service Law",
            prompt = "When an alcohol server identifies a patron exhibiting signs of visible intoxication, what is the server legally required to do?",
            options = listOf(
                "Politely and firmly cut the patron off, discontinue alcohol service, offer water/food, and arrange safe transportation",
                "Water down their next drink without telling them",
                "Let them finish the drink if their friends promise to drive",
                "Charge them double price for the next drink"
            ),
            correctIndex = 0,
            explanation = "Under Dram Shop and state alcohol licensing laws, serving alcohol to visibly intoxicated individuals is illegal. You must refuse further service clearly and professionally."
        )
    )

    // === INITIAL LEADERBOARD DATA ===
    val sampleLeaderboard: List<LeaderboardEntry> = listOf(
        LeaderboardEntry(1, "Elena_TheMixer", 9850, "Master Mixologist", "99.2%"),
        LeaderboardEntry(2, "CocktailKing_Marcus", 9420, "Lead Bartender", "97.8%"),
        LeaderboardEntry(3, "SpeakeasySam", 8910, "Senior Barman", "96.5%"),
        LeaderboardEntry(4, "GinBotanist_Chloe", 8500, "Craft Specialist", "95.0%"),
        LeaderboardEntry(5, "You (Student)", 7640, "Apprentice Mixologist", "94.5%", isCurrentUser = true),
        LeaderboardEntry(6, "TokyoBar_Kenji", 7200, "Journeyman", "93.0%"),
        LeaderboardEntry(7, "WhiskeyDave", 6850, "Apprentice", "91.4%"),
        LeaderboardEntry(8, "BaristaToBartender", 6400, "Apprentice", "89.0%")
    )

    // === SPEED RUSH CUSTOMERS ===
    val speedCustomers: List<SpeedCustomer> = listOf(
        SpeedCustomer(
            id = "c1",
            name = "Victoria",
            roleTitle = "Executive After-Work",
            requestedCocktailId = "dry_martini",
            dialogue = "\"Rough day at headquarters. Make me a bone-dry Classic Martini with an olive, extra cold!\"",
            totalTimeSeconds = 25,
            currentMood = CustomerMood.HAPPY
        ),
        SpeedCustomer(
            id = "c2",
            name = "Julian",
            roleTitle = "Speakeasy Connoisseur",
            requestedCocktailId = "old_fashioned",
            dialogue = "\"Give me an Old Fashioned. Good bourbon, heavy on the aromatic bitters, slow stir!\"",
            totalTimeSeconds = 22,
            currentMood = CustomerMood.HAPPY
        ),
        SpeedCustomer(
            id = "c3",
            name = "Camila",
            roleTitle = "Celebration Party",
            requestedCocktailId = "margarita",
            dialogue = "\"Hey bartender! It's our anniversary—two Margaritas shaken with crisp lime and a salted rim!\"",
            totalTimeSeconds = 20,
            currentMood = CustomerMood.HAPPY
        ),
        SpeedCustomer(
            id = "c4",
            name = "Arthur",
            roleTitle = "Italian Foodie",
            requestedCocktailId = "negroni",
            dialogue = "\"Buonasera! I need an authentic Negroni before dinner. Equal parts, fresh orange!\"",
            totalTimeSeconds = 20,
            currentMood = CustomerMood.HAPPY
        ),
        SpeedCustomer(
            id = "c5",
            name = "Maya",
            roleTitle = "Nightclub Socialite",
            requestedCocktailId = "espresso_martini",
            dialogue = "\"I need energy for the dance floor! Whip up an Espresso Martini with thick crema foam!\"",
            totalTimeSeconds = 18,
            currentMood = CustomerMood.HAPPY
        )
    )

    val defaultBadges: List<StudentBadge> = listOf(
        StudentBadge("b_first_pour", "First Liquid Poured", "Poured your very first spirit into the shaker", "🥃", true),
        StudentBadge("b_ratio_master", "Ratio Calibrator", "Achieved 95%+ precision in the recipe accuracy test", "⚖️", true),
        StudentBadge("b_ice_craft", "Ice & Glass Master", "Matched all 10 real-world glassware dimensions", "🧊", true),
        StudentBadge("b_speed_demon", "Rush Hour Hero", "Served 5 consecutive rush hour guests before the clock ran out", "⏱️", false),
        StudentBadge("b_custom_inventor", "Original Mixologist", "Formulated and claimed a unique custom cocktail recipe", "📜", false),
        StudentBadge("b_servsafe_star", "Responsible Beverage Pro", "Completed all ServSafe and Dram Shop compliance modules", "🛡️", true)
    )
}
