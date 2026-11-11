package com.example.viewmodel

import android.app.Application
import android.content.Context
import android.location.Geocoder
import android.location.LocationManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BarCraftData
import com.example.data.CocktailCatalog
import com.example.database.BarCraftDatabase
import com.example.model.*
import com.example.repository.CocktailRepository
import com.example.util.PdfExporter
import com.example.util.ShareHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.Locale

enum class AppScreen {
    VIRTUAL_BAR,
    ACADEMY_QUIZ,
    SPEED_RUSH,
    COCKTAILS_LIBRARY,
    LAWS_SAFETY,
    AI_ASSISTANT
}

data class VirtualBarUiState(
    val selectedGlass: GlassType = GlassType.ROCKS,
    val hasIce: Boolean = true,
    val selectedGarnish: String = "orange_slice",
    val pouredInShaker: List<MeasuredIngredient> = emptyList(),
    val servedInGlass: List<MeasuredIngredient> = emptyList(),
    val bottleDepletions: Map<String, Float> = emptyMap(), // id -> remaining oz
    val isPouringId: String? = null,
    val isShaking: Boolean = false,
    val isStirring: Boolean = false,
    val shakeProgress: Float = 0f,
    val isStrainedToGlass: Boolean = false,
    val detectedRecipeMatch: CocktailRecipe? = null,
    val matchAccuracyPercent: Int = 0,
    val isNovelCreation: Boolean = false,
    val claimedCustomRecipe: CocktailRecipe? = null,
    val statusMessage: String = "Select spirits, mixers, and ice to pour into your shaker.",
    // Behind-the-Bar Stations & Tools
    val activeBarStation: BarStation = BarStation.SPEED_RAIL,
    val selectedJiggerMeasureOz: Float = 1.0f,
    val selectedBittersDashes: Int = 2, // Bitters measured in dashes 1 to 6
    val selectedWinePourOz: Float = 4.0f, // Wine pour measurements: 4oz and 8oz
    val selectedBeerPourOz: Float = 12.0f, // Beer pour measurements: 8oz and 12oz
    val activeStrainer: BarTool = BarTool.HAWTHORNE,
    val isDoubleStraining: Boolean = false,
    val isMuddling: Boolean = false,
    val isSmokingActive: Boolean = false,
    val isSmoked: Boolean = false,
    val selectedWoodChip: String = "Charred Bourbon Oak",
    val rimDressing: String? = null,
    val toolFeedbackMessage: String? = null
)

data class AcademyUiState(
    val currentQuestionIndex: Int = 0,
    val selectedOptionIndex: Int? = null,
    val isAnswerSubmitted: Boolean = false,
    val isAnswerCorrect: Boolean = false,
    val currentStreak: Int = 0,
    val totalScore: Int = 0,
    val studentProfile: StudentProfile = StudentProfile(
        studentName = "Alex Rivera",
        level = 3,
        xp = 1450,
        maxXpForCurrentLevel = 2000,
        totalQuizzesPassed = 8,
        ratioAccuracyScore = 96.2f,
        speedRushHighScore = 480,
        customCocktailsCreated = 2,
        servSafeQuizPassed = true,
        unlockedBadges = BarCraftData.defaultBadges
    ),
    val leaderboard: List<LeaderboardEntry> = BarCraftData.sampleLeaderboard
)

data class SpeedRushUiState(
    val isPlaying: Boolean = false,
    val secondsLeft: Int = 600, // 10 minutes shift (600 seconds)
    val roundNumber: Int = 1,
    val currentCustomerIndex: Int = 0, // 0 to 11 (dozen drinks per round)
    val roundDrinks: List<CocktailRecipe> = emptyList(), // Dozen (12) different distinct drinks
    val selectedGlass: GlassType? = null,
    val selectedTechnique: PrepTechnique? = null,
    val selectedGarnish: String? = null,
    val addedIngredients: List<MeasuredIngredient> = emptyList(),
    val score: Int = 0,
    val simulatedTipsEarned: Float = 0.0f,
    val drinksServedCount: Int = 0,
    val customerSatisfaction: CustomerMood = CustomerMood.HAPPY,
    val roundFinished: Boolean = false,
    val feedbackMessage: String = ""
)

