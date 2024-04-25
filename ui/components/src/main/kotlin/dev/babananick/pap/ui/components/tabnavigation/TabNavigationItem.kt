package dev.babananick.pap.ui.components.tabnavigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
    val shape = remember { RoundedCornerShape(10.dp) }
    val backgroundColor by rememberBackgroundColor(selected)
    NavigationBarItem(
        selected = selected,
        onClick = { tabNavigator.current = tab },
        icon = {
            Box(
                modifier = Modifier
                    .size(55.dp)
                    .background(backgroundColor,shape),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = tab.options.icon!!,
                    contentDescription = tab.options.title,
                )
            }

        },
        colors = NavigationBarItemColors(
            selectedIconColor = Color(0xFF31674D),
            selectedTextColor = Color(0xFF31674D),
            selectedIndicatorColor = Color.Transparent,
            unselectedIconColor = Color(0x3CBBDACB),
            unselectedTextColor = Color(0x3CBBDACB),
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent,
        )
    )
}

@Composable
fun rememberBackgroundColor(
    selected: Boolean
): State<Color> {
    val color by remember(selected) {
        derivedStateOf {
            if(selected){
                Color(0x3CBBDACB)
            } else{
                Color.Transparent
            }
        }
    }
    return animateColorAsState(color)
}