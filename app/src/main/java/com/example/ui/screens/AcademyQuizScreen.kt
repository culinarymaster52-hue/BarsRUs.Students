package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarCraftData
import com.example.model.LeaderboardEntry
import com.example.ui.theme.*
import com.example.util.ShareHelper
import com.example.viewmodel.BarCraftViewModel

@Composable
fun AcademyQuizScreen(
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val academyState by viewModel.academyState.collectAsState()
    var selectedTab by remember { mutableStateOf(0) } // 0: Quiz & Challenges, 1: Leaderboard, 2: Badges & Profile

    val currentQ = BarCraftData.quizQuestions[academyState.currentQuestionIndex % BarCraftData.quizQuestions.size]
    val profile = academyState.studentProfile

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .padding(16.dp)
    ) {
        // Academy Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "MIXOLOGY ACADEMY",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGold,
                        letterSpacing = 1.sp
                    )
                )
                Text(
                    text = "Level ${profile.level} • ${profile.studentName}",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            }

            // Streak Pill
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (academyState.currentStreak > 0) Color(0xFF4A2B0F) else ObsidianCard
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                ) {
                    Text("🔥", fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${academyState.currentStreak} Streak",
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = AmberGoldLight
                        )
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // XP Progress Bar
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("XP: ${profile.xp} / ${profile.maxXpForCurrentLevel}", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary))
                    Text("Ratio Accuracy: ${profile.ratioAccuracyScore}%", style = MaterialTheme.typography.labelSmall.copy(color = SuccessGreen, fontWeight = FontWeight.Bold))
                }
                Spacer(modifier = Modifier.height(6.dp))
                val xpFraction = (profile.xp.toFloat() / profile.maxXpForCurrentLevel.toFloat()).coerceIn(0f, 1f)
                LinearProgressIndicator(
                    progress = { xpFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = AmberGold,
                    trackColor = ObsidianCard
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Navigation Tabs (Quizzes / Leaderboard / Badges)
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = ObsidianSurface,
            contentColor = AmberGold,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }, text = { Text("Quizzes", fontSize = 12.sp) })
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }, text = { Text("Leaderboard", fontSize = 12.sp) })
            Tab(selected = selectedTab == 2, onClick = { selectedTab = 2 }, text = { Text("Transcript & Badges", fontSize = 12.sp) })
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Tab Content
        when (selectedTab) {
            0 -> QuizContent(viewModel = viewModel, currentQ = currentQ, academyState = academyState)
            1 -> LeaderboardContent(leaderboard = academyState.leaderboard, onShare = {
                ShareHelper.shareLeaderboardScore(context, profile.studentName, academyState.totalScore, "Level ${profile.level} Mixologist")
            })
            2 -> BadgesAndTranscriptContent(viewModel = viewModel, profile = profile)
        }
    }
}

