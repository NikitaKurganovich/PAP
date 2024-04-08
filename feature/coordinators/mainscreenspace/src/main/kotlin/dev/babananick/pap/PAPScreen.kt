package dev.babananick.pap

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import coil.ImageLoader
import dev.babananick.pap.ui.theme.Green40
import javax.inject.Inject

class PAPScreen @Inject constructor(
    private val imageLoader: ImageLoader,
) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        TabNavigator(HomeTab(imageLoader)) {
            Scaffold(
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
                    NavigationBar(
                        contentColor = Green40
                    ) {
                        TabNavigationItem(HomeTab(imageLoader))
                        TabNavigationItem(TestsTab(imageLoader))
                        TabNavigationItem(ProfileTab(imageLoader))
                    }
                }
            )
        }
    }
    @Composable
    private fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary),
            selected = tabNavigator.current.key == tab.key,
            onClick = { tabNavigator.current = tab },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }

}

