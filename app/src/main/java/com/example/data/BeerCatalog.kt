package com.example.data

import androidx.compose.ui.graphics.Color
import com.example.model.Ingredient
import com.example.model.IngredientType

object BeerCatalog {

    val americanBeers: List<Ingredient> = listOf(
        Ingredient("star_northern_lights_ipa", "Star Northern Lights IPA", IngredientType.BEER, 6.5f, Color(0xFFF39C12), 12.0f, "oz", "American West Coast IPA (12oz) with bright pine, floral citrus, and bold resinous hop bitterness", 12.0f),
        Ingredient("blue_and_gray_fred_red", "Blue & Gray Fred Red", IngredientType.BEER, 5.8f, Color(0xFFB7410E), 12.0f, "oz", "Fredericksburg Virginia Red Ale (22.5oz bottle) rich in caramel malts and toffee roast", 22.5f),
        Ingredient("sam_adams_boston_lager", "Sam Adams Boston Lager", IngredientType.BEER, 5.0f, Color(0xFFD48817), 12.0f, "oz", "Flagship Boston Vienna Lager (12oz) with distinct German Hallertau Mittelfrüh hops and caramelized malt", 12.0f),
        Ingredient("yuengling_lager", "Yuengling Traditional Lager", IngredientType.BEER, 4.5f, Color(0xFFC77826), 12.0f, "oz", "America's oldest brewery lager (12oz) with roasted amber malts and subtle cluster hop bitterness", 12.0f),
        Ingredient("flying_dog_doggie_style", "Flying Dog Doggie Style Pale", IngredientType.BEER, 5.5f, Color(0xFFE5A125), 12.0f, "oz", "Classic Maryland pale ale (12oz) with Northern Brewer and Cascade hops, biscuit malt, and clean citrus", 12.0f),
        Ingredient("underdog_atlantic_lager", "Underdog Atlantic Lager", IngredientType.BEER, 4.7f, Color(0xFFF1C40F), 12.0f, "oz", "Flying Dog craft easy-drinking lager (12oz) with crisp pilsner malt and subtle floral aromatics", 12.0f),
        Ingredient("shock_top", "Shock Top Belgian White", IngredientType.BEER, 5.2f, Color(0xFFF7DC6F), 12.0f, "oz", "Spiced wheat ale (12oz) brewed with real orange, lemon, lime peels and ground coriander", 12.0f),
        Ingredient("budweiser_spun_aluminum", "Budweiser (Spun Aluminum)", IngredientType.BEER, 5.0f, Color(0xFFF5B041), 12.0f, "oz", "Chilled 16oz spun aluminum bottle of the classic American lager, aged on beechwood", 16.0f),
        Ingredient("bud_and_bud_light_spun", "Bud & Bud Light (Spun Bottle)", IngredientType.BEER, 4.2f, Color(0xFFFAD7A0), 12.0f, "oz", "Frosty 16oz spun aluminum bottle of crisp light lager with rice and barley balance", 16.0f),
        Ingredient("bud_platinum", "Bud Light Platinum", IngredientType.BEER, 6.0f, Color(0xFFF9E79F), 12.0f, "oz", "Triple-filtered higher ABV golden lager (12oz) with sweet corn malt and subtle floral hops", 12.0f),
        Ingredient("michelob_ultra", "Michelob Ultra", IngredientType.BEER, 4.2f, Color(0xFFFCF3CF), 12.0f, "oz", "Low-carb, light-calorie American lager (12oz) crafted with Herkules hops and Munich malts", 12.0f),
        Ingredient("miller_lite", "Miller Lite", IngredientType.BEER, 4.2f, Color(0xFFFCEBA0), 12.0f, "oz", "Pilsner-style American light lager (12oz) brewed with crystal malt and Galena hops", 12.0f),
        Ingredient("blue_moon", "Blue Moon Belgian White", IngredientType.BEER, 5.4f, Color(0xFFFAD7A0), 12.0f, "oz", "Creamy wheat ale (12oz) brewed with Valencia orange peel, rolled oats, and fresh coriander", 12.0f),
        Ingredient("stevens_nude_beach", "Stevens Nude Beach Summer Ale", IngredientType.BEER, 5.0f, Color(0xFFF9E79F), 12.0f, "oz", "Crisp Wisconsin summer wheat ale (12oz) brewed with raw wheat and snappy floral citrus", 12.0f),
        Ingredient("leinenkugel_shandy", "Leinenkugel's Summer Shandy", IngredientType.BEER, 4.2f, Color(0xFFF9E79F), 12.0f, "oz", "Traditional German Weiss beer (12oz) blended with natural lemonade juice and malt", 12.0f),
        Ingredient("legend_lager", "Legend Brewing Lager", IngredientType.BEER, 4.8f, Color(0xFFE59866), 12.0f, "oz", "Richmond Virginia craft European-style golden lager (12oz) with light toasted grain", 12.0f),
        Ingredient("land_shark", "Landshark Island Lager", IngredientType.BEER, 4.6f, Color(0xFFFDEBD0), 12.0f, "oz", "Refreshing island-style golden lager (12oz) brewed with complex two-row caramel malts", 12.0f),
        Ingredient("sierra_nevada_torpedo", "Sierra Nevada Torpedo IPA", IngredientType.BEER, 7.2f, Color(0xFFD35400), 12.0f, "oz", "Aggressively hopped Extra IPA (12oz) bursting with Magnum, Crystal, and Citra hop oils", 12.0f)
    )

