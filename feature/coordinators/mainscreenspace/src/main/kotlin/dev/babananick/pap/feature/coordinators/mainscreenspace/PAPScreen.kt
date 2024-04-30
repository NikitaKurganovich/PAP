package dev.babananick.pap.feature.coordinators.mainscreenspace

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.babananick.pap.feature.academic.lecturechoose.AcademicTab
import dev.babananick.pap.feature.profile.ProfileTab
import dev.babananick.pap.feature.tests.testchoose.TestsTab
import dev.babananick.pap.ui.components.tabnavigation.TabNavigationItem

class PAPScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var isVisible by rememberSaveable { mutableStateOf(true) }
        val testTab = remember {
            TestsTab(
                onNavigator = { isVisible = it }
            )
        }

        val tabShape = remember {
            RoundedCornerShape(20.dp)
        }
        val headerShape = remember {
            RoundedCornerShape(
                bottomStart = 20.dp,
                bottomEnd = 20.dp
            )
        }
        TabNavigator(AcademicTab) {
            Scaffold(
                topBar = {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically { height ->
                            -height
                        },
                        exit = slideOutVertically { height ->
                            -height
                        }) {
                        TopAppBar(
                            modifier = Modifier
                                .shadow(elevation = 4.dp, shape = headerShape)
                                .clip(headerShape),
                            title = {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = it.current.options.title,
                                    textAlign = TextAlign.Center
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = Color.White
                            )
                        )
                    }
                },
                bottomBar = {
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically { height ->
                            height
                        },
                        exit = slideOutVertically { height ->
                            height
                        }) {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer,
                            modifier = Modifier
                                .padding(5.dp)
                                .shadow(elevation = 4.dp, shape = tabShape)
                                .clip(tabShape)
                        ) {
                            TabNavigationItem(AcademicTab)
                            TabNavigationItem(testTab)
                            TabNavigationItem(ProfileTab)
                        }
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}

