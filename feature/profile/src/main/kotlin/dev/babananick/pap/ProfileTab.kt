package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import coil.ImageLoader
import compose.icons.TablerIcons
import compose.icons.tablericons.User
import javax.inject.Inject

data class ProfileTab @Inject constructor(
    private val imageLoader: ImageLoader,
): Tab {
    private fun readResolve(): Any = ProfileTab(imageLoader)
    override val options: TabOptions
        @Composable
        get() {
            val title = "Ваш профиль"
            val icon = rememberVectorPainter(TablerIcons.User)
            return remember {
                TabOptions(
                    index = 2u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable

    override fun Content() {
        Navigator(ProfileScreen(imageLoader))
    }
}