@Composable
private fun QuizContent(
    viewModel: BarCraftViewModel,
    currentQ: com.example.model.QuizQuestion,
    academyState: com.example.viewmodel.AcademyUiState
) {
    val scrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState)) {
        // Question Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Category badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = CopperAccent.copy(alpha = 0.2f),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = currentQ.type.label.uppercase(),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = CopperLight)
                    )
                }

                Text(
                    text = currentQ.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = currentQ.prompt,
                    style = MaterialTheme.typography.bodyLarge.copy(color = TextPrimary, fontWeight = FontWeight.Medium)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Options
                currentQ.options.forEachIndexed { index, optionText ->
                    val isSelected = academyState.selectedOptionIndex == index
                    val isSubmitted = academyState.isAnswerSubmitted
                    val isCorrectOption = index == currentQ.correctIndex

                    val borderColor = when {
                        !isSubmitted && isSelected -> AmberGold
                        isSubmitted && isCorrectOption -> SuccessGreen
                        isSubmitted && isSelected && !isCorrectOption -> ErrorRed
                        else -> ObsidianCard
                    }

                    val bgColor = when {
                        !isSubmitted && isSelected -> AmberGold.copy(alpha = 0.15f)
                        isSubmitted && isCorrectOption -> SuccessGreen.copy(alpha = 0.2f)
                        isSubmitted && isSelected && !isCorrectOption -> ErrorRed.copy(alpha = 0.2f)
                        else -> ObsidianSurfaceVariant
                    }

                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = bgColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
                            .clickable(enabled = !isSubmitted) { viewModel.selectQuizOption(index) }
                            .testTag("quiz_option_$index")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) AmberGold else ObsidianCard),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = ('A' + index).toString(),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) ObsidianBg else TextSecondary
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = optionText,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = if (isSubmitted && isCorrectOption) SuccessGreen else TextPrimary,
                                    fontWeight = if (isSelected || (isSubmitted && isCorrectOption)) FontWeight.Bold else FontWeight.Normal
                                ),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Action buttons (Submit / Next)
                if (!academyState.isAnswerSubmitted) {
                    Button(
                        onClick = { viewModel.submitQuizAnswer() },
                        enabled = academyState.selectedOptionIndex != null,
                        colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("submit_quiz_button")
                    ) {
                        Text("Check Accuracy", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    }
                } else {
                    // Explanation Box
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = ObsidianCard,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = if (academyState.isAnswerCorrect) Icons.Default.CheckCircle else Icons.Default.Cancel,
                                    contentDescription = null,
                                    tint = if (academyState.isAnswerCorrect) SuccessGreen else ErrorRed,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (academyState.isAnswerCorrect) "Excellent Precision! (+100 XP)" else "Ratio Inaccurate",
                                    fontWeight = FontWeight.Bold,
                                    color = if (academyState.isAnswerCorrect) SuccessGreen else ErrorRed
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = currentQ.explanation,
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Button(
                        onClick = { viewModel.nextQuizQuestion() },
                        colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth().height(48.dp).testTag("next_quiz_button")
                    ) {
                        Text("Next Challenge", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun LeaderboardContent(
    leaderboard: List<LeaderboardEntry>,
    onShare: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        ) {
            Text(
                text = "GLOBAL BARTENDER STANDINGS",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
            )
            TextButton(onClick = onShare) {
                Icon(Icons.Default.Share, contentDescription = null, tint = AmberGold, modifier = Modifier.size(14.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Share Rank", color = AmberGold, fontSize = 11.sp)
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(leaderboard) { entry ->
                val isMe = entry.isCurrentUser
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = if (isMe) AmberGold.copy(alpha = 0.2f) else ObsidianSurface,
                    border = if (isMe) ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(AmberGold)) else null,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        // Rank position
                        Text(
                            text = when (entry.rank) {
                                1 -> "🥇"
                                2 -> "🥈"
                                3 -> "🥉"
                                else -> "#${entry.rank}"
                            },
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold),
                            modifier = Modifier.width(36.dp)
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = entry.username,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isMe) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isMe) AmberGold else TextPrimary
                                )
                            )
                            Text(
                                text = "${entry.rankBadge} • ${entry.accuracy} accuracy",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 10.sp)
                            )
                        }

                        Text(
                            text = "${entry.score} pts",
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BadgesAndTranscriptContent(
    viewModel: BarCraftViewModel,
    profile: com.example.model.StudentProfile
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // PDF Export Banner
        Card(
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.School, contentDescription = null, tint = AmberGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Official Academic Transcript (PDF)",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Generate and export your verified student transcript with all completed quiz scores, ratio accuracy metrics, and ServSafe certification attestation.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { viewModel.exportAndShareTranscriptPdf(context) },
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().testTag("export_transcript_pdf_button")
                ) {
                    Icon(Icons.Default.PictureAsPdf, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Export Formatted PDF Transcript", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "EARNED MIXOLOGY BADGES",
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
        )
        Spacer(modifier = Modifier.height(8.dp))

        profile.unlockedBadges.forEach { badge ->
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ObsidianSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = badge.iconEmoji, fontSize = 26.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = badge.title,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                        )
                        Text(
                            text = badge.description,
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(6.dp),
                        color = if (badge.unlocked) SuccessGreen.copy(alpha = 0.2f) else ObsidianCard
                    ) {
                        Text(
                            text = if (badge.unlocked) "UNLOCKED" else "LOCKED",
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = if (badge.unlocked) SuccessGreen else TextMuted,
                                fontSize = 9.sp
                            )
                        )
                    }
                }
            }
        }
    }
}
