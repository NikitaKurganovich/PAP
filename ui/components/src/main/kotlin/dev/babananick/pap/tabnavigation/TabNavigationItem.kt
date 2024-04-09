package dev.babananick.pap.tabnavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab,
) {
    val tabNavigator = LocalTabNavigator.current
    NavigationBarItem(
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        selected = tabNavigator.current.key == tab.key,
        onClick = { tabNavigator.current = tab },
        icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
    )
}