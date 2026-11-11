package com.example.ui.screens

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.BarCraftData
import com.example.ui.theme.*
import com.example.util.ShareHelper
import com.example.viewmodel.BarCraftViewModel

@Composable
fun LawsAndSafetyScreen(
    viewModel: BarCraftViewModel,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val lawsState by viewModel.lawsState.collectAsState()
    val law = lawsState.currentLaw
    val scrollState = rememberScrollState()

    // Permission launcher for Location
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val granted = permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)
        if (granted) {
            viewModel.detectLocation(context)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ObsidianBg)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Screen Header
        Text(
            text = "ALCOHOL LAWS & RBS SAFETY",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = AmberGold,
                letterSpacing = 1.sp
            )
        )
        Text(
            text = "Responsible beverage service, location-based statutes, and certifications",
            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
        )

        Spacer(modifier = Modifier.height(14.dp))

        // Location Connection Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.LocationOn, contentDescription = null, tint = AmberGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Current Region Jurisdiction",
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                            )
                            Text(
                                text = lawsState.locationDetectedCity,
                                style = MaterialTheme.typography.bodySmall.copy(color = AmberGoldLight, fontSize = 11.sp)
                            )
                        }
                    }

                    OutlinedButton(
                        onClick = {
                            locationPermissionLauncher.launch(
                                arrayOf(
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION
                                )
                            )
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.testTag("detect_location_button")
                    ) {
                        Icon(Icons.Default.MyLocation, contentDescription = null, modifier = Modifier.size(14.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (lawsState.isLocating) "Locating..." else "Detect", fontSize = 11.sp)
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // State selector chips
                Text("Select Jurisdiction State:", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 10.sp))
                Spacer(modifier = Modifier.height(6.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    BarCraftData.stateLaws.forEach { item ->
                        FilterChip(
                            selected = lawsState.selectedStateCode == item.stateCode,
                            onClick = { viewModel.selectStateLaw(item.stateCode) },
                            label = { Text(item.stateCode, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // State Statutes Detail Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${law.stateName.uppercase()} ALCOHOL SERVICE LAWS",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                )
                Spacer(modifier = Modifier.height(12.dp))

                // Standard Drink Equivalency
                LawSection(
                    title = "Standard Drink Equivalency (14g pure ethanol)",
                    content = law.standardDrinkOunces,
                    icon = Icons.Default.LocalBar
                )

                // Legal Serving Hours
                LawSection(
                    title = "Legal Serving Hours",
                    content = law.servingHours,
                    icon = Icons.Default.Schedule
                )

                // Dram Shop Liability
                LawSection(
                    title = "Dram Shop Liability & Third-Party Law",
                    content = law.dramShopLiability,
                    icon = Icons.Default.Gavel
                )

                // Fake ID Detection & Penalties
                LawSection(
                    title = "Fake ID Checking (FEAR Protocol) & Penalties",
                    content = "${law.fakeIdPenalties}\nProtocol: Feel tactile embossed dates, Examine microprint & holograms, Ask questions regarding birth year/zodiac sign, Refuse service if suspect.",
                    icon = Icons.Default.Badge
                )

                // RBS Certification Status
                LawSection(
                    title = "State RBS Training Status",
                    content = law.rbsDescription,
                    icon = Icons.Default.VerifiedUser
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // ServSafe & Certification Direct Links
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Verified, contentDescription = null, tint = SuccessGreen)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Official Certifications & Course Links",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Obtaining an accredited server certificate protects you from personal liability, meets state statutory mandates, and qualifies you for employment.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_SERVSAFE_ALCOHOL) },
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth().testTag("servsafe_link_button")
                ) {
                    Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Visit Official ServSafe Alcohol Website", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_TIPS_CERTIFICATION) },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.OpenInNew, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Visit TIPS Alcohol Certification Portal", fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Drink Copyright & Patent Resources Card
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Copyright, contentDescription = null, tint = AmberGold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Copyright & Trademarking Custom Cocktails",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Created an original cocktail in Bars R Us? Protect your signature drinks through federal trademark registration and creative copyright documentation.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                )
                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(
                        onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_USPTO_TRADEMARK) },
                        colors = ButtonDefaults.buttonColors(containerColor = CopperAccent, contentColor = ObsidianBg),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("USPTO Trademarks", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                    OutlinedButton(
                        onClick = { ShareHelper.openWebUrl(context, ShareHelper.URL_COPYRIGHT_GOV) },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("US Copyright Office", fontSize = 11.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Social Media Community Share
        Surface(
            shape = RoundedCornerShape(14.dp),
            color = ObsidianSurfaceVariant,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Text(
                    text = "SHARE YOUR PROGRESS & GROW COMMUNITY",
                    style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = AmberGold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Share your cocktail creations, quiz master certificates, and speed rush scores to Instagram, X, TikTok, or WhatsApp to build your bartender portfolio!",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = {
                        ShareHelper.shareLeaderboardScore(
                            context = context,
                            studentName = "Student Mixologist",
                            score = 7640,
                            rankTitle = "Bars R Us Certified Apprentice"
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = AmberGold, contentColor = ObsidianBg),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Share Bars R Us Portfolio to Social Media", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun LawSection(
    title: String,
    content: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = AmberGold, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
            )
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp),
            modifier = Modifier.padding(start = 22.dp)
        )
    }
}
