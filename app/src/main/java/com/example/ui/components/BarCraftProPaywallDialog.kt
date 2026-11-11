package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*

import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString

/**
 * 100% Free Edition & Google Play Publishing Checklist Dialog.
 * All payment methods (PayPal, Venmo, Google Pay, Apple Pay) have been deleted
 * and all app features are 100% free and unlocked for every user.
 */
@Composable
fun BarCraftProPaywallDialog(
    isPro: Boolean,
    onDismiss: () -> Unit,
    onUnlockPro: () -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    var copiedLabel by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = null,
        text = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                // Free Access Badge
                Box(
                    modifier = Modifier
                        .size(58.dp)
                        .clip(CircleShape)
                        .background(SuccessGreen)
                        .border(3.dp, ObsidianBg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text("🚀", fontSize = 28.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "BARS R US • 100% FREE EDITION",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = AmberGold,
                        letterSpacing = 0.8.sp
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "All Features Unlocked • Google Play Publishing Guide",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                // 100% Free Banner
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = SuccessGreen.copy(alpha = 0.18f),
                    border = BorderStroke(1.dp, SuccessGreen),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null, tint = SuccessGreen)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "ALL FEATURES 100% FREE & UNLOCKED",
                                color = SuccessGreen,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 11.5.sp
                            )
                            Text(
                                text = "100+ Spirits, 114 Wines, 54 Beers, 300 Recipes, Speed Rush & AI Mixologist",
                                color = Color.White,
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Quick Copy Card for Package ID & Play Store Fields
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = ObsidianSurface,
                    border = BorderStroke(1.dp, AmberGold.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(10.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "QUICK-COPY PLAY CONSOLE FIELDS",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = AmberGold,
                                    fontSize = 10.5.sp
                                )
                            )
                            copiedLabel?.let { label ->
                                Text(
                                    text = "Copied $label!",
                                    color = SuccessGreen,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.5.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        CopyableFieldRow(
                            label = "Public Package ID / Application ID",
                            value = "com.barsrus.mixology.pub",
                            onCopy = {
                                clipboardManager.setText(AnnotatedString("com.barsrus.mixology.pub"))
                                copiedLabel = "Package ID"
                            }
                        )

                        CopyableFieldRow(
                            label = "App Name (30 chars max)",
                            value = "Bars R Us",
                            onCopy = {
                                clipboardManager.setText(AnnotatedString("Bars R Us"))
                                copiedLabel = "App Name"
                            }
                        )

                        CopyableFieldRow(
                            label = "Short Description (80 chars max)",
                            value = "Interactive bartending academy, virtual bar shaker, 300+ recipes & quizzes.",
                            onCopy = {
                                clipboardManager.setText(
                                    AnnotatedString("Interactive bartending academy, virtual bar shaker, 300+ recipes & quizzes.")
                                )
                                copiedLabel = "Short Desc"
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Step-by-Step Google Play Publishing Guide
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color.Black,
                    border = BorderStroke(1.dp, AmberGold),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(
                            text = "HOW TO PUBLISH PUBLICLY ON GOOGLE PLAY STORE",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = AmberGold,
                                fontSize = 11.5.sp
                            )
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Your public package ID is now set to 'com.barsrus.mixology.pub'. To publish publicly for everyone on the Google Play Store, follow these steps:",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White,
                                fontSize = 10.sp,
                                lineHeight = 13.5.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        PublishStepItem(
                            step = "1",
                            title = "Export Code via the 'GitHub' Tab (or Download ZIP)",
                            detail = "In the AI Studio top menu (right next to 'Publish' and 'Versions'), tap 'GitHub' to push your app's code to your GitHub account, or open the Code view on a computer to click 'Download ZIP'."
                        )
                        PublishStepItem(
                            step = "2",
                            title = "Public Package ID Already Updated",
                            detail = "Your app's applicationId is now 'com.barsrus.mixology.pub' (Version 6.0), so it will not conflict with the old AI Studio Beta Personal Library ID."
                        )
                        PublishStepItem(
                            step = "3",
                            title = "Build Signed Release .AAB in Android Studio",
                            detail = "Open your exported project in Android Studio on a computer, click Build > Generate Signed App Bundle / APK > Android App Bundle (.aab), create a new Upload Keystore (.jks), and generate 'app-release.aab'."
                        )
                        PublishStepItem(
                            step = "4",
                            title = "Click 'Create app' in Google Play Console",
                            detail = "Go to play.google.com/console > All apps and click the blue 'Create app' button. Enter 'Bars R Us', select 'App' and 'Free', and click 'Create app'."
                        )
                        PublishStepItem(
                            step = "5",
                            title = "Upload .AAB & Roll Out to Public Production",
                            detail = "Complete Policy > App content (18+/21+ Target Audience & IARC rating), then upload your 'app-release.aab' under Testing / Production. (Note: Personal developer accounts created after Nov 2023 require 12 testers opted-in for 14 days in Closed Testing before unlocking Public Production.)"
                        )
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Got It", fontWeight = FontWeight.Bold)
            }
        }
    )
}

@Composable
private fun CopyableFieldRow(
    label: String,
    value: String,
    onCopy: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.Black.copy(alpha = 0.45f))
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = label,
                color = TextSecondary,
                fontSize = 9.sp
            )
            Text(
                text = value,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.5.sp
            )
        }
        Spacer(modifier = Modifier.width(6.dp))
        OutlinedButton(
            onClick = onCopy,
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
            modifier = Modifier.height(26.dp),
            border = BorderStroke(1.dp, AmberGold)
        ) {
            Text(
                text = "Copy",
                color = AmberGold,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun PublishStepItem(step: String, title: String, detail: String) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        Surface(
            shape = CircleShape,
            color = AmberGold,
            modifier = Modifier.size(20.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = step,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = ObsidianBg
                )
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 11.sp
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = detail,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = TextSecondary,
                    fontSize = 9.5.sp,
                    lineHeight = 13.sp
                )
            )
        }
    }
}
