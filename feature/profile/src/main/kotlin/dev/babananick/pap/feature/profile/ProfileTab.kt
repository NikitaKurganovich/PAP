package dev.babananick.pap.feature.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.babananick.pap.ui.theme.R as theme

object ProfileTab : Tab {
    private fun readResolve(): Any = ProfileTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.profile_tab_text)
            val icon = painterResource(theme.drawable.profile)
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