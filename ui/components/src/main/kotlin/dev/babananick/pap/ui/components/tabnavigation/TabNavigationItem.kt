package dev.babananick.pap.ui.components.tabnavigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab

@Composable
fun RowScope.TabNavigationItem(
    tab: Tab,
) {
    val tabNavigator = LocalTabNavigator.current
    val selected by remember(tabNavigator.current) {
        derivedStateOf {
            tabNavigator.current.key == tab.key
        }
    }
    val tabTextStyle by rememberTextStyle(selected)
    NavigationBarItem(
        selected = selected,
        onClick = { tabNavigator.current = tab },
        icon = {
            Icon(
                painter = tab.options.icon!!,
                contentDescription = tab.options.title,
            )
        },
        label = {
            Text(
                text = tab.options.title,
                style = tabTextStyle
            )
        },
        colors = NavigationBarItemColors(
            selectedIconColor = MaterialTheme.colorScheme.onSurface,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            selectedIndicatorColor = MaterialTheme.colorScheme.secondaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurface,
            unselectedTextColor = MaterialTheme.colorScheme.onSurface,
            disabledIconColor = MaterialTheme.colorScheme.onSurface,
            disabledTextColor = MaterialTheme.colorScheme.onSurface,
        )
    )
}

@Composable
fun rememberTextStyle(
    selected: Boolean
): State<TextStyle> {
    val selectedTabTextStyle = selectedTabTextStyle
    val tabTextStyle = tabTextStyle
    val textStyle by remember(selected) {
        derivedStateOf {
            if (selected) {
                selectedTabTextStyle
            } else {
                tabTextStyle
            }
        }
    }
    return rememberUpdatedState(newValue = textStyle)
}

private val selectedTabTextStyle: TextStyle
    @Composable
    get() {
        val selectedTabTextStyle = MaterialTheme.typography.labelMedium.copy(
            fontWeight = FontWeight.W600
        )
        return remember {
            selectedTabTextStyle
        }
    }
private val tabTextStyle: TextStyle
    @Composable
    get() {
        val tabTextStyle = MaterialTheme.typography.labelMedium
        return remember {
            tabTextStyle
        }
    }