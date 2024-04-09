package dev.babananick.pap

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import dev.babananick.pap.tabnavigation.TabNavigationItem

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

        val tabShape = remember {
            RoundedCornerShape(20.dp)
        }
        TabNavigator(AcademicTab) {
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
                        NavigationBar(
                            containerColor = Color(0xFFFFFFFF),
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
                }
            )
        }
    }
}