data class LawsUiState(
    val selectedStateCode: String = "CA",
    val currentLaw: AlcoholLaw = BarCraftData.stateLaws[0],
    val locationDetectedCity: String = "San Francisco, CA",
    val isLocating: Boolean = false
)

class BarCraftViewModel(application: Application) : AndroidViewModel(application) {

    private val _currentScreen = MutableStateFlow(AppScreen.VIRTUAL_BAR)
    val currentScreen: StateFlow<AppScreen> = _currentScreen.asStateFlow()

    private val _barState = MutableStateFlow(VirtualBarUiState())
    val barState: StateFlow<VirtualBarUiState> = _barState.asStateFlow()

    private val _academyState = MutableStateFlow(AcademyUiState())
    val academyState: StateFlow<AcademyUiState> = _academyState.asStateFlow()

    private val _speedRushState = MutableStateFlow(SpeedRushUiState())
    val speedRushState: StateFlow<SpeedRushUiState> = _speedRushState.asStateFlow()

    private val _lawsState = MutableStateFlow(LawsUiState())
    val lawsState: StateFlow<LawsUiState> = _lawsState.asStateFlow()

    private val _customRecipes = MutableStateFlow<List<CocktailRecipe>>(emptyList())
    val customRecipes: StateFlow<List<CocktailRecipe>> = _customRecipes.asStateFlow()

    // Room Database & Repository Layer for 300 Cocktail Recipes
    private val database = BarCraftDatabase.getDatabase(application)
    val cocktailRepository = CocktailRepository(database.cocktailDao())

