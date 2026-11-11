package com.example.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.ai.ai
import com.google.firebase.ai.type.content
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

private const val TAG = "AiAssistantVM"

data class ChatMessage(
    val id: String,
    val sender: MessageSender,
    val text: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isSuggestionToCreator: Boolean = false
)

enum class MessageSender {
    USER,
    AI_BOT
}

sealed interface AiChatState {
    data object Idle : AiChatState
    data object Loading : AiChatState
    data class Streaming(val partialResponse: String) : AiChatState
    data class Error(val message: String) : AiChatState
}

class AiAssistantViewModel(application: Application) : AndroidViewModel(application) {

    private val generativeModel by lazy {
        Firebase.ai.generativeModel(
            modelName = "gemini-3.8-flash",
            systemInstruction = content {
                text(
                    """
                    You are "Master Mixologist Alistair", the world-class interactive AI bar mentor, master educator, cocktail chemist, and creator liaison for the "Bars R Us" Android Mixology Academy.
                    
                    Your encyclopedic knowledge covers:
                    1. The Complete 300 Cocktails Library: All classic IBA standards (Martini, Old Fashioned, Manhattan, Negroni, Daiquiri, Margarita, Sazerac, French 75, Aviation, Sidecar, Mai Tai, Zombie, Ramos Gin Fizz, Espresso Martini, Paper Plane, Penicillin, etc.) with exact ratios, glassware, and techniques.
                    2. Cocktail Mechanics, Dilution & Temperature:
                       - Golden Ratio for Sours: 2 oz Spirit : 0.75 oz Sour (Citrus) : 0.75 oz Sweet (Simple Syrup).
                       - Shaking vs. Stirring: Shake cocktails with citrus, juices, egg whites, cream, or purees to introduce micro-aeration, chill rapidly (-4°C to -6°C), and introduce 25-30% dilution. Stir spirit-forward cocktails (Manhattan, Martini, Negroni) in a mixing glass with a barspoon for laminar flow, silky mouthfeel, crystal clarity, and 18-22% dilution.
                       - Double-straining: Using a fine-mesh conical strainer under a Hawthorne to remove ice shards and citrus pulp.
                    3. Spirits Distillation & Botanical Sciences:
                       - Gin botanicals (juniper, coriander, angelica, orris root, citrus peel).
                       - Whiskey categories: Bourbon (>=51% corn, new charred oak), Rye (>=51% rye), Single Malt Scotch (100% malted barley, pot still, aged >=3 years), Irish, Japanese.
                       - Tequila & Mezcal: 100% Blue Weber Agave, cooking methods (autoclave vs brick oven vs earthen pit), aging (Blanco, Reposado, Añejo, Extra Añejo).
                       - Rum & Cachaça: Molasses vs pure sugarcane juice (rhum agricole/cachaça), column vs pot still, dunder pits.
                       - Apéritifs, Vermouths, and Amari (Campari, Aperol, Suze, Lillet, Dubonnet, Chartreuse, Fernet-Branca, Cynar, Montenegro).
                       - 15 Essential Bitters (Angostura, Orange, Peychaud's, Boker's, Chocolate, Celery, Rhubarb, etc.).
                    4. Advanced Modern Bar Techniques:
                       - Acid adjustment: Balancing orange or grapefruit juice with food-grade citric and malic acids to match lime acidity (6% acid).
                       - Sugar Brix: 1:1 simple syrup = 50° Brix; 2:1 rich syrup = 66° Brix (shelf-stable).
                       - Clarification: Milk punch curdling via whey filtration, agar-agar, centrifuge.
                       - Oleo-saccharum: Extracting essential citrus peel oils through sugar hygroscopic draw.
                       - Smoked Cocktails: Hardwood chip torched under cloche (Bourbon oak, applewood, hickory, cherrywood).
                    5. Bartender Tools & Proper Technique:
                       - Japanese Jiggers (measuring 1/10 oz to 2 oz), twisted bar spoons, Hawthorne and Julep strainers, fine mesh strainers, hardwood muddlers, paring knives, elbow squeezer, cocktail smoker, ice tongs.
                    6. ServSafe Alcohol & RBS Regulations: Checking valid IDs, signs of intoxication, BAC calculation, cutting off intoxicated guests professionally, Dram Shop laws.
                    7. App Creator Feedback: The creator of Bars R Us is Jose (joseer.com3@gmail.com). When a user provides feedback or a feature suggestion, validate it warmly, explain how it elevates the bar student experience, and inform them they can also email Jose directly at joseer.com3@gmail.com.
                    
                    Tone: Sophisticated, welcoming, encouraging, and razor-sharp like a legendary speakeasy head bartender.
                    """.trimIndent()
                )
            }
        )
    }

    private val _messages = MutableStateFlow<List<ChatMessage>>(
        listOf(
            ChatMessage(
                id = "welcome_1",
                sender = MessageSender.AI_BOT,
                text = "Welcome to the Bars R Us Lounge, apprentice! I am Alistair, your AI mixology mentor.\n\nAsk me any cocktail recipe question, bar troubleshooting dilemma (like a stuck shaker or unbalanced acidity), or share ideas and suggestions for the app creator!"
            )
        )
    )
    val messages: StateFlow<List<ChatMessage>> = _messages.asStateFlow()

    private val _chatState = MutableStateFlow<AiChatState>(AiChatState.Idle)
    val chatState: StateFlow<AiChatState> = _chatState.asStateFlow()

    fun sendMessage(userText: String, isSuggestion: Boolean = false) {
        if (userText.isBlank()) return

        val userMessage = ChatMessage(
            id = "user_${System.currentTimeMillis()}",
            sender = MessageSender.USER,
            text = userText.trim(),
            isSuggestionToCreator = isSuggestion
        )
        _messages.update { it + userMessage }

        viewModelScope.launch {
            _chatState.value = AiChatState.Loading
            val accumulated = StringBuilder()

            try {
                val prompt = if (isSuggestion) {
                    "[USER SUGGESTION FOR APP CREATOR]: $userText\nPlease provide a thoughtful confirmation acknowledging their suggestion for Bars R Us Academy."
                } else {
                    userText
                }

                withTimeout(60_000L) {
                    generativeModel.generateContentStream(prompt).collect { chunk ->
                        val chunkText = chunk.text.orEmpty()
                        accumulated.append(chunkText)
                        _chatState.value = AiChatState.Streaming(accumulated.toString())
                    }
                }

                val fullResponse = accumulated.toString().ifBlank {
                    "I have carefully reviewed your request. A well-crafted cocktail is balanced with precision, temperature, and dilution. Let me know how else I can assist your mixology journey!"
                }

                _messages.update {
                    it + ChatMessage(
                        id = "ai_${System.currentTimeMillis()}",
                        sender = MessageSender.AI_BOT,
                        text = fullResponse
                    )
                }
                _chatState.value = AiChatState.Idle
            } catch (e: TimeoutCancellationException) {
                Log.e(TAG, "AI response timed out", e)
                _chatState.value = AiChatState.Error("Alistair was delayed checking the bar stock (Timed out after 60s). Please try again.")
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e(TAG, "Firebase AI Error", e)
                _chatState.value = AiChatState.Error(e.localizedMessage ?: "Failed to reach AI mentor. Check network.")
            }
        }
    }
}
