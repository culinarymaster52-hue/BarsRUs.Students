package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.AiAssistantViewModel
import com.example.viewmodel.AiChatState
import com.example.viewmodel.ChatMessage
import com.example.viewmodel.MessageSender
import kotlinx.coroutines.launch

@Composable
fun AiAssistantScreen(
    viewModel: AiAssistantViewModel,
    modifier: Modifier = Modifier
) {
    val messages by viewModel.messages.collectAsState()
    val chatState by viewModel.chatState.collectAsState()
    var inputQuery by remember { mutableStateOf("") }
    var isSendingSuggestion by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(messages.size, chatState) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    val samplePrompts = listOf(
        "Troubleshoot: Shaker tin is frozen shut!",
        "Why is my Whiskey Sour too tart?",
        "Suggest: Add smoked cocktail glass physics!",
        "What's the difference between Coupe and Martini glass?",
        "How do I prevent flat champagne in French 75?"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .padding(16.dp)
    ) {
        // AI Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(CircleShape)
                    .background(ObsidianCard)
                    .border(1.5.dp, AmberGold, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("🍸", fontSize = 22.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "ALISTAIR • AI BAR MENTOR",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.ExtraBold, color = AmberGold)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(shape = RoundedCornerShape(4.dp), color = SuccessGreen.copy(alpha = 0.2f)) {
                        Text(
                            text = "LIVE",
                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp),
                            style = MaterialTheme.typography.labelSmall.copy(color = SuccessGreen, fontWeight = FontWeight.Bold, fontSize = 8.sp)
                        )
                    }
                }
                Text(
                    text = "Q&A, troubleshooting, and creator suggestions",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Quick Suggestion Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(samplePrompts) { prompt ->
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = ObsidianSurfaceVariant,
                    border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(ObsidianCard)),
                    modifier = Modifier.clickable {
                        val isSug = prompt.startsWith("Suggest:")
                        inputQuery = prompt
                        isSendingSuggestion = isSug
                    }
                ) {
                    Text(
                        text = prompt,
                        style = MaterialTheme.typography.bodySmall.copy(color = AmberGoldLight, fontSize = 11.sp),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Chat Message List
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(messages) { msg ->
                ChatBubble(message = msg)
            }

            if (chatState is AiChatState.Loading) {
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(8.dp)
                    ) {
                        CircularProgressIndicator(modifier = Modifier.size(16.dp), color = AmberGold, strokeWidth = 2.dp)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Alistair is consulting mixology references...",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                        )
                    }
                }
            } else if (chatState is AiChatState.Streaming) {
                item {
                    val partial = (chatState as AiChatState.Streaming).partialResponse
                    ChatBubble(
                        message = ChatMessage(
                            id = "streaming_live",
                            sender = MessageSender.AI_BOT,
                            text = partial
                        )
                    )
                }
            } else if (chatState is AiChatState.Error) {
                item {
                    val err = (chatState as AiChatState.Error).message
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = ErrorRed.copy(alpha = 0.2f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "⚠️ $err",
                            color = ErrorRed,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Toggle & Direct Email to App Creator
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 2.dp)
        ) {
            Switch(
                checked = isSendingSuggestion,
                onCheckedChange = { isSendingSuggestion = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = AmberGold,
                    checkedTrackColor = AmberGoldDark,
                    uncheckedThumbColor = TextMuted,
                    uncheckedTrackColor = ObsidianCard
                ),
                modifier = Modifier.testTag("suggestion_toggle")
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isSendingSuggestion) "💡 Mode: Feedback for App Creator" else "💬 Mode: Mixology Q&A & Troubleshooting",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (isSendingSuggestion) AmberGold else TextSecondary
                    )
                )
                Text(
                    text = if (isSendingSuggestion) "Logs suggestions for Jose or email directly to joseer.com3@gmail.com" else "Ask Alistair recipe specs, proportions, or bar issues",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextMuted, fontSize = 9.5.sp)
                )
            }

            val context = androidx.compose.ui.platform.LocalContext.current
            IconButton(
                onClick = {
                    try {
                        val emailIntent = android.content.Intent(android.content.Intent.ACTION_SENDTO).apply {
                            data = android.net.Uri.parse("mailto:joseer.com3@gmail.com")
                            putExtra(android.content.Intent.EXTRA_SUBJECT, "[Bars R Us Suggestion / Feedback]")
                            putExtra(android.content.Intent.EXTRA_TEXT, inputQuery.ifBlank { "Hello Jose,\n\nI have a suggestion for the Bars R Us app:\n\n" })
                        }
                        context.startActivity(android.content.Intent.createChooser(emailIntent, "Send Suggestion to Creator"))
                    } catch (e: Exception) {
                        android.widget.Toast.makeText(context, "Creator email: joseer.com3@gmail.com", android.widget.Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(ObsidianCard)
            ) {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Email Creator Directly",
                    tint = AmberGold,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Input bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = inputQuery,
                onValueChange = { inputQuery = it },
                placeholder = {
                    Text(
                        if (isSendingSuggestion) "Type your suggestion for the creator..." else "Ask a recipe question or troubleshooting issue...",
                        fontSize = 12.sp
                    )
                },
                singleLine = false,
                maxLines = 3,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .weight(1f)
                    .testTag("ai_chat_input")
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    val q = inputQuery
                    val sug = isSendingSuggestion
                    inputQuery = ""
                    viewModel.sendMessage(q, sug)
                },
                enabled = inputQuery.isNotBlank() && chatState !is AiChatState.Loading,
                modifier = Modifier
                    .size(48.dp)
                    .background(if (inputQuery.isNotBlank()) AmberGold else ObsidianCard, CircleShape)
                    .testTag("ai_send_button")
            ) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = "Send",
                    tint = if (inputQuery.isNotBlank()) ObsidianBg else TextMuted
                )
            }
        }
    }
}

@Composable
private fun ChatBubble(message: ChatMessage) {
    val isUser = message.sender == MessageSender.USER
    val bubbleColor = if (isUser) {
        if (message.isSuggestionToCreator) Color(0xFF382347) else AmberGold.copy(alpha = 0.25f)
    } else {
        ObsidianSurface
    }

    val align = if (isUser) Alignment.End else Alignment.Start

    Column(
        horizontalAlignment = align,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (isUser && message.isSuggestionToCreator) {
            Text(
                text = "SUGGESTION FOR CREATOR",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = CopperLight,
                    fontSize = 9.sp
                ),
                modifier = Modifier.padding(bottom = 2.dp, end = 4.dp)
            )
        }

        Surface(
            shape = RoundedCornerShape(
                topStart = 14.dp,
                topEnd = 14.dp,
                bottomStart = if (isUser) 14.dp else 2.dp,
                bottomEnd = if (isUser) 2.dp else 14.dp
            ),
            color = bubbleColor,
            border = if (!isUser) ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(ObsidianCard)) else null,
            modifier = Modifier.widthIn(max = 320.dp)
        ) {
            Text(
                text = message.text,
                style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary, fontSize = 13.sp),
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}
