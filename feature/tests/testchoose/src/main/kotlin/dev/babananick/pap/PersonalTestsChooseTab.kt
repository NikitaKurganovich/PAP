package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.ImageLoader
import compose.icons.TablerIcons
import compose.icons.tablericons.ClipboardList
import javax.inject.Inject

data class TestsTab @Inject constructor(
    private val imageLoader: ImageLoader,
): Tab {
    private fun readResolve(): Any = TestsTab(imageLoader)
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
        Navigator(TestChooseScreen(imageLoader))
    }
}