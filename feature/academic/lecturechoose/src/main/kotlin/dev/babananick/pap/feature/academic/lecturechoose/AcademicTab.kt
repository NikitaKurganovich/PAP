package dev.babananick.pap.feature.academic.lecturechoose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.babananick.pap.ui.theme.R as theme

object AcademicTab : Tab {
    private fun readResolve(): Any = AcademicTab
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.academic_tab_text)
            val icon = painterResource(theme.drawable.mortarboard)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        Navigator(AcademicScreen())
    }
}