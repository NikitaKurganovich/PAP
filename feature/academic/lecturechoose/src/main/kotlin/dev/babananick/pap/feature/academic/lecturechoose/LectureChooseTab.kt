package dev.babananick.pap.feature.academic.lecturechoose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import dev.babananick.pap.ui.components.icons.Mortarboard

object AcademicTab : Tab {
    private fun readResolve(): Any = AcademicTab
    override val options: TabOptions
        @Composable
        get() {
            val title = "Лекции"
            val icon = rememberVectorPainter(Mortarboard)
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