    val belgiumBeers: List<Ingredient> = listOf(
        Ingredient("hoegaarden_wheat", "Hoegaarden Belgian Wheat", IngredientType.BEER, 4.9f, Color(0xFFF5EEA8), 12.0f, "oz", "Authentic Belgian witbier (12oz) brewed with Curaçao orange peel and spicy coriander", 12.0f),
        Ingredient("chimay_reserve_red", "Chimay Reserve Red Corked", IngredientType.BEER, 7.0f, Color(0xFF6E2C00), 12.0f, "oz", "Prestigious Trappist Dubbel (24.5oz / 750ml corked bottle) with apricot, brown sugar, and nutmeg", 24.5f),
        Ingredient("chimay_peres_white", "Chimay Pères Trappistes White", IngredientType.BEER, 8.0f, Color(0xFFD4AC0D), 12.0f, "oz", "Dry golden Trappist Tripel (12oz) with herbal hop flowers, muscat grape, and dry clove finish", 12.0f),
        Ingredient("wittekerke_wheat", "Wittekerke Belgian Wheat", IngredientType.BEER, 5.0f, Color(0xFFF9E79F), 12.0f, "oz", "Classic Bavikhove wheat beer (12oz) with cloudy straw hue, candied citrus, and soft vanilla foam", 12.0f),
        Ingredient("orval_trappist", "Orval Trappist Ale", IngredientType.BEER, 6.2f, Color(0xFFCA6F1E), 12.0f, "oz", "Iconic dry-hopped abbey ale (12oz) bottle-conditioned with Brettanomyces for funky complexity", 12.0f),
        Ingredient("lindemans_framboise", "Lindemans Framboise", IngredientType.BEER, 2.5f, Color(0xFF900C3F), 12.0f, "oz", "Belgian lambic beer (12oz) fermented with wild yeasts and real fresh raspberries", 12.0f)
    )

    val hollandBeers: List<Ingredient> = listOf(
        Ingredient("grolsch_ceramic", "Grolsch Ceramic Swingtop", IngredientType.BEER, 5.0f, Color(0xFFF7DC6F), 12.0f, "oz", "Iconic 16oz green ceramic flip-top bottle with two-hop variety malt and crisp lager finish", 16.0f),
        Ingredient("heineken", "Heineken Original", IngredientType.BEER, 5.0f, Color(0xFFF9E79F), 12.0f, "oz", "Pure malt Dutch lager (12oz) brewed with unique A-yeast for fruity ester balance", 12.0f),
        Ingredient("heineken_light", "Heineken Light", IngredientType.BEER, 3.3f, Color(0xFFFCF3CF), 12.0f, "oz", "Light Dutch premium lager (12oz) dry-hopped with Cascade hops and lower calories", 12.0f)
    )

