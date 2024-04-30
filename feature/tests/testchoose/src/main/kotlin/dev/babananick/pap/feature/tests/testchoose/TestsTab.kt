package dev.babananick.pap.feature.tests.testchoose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import cafe.adriel.voyager.transitions.SlideTransition
import dev.babananick.pap.ui.theme.R as theme

data class TestsTab(
    @Transient
    val onNavigator : (isRoot : Boolean) -> Unit,
): Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.tests_tab_text)
            val icon = painterResource(theme.drawable.desk)
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