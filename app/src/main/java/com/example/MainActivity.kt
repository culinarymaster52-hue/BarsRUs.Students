package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.BarCraftProPaywallDialog
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.util.configureAppCheck
import com.example.viewmodel.AiAssistantViewModel
import com.example.viewmodel.AppScreen
import com.example.viewmodel.BarCraftViewModel

class MainActivity : ComponentActivity() {

    private val barViewModel: BarCraftViewModel by viewModels()
    private val aiViewModel: AiAssistantViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // STEP 1: Configure App Check with launch intent before any UI or Firebase operations
        configureAppCheck(this, intent)

        enableEdgeToEdge()

        setContent {
            BarCraftTheme {
                MainAppScreen(
                    barViewModel = barViewModel,
                    aiViewModel = aiViewModel
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(
    barViewModel: BarCraftViewModel,
    aiViewModel: AiAssistantViewModel
) {
    val currentScreen by barViewModel.currentScreen.collectAsState()
    val isPro by barViewModel.isProLicensed.collectAsState()
    val showPaywall by barViewModel.showPaywallModal.collectAsState()

    // Handle system back navigation to return to Virtual Bar if in sub-screen
    BackHandler(enabled = currentScreen != AppScreen.VIRTUAL_BAR) {
        barViewModel.navigateTo(AppScreen.VIRTUAL_BAR)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "Bars R Us",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.ExtraBold,
                                color = AmberGold
                            )
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Surface(
                            shape = RoundedCornerShape(4.dp),
                            color = SuccessGreen
                        ) {
                            Text(
                                text = "100% FREE",
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = ObsidianBg,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 8.sp
                                )
                            )
                        }
                    }
                },
                actions = {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = SuccessGreen.copy(alpha = 0.2f),
                        border = ButtonDefaults.outlinedButtonBorder.copy(
                            brush = androidx.compose.ui.graphics.SolidColor(SuccessGreen)
                        ),
                        modifier = Modifier
                            .clickable { barViewModel.openPaywall() }
                            .padding(end = 12.dp)
                            .testTag("pro_pass_header_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Text("🚀", fontSize = 12.sp)
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "GOOGLE PLAY GUIDE",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SuccessGreen,
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ObsidianSurface,
                    titleContentColor = AmberGold
                )
            )
        },
        floatingActionButton = {
            if (currentScreen != AppScreen.AI_ASSISTANT) {
                FloatingActionButton(
                    onClick = { barViewModel.navigateTo(AppScreen.AI_ASSISTANT) },
                    containerColor = AmberGold,
                    contentColor = ObsidianBg,
                    shape = CircleShape,
                    modifier = Modifier
                        .size(56.dp)
                        .testTag("ai_mentor_fab")
                ) {
                    Icon(Icons.Default.SmartToy, contentDescription = "AI Mixologist Mentor & Suggestions")
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = ObsidianSurface,
                contentColor = AmberGold,
                windowInsets = WindowInsets.navigationBars,
                modifier = Modifier.testTag("main_navigation_bar")
            ) {
                NavigationBarItem(
                    selected = currentScreen == AppScreen.VIRTUAL_BAR,
                    onClick = { barViewModel.navigateTo(AppScreen.VIRTUAL_BAR) },
                    icon = { Icon(Icons.Default.LocalBar, contentDescription = "Virtual Bar") },
                    label = { Text("Bar Lab", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ObsidianBg,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    )
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.ACADEMY_QUIZ,
                    onClick = { barViewModel.navigateTo(AppScreen.ACADEMY_QUIZ) },
                    icon = { Icon(Icons.Default.School, contentDescription = "Quiz Academy") },
                    label = { Text("Academy", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ObsidianBg,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    )
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.SPEED_RUSH,
                    onClick = { barViewModel.navigateTo(AppScreen.SPEED_RUSH) },
                    icon = { Icon(Icons.Default.Speed, contentDescription = "Speed Rush") },
                    label = { Text("Speed", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ObsidianBg,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    )
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.COCKTAILS_LIBRARY,
                    onClick = { barViewModel.navigateTo(AppScreen.COCKTAILS_LIBRARY) },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Cocktails") },
                    label = { Text("Recipes", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ObsidianBg,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    )
                )

                NavigationBarItem(
                    selected = currentScreen == AppScreen.LAWS_SAFETY,
                    onClick = { barViewModel.navigateTo(AppScreen.LAWS_SAFETY) },
                    icon = { Icon(Icons.Default.Gavel, contentDescription = "Laws & Safety") },
                    label = { Text("Laws/RBS", fontSize = 10.sp) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = ObsidianBg,
                        selectedTextColor = AmberGold,
                        indicatorColor = AmberGold,
                        unselectedIconColor = TextSecondary,
                        unselectedTextColor = TextSecondary
                    )
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentScreen) {
                AppScreen.VIRTUAL_BAR -> VirtualBarScreen(viewModel = barViewModel)
                AppScreen.ACADEMY_QUIZ -> AcademyQuizScreen(viewModel = barViewModel)
                AppScreen.SPEED_RUSH -> SpeedRushScreen(viewModel = barViewModel)
                AppScreen.COCKTAILS_LIBRARY -> CocktailLibraryScreen(viewModel = barViewModel)
                AppScreen.LAWS_SAFETY -> LawsAndSafetyScreen(viewModel = barViewModel)
                AppScreen.AI_ASSISTANT -> AiAssistantScreen(viewModel = aiViewModel)
            }

            if (showPaywall) {
                BarCraftProPaywallDialog(
                    isPro = isPro,
                    onDismiss = { barViewModel.closePaywall() },
                    onUnlockPro = { barViewModel.unlockProLicense() }
                )
            }
        }
    }
}