    val germanyBeers: List<Ingredient> = listOf(
        Ingredient("ayinger_brauweisse", "Ayinger Bräuweisse", IngredientType.BEER, 5.1f, Color(0xFFF5CBA7), 12.0f, "oz", "Bavarian classic wheat beer (12oz) with golden cloudiness, banana esters, and clove", 12.0f),
        Ingredient("ayinger_celebrator", "Ayinger Celebrator Doppelbock", IngredientType.BEER, 6.7f, Color(0xFF2C1408), 12.0f, "oz", "World-renowned dark double bock (12oz) with dark toffee, roasted coffee, and dried figs", 12.0f),
        Ingredient("ayinger_jahrhundert", "Ayinger Jahrhundert Bier", IngredientType.BEER, 5.5f, Color(0xFFF4D03F), 12.0f, "oz", "17oz (500ml) Bavarian export festival beer with golden malt sweetness and noble hops", 17.0f),
        Ingredient("warsteiner_oktoberfest", "Warsteiner Oktoberfest & Verum", IngredientType.BEER, 4.8f, Color(0xFFE59866), 12.0f, "oz", "Historic Warstein golden pilsner and Marzen (12oz) brewed in strict accordance with the Reinheitsgebot", 12.0f),
        Ingredient("pinkus_munster_ale", "Pinkus Münster Alt Organic", IngredientType.BEER, 5.1f, Color(0xFFEDBB99), 12.0f, "oz", "17oz (500ml) organic Münsterland top-fermented alt ale brewed with organic barley malt", 17.0f),
        Ingredient("schoenramer_gold_pilsner", "Schönramer Gold & Pilsner", IngredientType.BEER, 5.7f, Color(0xFFF9E79F), 12.0f, "oz", "17oz (500ml) Bavarian specialty festbier with deep golden body, rich malt, and clean dry finish", 17.0f),
        Ingredient("reutberg_export_dunkel", "Reutberg Export Dunkel & Helle", IngredientType.BEER, 5.2f, Color(0xFF4A235A), 12.0f, "oz", "17oz (500ml) monastery-brewed dark Bavarian lager with roasted chocolate malt and bread crust", 17.0f),
        Ingredient("hopf_weisse", "Hopf Helle & Dunkel Weisse", IngredientType.BEER, 5.3f, Color(0xFFE59866), 12.0f, "oz", "17oz (500ml) Miesbach Bavarian wheat beer with spicy yeast profile and rich head", 17.0f)
    )

    val englandBeers: List<Ingredient> = listOf(
        Ingredient("sam_smith_organic_lager", "Sam Smith Organic Lager", IngredientType.BEER, 5.0f, Color(0xFFF9E79F), 12.0f, "oz", "Yorkshire Tadcaster organic English lager (12oz) fermented in stone Yorkshire squares", 12.0f),
        Ingredient("sam_smith_oatmeal_stout", "Sam Smith Oatmeal Stout", IngredientType.BEER, 5.0f, Color(0xFF1B0E05), 12.0f, "oz", "Creamy, silky English stout (12oz) with rolled oats, dark chocolate, and roasted coffee", 12.0f),
        Ingredient("sam_smith_nut_brown", "Sam Smith Nut Brown Ale", IngredientType.BEER, 5.0f, Color(0xFF5B2C08), 12.0f, "oz", "Classic British brown ale (12oz) with walnut, toasted hazelnuts, yeast esters, and dry finish", 12.0f),
        Ingredient("sam_smith_chocolate_stout", "Sam Smith Organic Chocolate Stout", IngredientType.BEER, 5.0f, Color(0xFF140A04), 12.0f, "oz", "Decadent English dark stout (12oz) conditioned with organic cocoa beans and malt", 12.0f),
        Ingredient("sam_smith_winter_welcome", "Sam Smith Winter Welcome", IngredientType.BEER, 6.0f, Color(0xFF935116), 12.0f, "oz", "Seasonal holiday ale (12oz) with rich amber malt, Goldings hops, and dried fruit warmth", 12.0f)
    )

    val scotlandBeers: List<Ingredient> = listOf(
        Ingredient("traquair_house_ale", "Traquair House Ale", IngredientType.BEER, 7.2f, Color(0xFF4A1E0B), 12.0f, "oz", "17oz (500ml) Scottish highland ale brewed in the oldest inhabited house in Scotland with oak fermenters", 17.0f),
        Ingredient("traquair_jacobite", "Traquair Jacobite Ale", IngredientType.BEER, 8.0f, Color(0xFF3B1506), 12.0f, "oz", "17oz (500ml) historic 18th-century recipe brewed with fresh coriander and heavy malt", 17.0f)
    )

    val mexicoBeers: List<Ingredient> = listOf(
        Ingredient("corona_extra", "Corona Extra", IngredientType.BEER, 4.6f, Color(0xFFF9E79F), 12.0f, "oz", "Iconic Mexican pale lager (12oz bottle) crisp with corn and barley malt, meant for fresh lime", 12.0f),
        Ingredient("corona_light", "Corona Light", IngredientType.BEER, 4.1f, Color(0xFFFCF3CF), 12.0f, "oz", "Light calorie Mexican lager (12oz) with snappy carbonation and clean lime-ready finish", 12.0f),
        Ingredient("dos_equis_amber", "Dos Equis XX Amber", IngredientType.BEER, 4.7f, Color(0xFFBA4A00), 12.0f, "oz", "Classic Mexican Vienna-style amber lager (12oz) with toasted caramel and brown sugar notes", 12.0f),
        Ingredient("negra_modelo", "Negra Modelo Munich Dunkel", IngredientType.BEER, 5.4f, Color(0xFF4A1F0D), 12.0f, "oz", "Mexico's finest slow-roasted Munich dunkel lager (12oz) with dark malt, toffee, and cocoa", 12.0f)
    )

