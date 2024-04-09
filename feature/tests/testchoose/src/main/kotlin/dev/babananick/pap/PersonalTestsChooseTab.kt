package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import compose.icons.TablerIcons
import compose.icons.tablericons.ClipboardList

class TestsTab(
    @Transient
    val onNavigator : (isRoot : Boolean) -> Unit,
): Tab {
    private fun readResolve(): Any = TestsTab(onNavigator)
    override val options: TabOptions
        @Composable
        get() {
            val title = "Персональные тесты"
            val icon = rememberVectorPainter(TablerIcons.ClipboardList)
            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(TestChooseScreen()){ navigator ->
            LaunchedEffect(navigator.lastItem){
                onNavigator(navigator.lastItem is TestChooseScreen)
            }
            SlideTransition(navigator = navigator)
        }
    }
}