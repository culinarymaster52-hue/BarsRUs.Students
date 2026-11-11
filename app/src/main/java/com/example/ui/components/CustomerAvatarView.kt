package com.example.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.SentimentVerySatisfied
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.CustomerMood
import com.example.model.SpeedCustomer
import com.example.ui.theme.*

@Composable
fun CustomerOrderCard(
    customer: SpeedCustomer,
    targetCocktailName: String,
    timeLeftSeconds: Int,
    modifier: Modifier = Modifier
) {
    val progressFraction = (timeLeftSeconds.toFloat() / customer.totalTimeSeconds.toFloat()).coerceIn(0f, 1f)
    val timerColor by animateColorAsState(
        targetValue = when {
            progressFraction > 0.5f -> SuccessGreen
            progressFraction > 0.25f -> WarningOrange
            else -> ErrorRed
        },
        animationSpec = tween(400),
        label = "timer_color"
    )

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianCard)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Customer Avatar Circle with dynamic mood
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(ObsidianSurfaceVariant)
                        .border(2.dp, timerColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    when (customer.currentMood) {
                        CustomerMood.HAPPY -> Icon(Icons.Default.SentimentVerySatisfied, contentDescription = "Happy", tint = AmberGold, modifier = Modifier.size(32.dp))
                        CustomerMood.NEUTRAL -> Icon(Icons.Default.SentimentSatisfied, contentDescription = "Patient", tint = TextPrimary, modifier = Modifier.size(32.dp))
                        CustomerMood.IMPATIENT, CustomerMood.ANGRY -> Icon(Icons.Default.SentimentDissatisfied, contentDescription = "Upset", tint = ErrorRed, modifier = Modifier.size(32.dp))
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = customer.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                        )
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = timerColor.copy(alpha = 0.2f),
                            modifier = Modifier.padding(start = 6.dp)
                        ) {
                            Text(
                                text = "${timeLeftSeconds}s",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = timerColor
                                )
                            )
                        }
                    }
                    Text(
                        text = customer.roleTitle,
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Speech Bubble
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = ObsidianSurface,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = customer.dialogue,
                        style = MaterialTheme.typography.bodyMedium.copy(color = AmberGoldLight, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ORDER: ",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                        )
                        Text(
                            text = targetCocktailName,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.ExtraBold, color = TextPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Patience progress bar
            LinearProgressIndicator(
                progress = { progressFraction },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(RoundedCornerShape(3.dp)),
                color = timerColor,
                trackColor = ObsidianBg
            )
        }
    }
}