    val internationalBeers: List<Ingredient> = listOf(
        Ingredient("red_stripe", "Red Stripe Jamaican Lager", IngredientType.BEER, 4.7f, Color(0xFFFAD7A0), 12.0f, "oz", "Kingston Jamaica stubby bottle (12oz) featuring crisp golden lager with sweet grain and mild hops", 12.0f),
        Ingredient("guinness_draught", "Guinness Draught & Red", IngredientType.BEER, 4.2f, Color(0xFF170802), 12.0f, "oz", "16oz pub draught can with nitrogen widget, black roasted barley, and thick creamy head", 16.0f),
        Ingredient("harp_lager", "Harp Irish Lager", IngredientType.BEER, 4.5f, Color(0xFFF7DC6F), 12.0f, "oz", "Traditional Irish golden lager (12oz) brewed with Irish malt and Saaz hops for crisp refreshment", 12.0f),
        Ingredient("peroni_nastro_azzurro", "Peroni Nastro Azzurro", IngredientType.BEER, 5.1f, Color(0xFFFAD7A0), 12.0f, "oz", "Italian premium lager (12oz) crafted with Nostrano dell'Isola maize and Saaz-Saaz hops", 12.0f),
        Ingredient("sapporo_premium", "Sapporo 22oz Steel Can", IngredientType.BEER, 4.9f, Color(0xFFF9E79F), 12.0f, "oz", "Legendary Japanese sculpted 22oz steel can, remarkably crisp with rice and pure lager yeast", 22.0f)
    )

    val alternativeBeers: List<Ingredient> = listOf(
        Ingredient("mikes_hard_lemonade", "Mike's Hard Lemonade", IngredientType.BEER, 5.0f, Color(0xFFF9E79F), 12.0f, "oz", "Refreshing proprietary malt beverage (12oz) with crisp natural tart lemon juice", 12.0f),
        Ingredient("angry_orchard", "Angry Orchard Apple Cider", IngredientType.BEER, 5.0f, Color(0xFFF5B041), 12.0f, "oz", "Crisp hard apple cider (12oz) crafted with culinary apples and traditional French bittersweet apples", 12.0f),
        Ingredient("bacardi_razz_mojito", "Bacardi Razberry & Mojito", IngredientType.BEER, 5.0f, Color(0xFFD98880), 12.0f, "oz", "Flavorful premium malt beverage (12oz) layered with raspberry, lime, and crushed mint", 12.0f)
    )

    val classicBeerCraft: List<Ingredient> = listOf(
        Ingredient("craft_ipa", "Hazy West Coast IPA", IngredientType.BEER, 6.8f, Color(0xFFF39C12), 12.0f, "oz", "Juicy, aromatic American IPA dry-hopped with Citra and Mosaic hops for citrus-pine resin", 16.0f),
        Ingredient("guinness_stout", "Guinness Extra Stout", IngredientType.BEER, 4.2f, Color(0xFF1B140E), 12.0f, "oz", "Nitrogenated Dublin dry stout with creamy head, dark roasted barley, and espresso notes", 16.0f),
        Ingredient("belgian_witbier", "Belgian Witbier", IngredientType.BEER, 5.2f, Color(0xFFF5E6A3), 12.0f, "oz", "Unfiltered cloudy wheat ale brewed with ground coriander and Curaçao sweet orange peel", 16.0f),
        Ingredient("pilsner", "Czech Saaz Craft Pilsner", IngredientType.BEER, 5.0f, Color(0xFFE5A125), 12.0f, "oz", "Crisp Bohemian lager with bready pilsner malt, snappy carbonation, and herbal Saaz hops", 16.0f),
        Ingredient("mexican_lager", "Pacifico Mexican Lager", IngredientType.BEER, 4.5f, Color(0xFFF1C40F), 12.0f, "oz", "Light-bodied golden lager crafted for crisp drinkability, perfect with fresh lime and salt", 12.0f),
        Ingredient("hard_cider", "Honeycrisp Hard Apple Cider", IngredientType.BEER, 5.5f, Color(0xFFF8C471), 12.0f, "oz", "Effervescent fermented heritage orchard apples with balanced sweet-tart orchard freshness", 16.0f)
    )

    val allBeers: List<Ingredient> = americanBeers + belgiumBeers + hollandBeers + germanyBeers + englandBeers + scotlandBeers + mexicoBeers + internationalBeers + alternativeBeers + classicBeerCraft
}
