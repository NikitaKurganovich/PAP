package dev.babananick.pap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.babananick.pap.tabnavigation.TabNavigationItem
import dev.babananick.pap.ui.theme.Green40

class PAPScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var isVisible by remember { mutableStateOf(true) }
        val testTab = remember {
            TestsTab(
                onNavigator = { isVisible = it }
            )
        }
        TabNavigator(HomeTab) {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    TopAppBar(
                        title = {
                            DefaultText(text = it.current.options.title)
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(MaterialTheme.colorScheme.primary)
                    )
                },
                content = {
                    Column(
                        modifier = Modifier
                            .padding(it)
                    ) {
                        CurrentTab()
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
                        BottomNavigation {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(testTab)
                            TabNavigationItem(ProfileTab)
                        }
                    }
                }
            )
        }
    }
}

