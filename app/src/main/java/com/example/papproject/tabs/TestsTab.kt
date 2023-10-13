package com.example.papproject.tabs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.example.papproject.screens.TestScreen
import compose.icons.TablerIcons
import compose.icons.tablericons.ClipboardList

object TestsTab: Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = "Tests"
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
        Navigator(TestScreen())
    }
}