package dev.babananick.pap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.babananick.pap.icons.User

object ProfileTab: Tab {
    private fun readResolve(): Any = ProfileTab
    override val options: TabOptions
        @Composable
        get() {
            val title = "Ваш профиль"
            val icon = rememberVectorPainter(User)
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
        Navigator(ProfileScreen())
    }
}