    val libraryRecipes: StateFlow<List<CocktailRecipe>> = cocktailRepository.allCocktails
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CocktailCatalog.allCocktails
        )

    private val prefs = application.getSharedPreferences("bars_r_us_prefs", Context.MODE_PRIVATE)

    private val _isProLicensed = MutableStateFlow(true)
    val isProLicensed: StateFlow<Boolean> = _isProLicensed.asStateFlow()

    private val _showPaywallModal = MutableStateFlow(false)
    val showPaywallModal: StateFlow<Boolean> = _showPaywallModal.asStateFlow()

    private var speedTimerJob: Job? = null
    private var shakeJob: Job? = null

    init {
        // Initialize bottle depletion map with full volumes
        val initialDepletions = BarCraftData.ingredients.associate { it.id to it.maxCapacityOz }
        _barState.update { it.copy(bottleDepletions = initialDepletions) }

        // Seed or update the Room database with the 300 cocktail catalog on launch
        viewModelScope.launch {
            cocktailRepository.ensurePopulated(CocktailCatalog.allCocktails)
        }
    }

    fun toggleRecipeFavorite(recipeId: String, currentFavorite: Boolean) {
        viewModelScope.launch {
            cocktailRepository.setFavorite(recipeId, !currentFavorite)
        }
    }

    fun openPaywall() {
        _showPaywallModal.value = true
    }

    fun closePaywall() {
        _showPaywallModal.value = false
    }

    fun unlockProLicense() {
        _isProLicensed.value = true
        _showPaywallModal.value = false
    }

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    // === VIRTUAL BAR ACTIONS ===
    fun setBarStation(station: BarStation) {
        _barState.update { it.copy(activeBarStation = station) }
    }

    fun setJiggerMeasure(oz: Float) {
        _barState.update { 
            it.copy(
                selectedJiggerMeasureOz = oz,
                statusMessage = "Japanese Jigger dialed to $oz oz for precision pour."
            ) 
        }
    }

    fun setGlassType(glass: GlassType) {
        _barState.update { it.copy(selectedGlass = glass) }
    }

    fun toggleIce() {
        _barState.update { it.copy(hasIce = !it.hasIce) }
    }

    fun setGarnish(garnishId: String) {
        _barState.update { it.copy(selectedGarnish = garnishId) }
    }

    fun useIceTongs(add: Boolean) {
        _barState.update { 
            it.copy(
                hasIce = add,
                statusMessage = if (add) "Used sanitary tongs to place crystalline ice into vessel." else "Emptied ice with tongs."
            ) 
        }
    }

    fun useMuddler() {
        viewModelScope.launch {
            _barState.update { it.copy(isMuddling = true, statusMessage = "Muddling fresh herbs & citrus in tin to release essential aromatic oils...") }
            delay(1200)
            _barState.update { 
                it.copy(
                    isMuddling = false,
                    statusMessage = "Muddled thoroughly! Botanicals and essential citrus oils extracted."
                ) 
            }
        }
    }

    fun useBarSpoon() {
        startStir()
    }

    fun useParingKnife(garnishId: String) {
        _barState.update { 
            it.copy(
                selectedGarnish = garnishId,
                statusMessage = "Trimmed, notched, and expressed ${BarCraftData.getIngredient(garnishId).name} using paring knife."
            ) 
        }
    }

    fun useCitrusSqueezer(ingredientId: String) {
        val ing = BarCraftData.getIngredient(ingredientId)
        val oz = _barState.value.selectedJiggerMeasureOz
        pourIngredient(ing, oz)
    }

    fun useGlassRimmer(dressing: String) {
        _barState.update { 
            it.copy(
                rimDressing = dressing,
                statusMessage = "Rotated glass rim in lime sponge and crusted with $dressing."
            ) 
        }
    }

    fun clearGlassRim() {
        _barState.update { it.copy(rimDressing = null, statusMessage = "Wiped and polished glass rim clean.") }
    }

    fun equipStrainer(strainer: BarTool) {
        _barState.update { 
            it.copy(
                activeStrainer = strainer,
                statusMessage = "Equipped ${strainer.displayName} snugly over the vessel rim."
            ) 
        }
    }

    fun toggleDoubleStraining() {
        _barState.update { 
            it.copy(
                isDoubleStraining = !it.isDoubleStraining,
                statusMessage = if (!it.isDoubleStraining) "Equipped Fine Mesh Conical Strainer for double-straining." else "Removed fine mesh strainer."
            ) 
        }
    }

    fun useCocktailSmoker(woodType: String) {
        viewModelScope.launch {
            _barState.update { 
                it.copy(
                    isSmokingActive = true,
                    selectedWoodChip = woodType,
                    statusMessage = "Igniting $woodType chips with torch... Billowing rich aromatic campfire smoke into glass!"
                ) 
            }
            delay(1800)
            _barState.update { 
                it.copy(
                    isSmokingActive = false,
                    isSmoked = true,
                    statusMessage = "Cocktail glass infused with dense, aromatic $woodType smoke!"
                ) 
            }
        }
    }

    fun clearSmoke() {
        _barState.update { it.copy(isSmoked = false, isSmokingActive = false, statusMessage = "Smoke cleared from glass.") }
    }

    fun useBarTowel() {
        _barState.update { 
            it.copy(
                statusMessage = "Wiped bar station with quat-sanitized microfiber bar towel. Spotless work counter!"
            ) 
        }
    }

    fun isIngredientUnlocked(ingredientId: String): Boolean {
        return true
    }

    fun setBittersDashes(dashes: Int) {
        val clamped = dashes.coerceIn(1, 6)
        _barState.update {
            it.copy(
                selectedBittersDashes = clamped,
                statusMessage = "Aromatic Bitters dialed to $clamped ${if (clamped == 1) "dash" else "dashes"} (${String.format(Locale.US, "%.2f", clamped * 0.03f)} oz)."
            )
        }
    }

    fun setWinePourMeasure(oz: Float) {
        val pour = if (oz >= 6.0f) 8.0f else 4.0f
        _barState.update {
            it.copy(
                selectedWinePourOz = pour,
                statusMessage = "Wine pour measurement set to ${pour.toInt()} oz ${if (pour == 4.0f) "(Standard Tasting Glass)" else "(Large Glass / Carafe)"}."
            )
        }
    }

    fun setBeerPourMeasure(oz: Float) {
        val pour = if (oz <= 10.0f) 8.0f else 12.0f
        _barState.update {
            it.copy(
                selectedBeerPourOz = pour,
                statusMessage = "Beer pour measurement set to ${pour.toInt()} oz ${if (pour == 8.0f) "(Half Pint / Goblet Pour)" else "(Full 12 oz Bottle / Pint)"}."
            )
        }
    }

    fun pourBitters(ingredient: Ingredient, dashes: Int = _barState.value.selectedBittersDashes) {
        val amountOz = (dashes * 0.03f) // 1 dash ~ 0.03 oz
        pourIngredient(ingredient, amountOz)
    }

    fun pourIngredient(ingredient: Ingredient, amountOz: Float) {
        viewModelScope.launch {
            _barState.update { it.copy(isPouringId = ingredient.id) }
            delay(350) // Animated pouring stream duration

            _barState.update { current ->
                val currentVol = current.bottleDepletions[ingredient.id] ?: ingredient.maxCapacityOz
                val remaining = currentVol - amountOz
                // Automatically refill bottle once depleted (<= 0.05 oz)
                val isDepleted = remaining <= 0.05f
                val finalRemaining = if (isDepleted) ingredient.maxCapacityOz else remaining
                val updatedDepletions = current.bottleDepletions + (ingredient.id to finalRemaining)

                val existing = current.pouredInShaker.find { it.ingredient.id == ingredient.id }
                val updatedPoured = if (existing != null) {
                    current.pouredInShaker.map {
                        if (it.ingredient.id == ingredient.id) it.copy(amountOz = it.amountOz + amountOz) else it
                    }
                } else {
                    current.pouredInShaker + MeasuredIngredient(ingredient, amountOz)
                }

                val refillNotice = if (isDepleted) " Bottle emptied and automatically refilled with a fresh 750ml bottle! 🍾" else ""

                current.copy(
                    pouredInShaker = updatedPoured,
                    bottleDepletions = updatedDepletions,
                    isPouringId = null,
                    statusMessage = "Poured ${String.format(Locale.US, "%.2f", amountOz)} ${ingredient.unit} of ${ingredient.name}.$refillNotice"
                )
            }
        }
    }

    fun startShake() {
        if (_barState.value.isShaking) return
        shakeJob?.cancel()
        shakeJob = viewModelScope.launch {
            _barState.update { it.copy(isShaking = true) }
            var p = 0f
            while (p < 1f) {
                delay(120)
                p += 0.15f
                _barState.update { it.copy(shakeProgress = p.coerceAtMost(1f)) }
            }
            delay(200)
            _barState.update { 
                it.copy(
                    isShaking = false,
                    statusMessage = "Vigorously shaken & aerated! Shaker tin is ice-cold."
                ) 
            }
        }
    }

    fun startStir() {
        if (_barState.value.isStirring) return
        viewModelScope.launch {
            _barState.update { it.copy(isStirring = true, statusMessage = "Gently stirring with barspoon...") }
            delay(1500)
            _barState.update { it.copy(isStirring = false, statusMessage = "Silky smooth and chilled without over-aeration.") }
        }
    }

    fun strainToServingGlass() {
        val shakerLiquid = _barState.value.pouredInShaker
        if (shakerLiquid.isEmpty()) return

        // Evaluate against classic recipes
        var bestMatch: CocktailRecipe? = null
        var bestAccuracy = 0

        for (recipe in BarCraftData.classicCocktails) {
            val acc = calculateAccuracy(shakerLiquid, recipe)
            if (acc > bestAccuracy) {
                bestAccuracy = acc
                bestMatch = recipe
            }
        }

        val isMatch = bestAccuracy >= 75
        val isNovel = !isMatch

        _barState.update { current ->
            current.copy(
                servedInGlass = shakerLiquid,
                pouredInShaker = emptyList(),
                isStrainedToGlass = true,
                detectedRecipeMatch = if (isMatch) bestMatch else null,
                matchAccuracyPercent = if (isMatch) bestAccuracy else 0,
                isNovelCreation = isNovel,
                statusMessage = if (isMatch) {
                    "Strained! Matches ${bestMatch?.name} with $bestAccuracy% accuracy!"
                } else {
                    "Unique custom drink created! You can claim this creation as your own."
                }
            )
        }

        // Award XP if accurate
        if (isMatch && bestAccuracy >= 85) {
            awardStudentXp(150, "Crafted Authentic ${bestMatch?.name} ($bestAccuracy%)")
        }
    }

    private fun calculateAccuracy(poured: List<MeasuredIngredient>, target: CocktailRecipe): Int {
        var score = 0f
        var totalTargetOz = target.ingredients.sumOf { it.amountOz.toDouble() }.toFloat().coerceAtLeast(0.1f)

        target.ingredients.forEach { targetItem ->
            val match = poured.find { it.ingredient.id == targetItem.ingredient.id }
            if (match != null) {
                val ratio = (1.0f - kotlin.math.abs(match.amountOz - targetItem.amountOz) / targetItem.amountOz).coerceIn(0f, 1f)
                score += ratio * (targetItem.amountOz / totalTargetOz)
            }
        }

        // Deduct for extraneous ingredients
        val extraneous = poured.filter { p -> target.ingredients.none { it.ingredient.id == p.ingredient.id } }
        val penalty = (extraneous.size * 0.15f)

        return ((score - penalty).coerceIn(0f, 1f) * 100).toInt()
    }

    fun claimCustomCocktail(name: String, author: String, description: String, garnish: String) {
        val glass = _barState.value.selectedGlass
        val ingredients = _barState.value.servedInGlass.ifEmpty { _barState.value.pouredInShaker }
        val newRecipe = CocktailRecipe(
            id = "custom_${System.currentTimeMillis()}",
            name = name.ifBlank { "Signature Mixologist Creation" },
            category = "Custom Creation",
            glass = glass,
            iceRequired = _barState.value.hasIce,
            technique = if (_barState.value.shakeProgress > 0.5f) PrepTechnique.SHAKEN else PrepTechnique.STIRRED,
            ingredients = ingredients,
            garnish = garnish.ifBlank { "Custom Rim & Twist" },
            description = description.ifBlank { "Original cocktail formulation created in Bars R Us Academy." },
            historyNotes = "Claimed and registered by $author at Bars R Us Mixology Lab.",
            isCustom = true,
            creatorName = author.ifBlank { "Student Bartender" },
            creationTimestamp = System.currentTimeMillis()
        )

        _customRecipes.update { listOf(newRecipe) + it }
        viewModelScope.launch {
            cocktailRepository.insertCocktail(newRecipe)
        }
        _barState.update {
            it.copy(
                claimedCustomRecipe = newRecipe,
                isNovelCreation = false,
                statusMessage = "Successfully registered '$name'! Saved to Room database and ready to export formatted PDF."
            )
        }

        awardStudentXp(250, "Claimed Original Cocktail '$name'")
    }

    fun resetBar() {
        _barState.update {
            it.copy(
                pouredInShaker = emptyList(),
                servedInGlass = emptyList(),
                isStrainedToGlass = false,
                shakeProgress = 0f,
                detectedRecipeMatch = null,
                isNovelCreation = false,
                claimedCustomRecipe = null,
                isSmoked = false,
                isSmokingActive = false,
                rimDressing = null,
                statusMessage = "Bar sanitized and reset. Shaker and tools ready for next order."
            )
        }
    }

    // === ACADEMY QUIZ ACTIONS ===
    fun selectQuizOption(index: Int) {
        if (_academyState.value.isAnswerSubmitted) return
        _academyState.update { it.copy(selectedOptionIndex = index) }
    }

    fun submitQuizAnswer() {
        val state = _academyState.value
        val selected = state.selectedOptionIndex ?: return
        val currentQ = BarCraftData.quizQuestions[state.currentQuestionIndex % BarCraftData.quizQuestions.size]
        val isCorrect = selected == currentQ.correctIndex

        val newStreak = if (isCorrect) state.currentStreak + 1 else 0
        val earnedPoints = if (isCorrect) 100 + (newStreak * 20) else 0

        _academyState.update {
            it.copy(
                isAnswerSubmitted = true,
                isAnswerCorrect = isCorrect,
                currentStreak = newStreak,
                totalScore = it.totalScore + earnedPoints
            )
        }

        if (isCorrect) {
            awardStudentXp(earnedPoints, "Passed Quiz: ${currentQ.title}")
        }
    }

    fun nextQuizQuestion() {
        val nextIdx = (_academyState.value.currentQuestionIndex + 1) % BarCraftData.quizQuestions.size
        _academyState.update {
            it.copy(
                currentQuestionIndex = nextIdx,
                selectedOptionIndex = null,
                isAnswerSubmitted = false,
                isAnswerCorrect = false
            )
        }
    }

    // === SPEED RUSH ACTIONS (10-MINUTE SHIFTS & DOZEN ROTATING DRINKS) ===
    private fun getRoundDrinks(round: Int): List<CocktailRecipe> {
        val all = CocktailCatalog.allCocktails
        val offset = ((round - 1) * 12) % all.size
        return (0 until 12).map { i ->
            all[(offset + i) % all.size]
        }
    }

    fun startSpeedRush() {
        speedTimerJob?.cancel()
        val round = _speedRushState.value.roundNumber
        val drinks = getRoundDrinks(round)

        _speedRushState.update {
            it.copy(
                isPlaying = true,
                secondsLeft = 600, // Exactly 10 minutes (600 seconds)
                roundNumber = round,
                currentCustomerIndex = 0,
                roundDrinks = drinks,
                selectedGlass = null,
                selectedTechnique = null,
                selectedGarnish = null,
                addedIngredients = emptyList(),
                score = 0,
                simulatedTipsEarned = 0f,
                drinksServedCount = 0,
                customerSatisfaction = CustomerMood.HAPPY,
                roundFinished = false,
                feedbackMessage = "10-Minute Rush Hour Shift started! First customer is waiting."
            )
        }

        speedTimerJob = viewModelScope.launch {
            while (_speedRushState.value.secondsLeft > 0 && _speedRushState.value.isPlaying) {
                delay(1000)
                _speedRushState.update { current ->
                    val newSec = current.secondsLeft - 1
                    val mood = when {
                        newSec > 300 -> CustomerMood.HAPPY
                        newSec > 120 -> CustomerMood.NEUTRAL
                        newSec > 30 -> CustomerMood.IMPATIENT
                        else -> CustomerMood.ANGRY
                    }
                    current.copy(secondsLeft = newSec, customerSatisfaction = mood)
                }
            }

            if (_speedRushState.value.secondsLeft <= 0) {
                finishSpeedRush()
            }
        }
    }

    fun nextSpeedRushRound() {
        speedTimerJob?.cancel()
        val nextRound = _speedRushState.value.roundNumber + 1
        _speedRushState.update { it.copy(roundNumber = nextRound) }
        startSpeedRush()
    }

    fun selectSpeedGlass(glass: GlassType) {
        _speedRushState.update { it.copy(selectedGlass = glass) }
    }

    fun selectSpeedTechnique(technique: PrepTechnique) {
        _speedRushState.update { it.copy(selectedTechnique = technique) }
    }

    fun selectSpeedGarnish(garnish: String) {
        _speedRushState.update { it.copy(selectedGarnish = garnish) }
    }

    fun addSpeedIngredient(ingredient: Ingredient, amountOz: Float) {
        _speedRushState.update { current ->
            val existing = current.addedIngredients.find { it.ingredient.id == ingredient.id }
            val updated = if (existing != null) {
                current.addedIngredients.map {
                    if (it.ingredient.id == ingredient.id) it.copy(amountOz = it.amountOz + amountOz) else it
                }
            } else {
                current.addedIngredients + MeasuredIngredient(ingredient, amountOz)
            }
            current.copy(addedIngredients = updated)
        }
    }

    fun removeSpeedIngredient(index: Int) {
        _speedRushState.update { current ->
            val updated = current.addedIngredients.toMutableList()
            if (index in updated.indices) {
                updated.removeAt(index)
            }
            current.copy(addedIngredients = updated)
        }
    }

    fun clearSpeedCraft() {
        _speedRushState.update {
            it.copy(
                selectedGlass = null,
                selectedTechnique = null,
                selectedGarnish = null,
                addedIngredients = emptyList()
            )
        }
    }

    fun serveSpeedCustomer() {
        val state = _speedRushState.value
        if (!state.isPlaying || state.roundDrinks.isEmpty()) return

        val targetRecipe = state.roundDrinks[state.currentCustomerIndex.coerceIn(0, state.roundDrinks.size - 1)]

        // 1. Evaluate Glass Match (25%)
        val glassMatch = state.selectedGlass == targetRecipe.glass
        val glassScore = if (glassMatch) 25 else 0

        // 2. Evaluate Technique Match (25%)
        val techniqueMatch = state.selectedTechnique == targetRecipe.technique
        val techniqueScore = if (techniqueMatch) 25 else 0

        // 3. Evaluate Garnish Match (20%)
        val garnishMatch = state.selectedGarnish != null &&
                (targetRecipe.garnish.contains(state.selectedGarnish, ignoreCase = true) ||
                 state.selectedGarnish.contains(targetRecipe.garnish.split(" ").first(), ignoreCase = true))
        val garnishScore = if (garnishMatch) 20 else 5

        // 4. Evaluate Ingredients & Measurements (30%)
        val ingAccuracy = if (state.addedIngredients.isNotEmpty()) calculateAccuracy(state.addedIngredients, targetRecipe) else 0
        val ingScore = (ingAccuracy * 0.30f).toInt()

        val totalAccuracy = (glassScore + techniqueScore + garnishScore + ingScore).coerceIn(0, 100)

        // Tips calculation
        val tipBase = when {
            totalAccuracy >= 90 -> 8.0f
            totalAccuracy >= 75 -> 5.0f
            totalAccuracy >= 50 -> 2.5f
            else -> 0.5f
        }
        val points = (totalAccuracy * 10) + (state.secondsLeft / 10)

        val nextIndex = state.currentCustomerIndex + 1
        val isRoundFinished = nextIndex >= 12

        val feedback = buildString {
            append("Served #${state.currentCustomerIndex + 1} (${targetRecipe.name}): $totalAccuracy% Accuracy. ")
            if (!glassMatch) append("Wrong Glass (${state.selectedGlass?.displayName ?: "None"} vs ${targetRecipe.glass.displayName}). ")
            if (!techniqueMatch) append("Wrong Method (${state.selectedTechnique?.actionName ?: "None"} vs ${targetRecipe.technique.actionName}). ")
            append("Tip earned: +$${String.format(Locale.US, "%.2f", tipBase)}!")
        }

        _speedRushState.update { current ->
            current.copy(
                currentCustomerIndex = nextIndex,
                roundFinished = isRoundFinished,
                score = current.score + points,
                simulatedTipsEarned = current.simulatedTipsEarned + tipBase,
                drinksServedCount = current.drinksServedCount + 1,
                feedbackMessage = feedback,
                selectedGlass = null,
                selectedTechnique = null,
                selectedGarnish = null,
                addedIngredients = emptyList()
            )
        }

        if (isRoundFinished) {
            finishSpeedRush()
        }
    }

    private fun finishSpeedRush() {
        speedTimerJob?.cancel()
        val finalScore = _speedRushState.value.score
        _speedRushState.update {
            it.copy(
                isPlaying = false,
                roundFinished = true,
                feedbackMessage = "10-Minute Shift Complete! You served ${it.drinksServedCount} of 12 drinks and earned $${String.format(Locale.US, "%.2f", it.simulatedTipsEarned)} in tips! Score: $finalScore"
            )
        }

        // Update high score on leaderboard
        awardStudentXp(300 + (finalScore / 2), "10-Min Speed Rush Shift Complete")
        _academyState.update { cur ->
            val updatedProfile = cur.studentProfile.copy(
                speedRushHighScore = maxOf(cur.studentProfile.speedRushHighScore, finalScore)
            )
            val updatedLb = cur.leaderboard.map { entry ->
                if (entry.isCurrentUser) entry.copy(score = maxOf(entry.score, finalScore)) else entry
            }.sortedByDescending { it.score }
            cur.copy(studentProfile = updatedProfile, leaderboard = updatedLb)
        }
    }

    // === ALCOHOL LAWS & LOCATION ACTIONS ===
    fun selectStateLaw(stateCode: String) {
        val law = BarCraftData.getLawForState(stateCode)
        _lawsState.update { it.copy(selectedStateCode = stateCode, currentLaw = law) }
    }

    fun detectLocation(context: Context) {
        _lawsState.update { it.copy(isLocating = true) }
        viewModelScope.launch {
            try {
                val lm = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
                val isGpsEnabled = lm?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
                val isNetworkEnabled = lm?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false

                if (isGpsEnabled || isNetworkEnabled) {
                    val location = try {
                        lm?.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                            ?: lm?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                    } catch (se: SecurityException) {
                        null
                    }

                    if (location != null && Geocoder.isPresent()) {
                        val geocoder = Geocoder(context, Locale.getDefault())
                        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
                        if (!addresses.isNullOrEmpty()) {
                            val addr = addresses[0]
                            val stateCode = addr.adminArea ?: "CA"
                            val matchedState = BarCraftData.stateLaws.find { 
                                it.stateName.equals(stateCode, ignoreCase = true) || it.stateCode.equals(stateCode, ignoreCase = true) 
                            }?.stateCode ?: "CA"

                            _lawsState.update {
                                it.copy(
                                    selectedStateCode = matchedState,
                                    currentLaw = BarCraftData.getLawForState(matchedState),
                                    locationDetectedCity = "${addr.locality ?: "Local"}, ${addr.adminArea ?: "US"}",
                                    isLocating = false
                                )
                            }
                            return@launch
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            // Fallback default
            _lawsState.update { it.copy(isLocating = false, locationDetectedCity = "Default Region (Select State Below)") }
        }
    }

    // === PDF EXPORT & SHARING ===
    fun exportAndShareRecipePdf(context: Context, recipe: CocktailRecipe) {
        val studentName = _academyState.value.studentProfile.studentName
        val pdfFile = PdfExporter.exportRecipePdf(context, recipe, studentName)
        if (pdfFile != null) {
            PdfExporter.sharePdf(context, pdfFile, "${recipe.name} - Cocktail Recipe Card")
        }
    }

    fun exportAndShareTranscriptPdf(context: Context) {
        val profile = _academyState.value.studentProfile
        val pdfFile = PdfExporter.exportStudentTranscriptPdf(context, profile)
        if (pdfFile != null) {
            PdfExporter.sharePdf(context, pdfFile, "Bars R Us Academy Academic Transcript - ${profile.studentName}")
        }
    }

    private fun awardStudentXp(amount: Int, reason: String) {
        _academyState.update { current ->
            val newXp = current.studentProfile.xp + amount
            val currentLevel = current.studentProfile.level
            val maxXp = current.studentProfile.maxXpForCurrentLevel

            val (finalLevel, finalMaxXp) = if (newXp >= maxXp) {
                Pair(currentLevel + 1, maxXp + 1000)
            } else {
                Pair(currentLevel, maxXp)
            }

            val updatedProfile = current.studentProfile.copy(
                xp = newXp,
                level = finalLevel,
                maxXpForCurrentLevel = finalMaxXp
            )
            current.copy(studentProfile = updatedProfile)
        }
    }
}
