package com.example.model

enum class QuizType(val label: String) {
    RATIO_CHALLENGE("Ratio Accuracy"),
    GLASS_MATCH("Glassware Match"),
    GARNISH_TECHNIQUE("Garnish & Technique"),
    BLIND_SPEC("Blind Spec Test"),
    SERVSAFE_LAW("Alcohol Law & RBS")
}

data class QuizQuestion(
    val id: String,
    val type: QuizType,
    val title: String,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val cocktailId: String? = null
)

data class LeaderboardEntry(
    val rank: Int,
    val username: String,
    val score: Int,
    val rankBadge: String,
    val accuracy: String,
    val isCurrentUser: Boolean = false
)

enum class CustomerMood(val label: String, val tipBonus: Int) {
    HAPPY("Delighted", 15),
    NEUTRAL("Patient", 8),
    IMPATIENT("Waiting...", 3),
    ANGRY("Upset!", 0)
}

data class SpeedCustomer(
    val id: String,
    val name: String,
    val roleTitle: String,
    val requestedCocktailId: String,
    val dialogue: String,
    val customTwist: String = "",
    val totalTimeSeconds: Int = 20,
    val currentMood: CustomerMood = CustomerMood.HAPPY
)

data class StudentBadge(
    val id: String,
    val title: String,
    val description: String,
    val iconEmoji: String,
    val unlocked: Boolean
)

data class StudentProfile(
    val studentName: String = "Apprentice Mixologist",
    val level: Int = 1,
    val xp: Int = 0,
    val maxXpForCurrentLevel: Int = 500,
    val totalQuizzesPassed: Int = 0,
    val ratioAccuracyScore: Float = 94.5f,
    val speedRushHighScore: Int = 0,
    val customCocktailsCreated: Int = 0,
    val servSafeQuizPassed: Boolean = false,
    val unlockedBadges: List<StudentBadge> = emptyList()
)

data class AlcoholLaw(
    val stateCode: String,
    val stateName: String,
    val legalAge: Int = 21,
    val legalBac: String = "0.08% BAC (0.00% Zero Tolerance for Under 21, 0.04% Commercial)",
    val standardDrinkOunces: String = "12 oz beer (5%), 5 oz wine (12%), 1.5 oz 80-proof spirits (40%) = ~14g pure ethanol",
    val servingHours: String,
    val dramShopLiability: String,
    val fakeIdPenalties: String,
    val rbsMandatory: Boolean,
    val rbsDescription: String
)
