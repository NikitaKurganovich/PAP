package dev.babananick.pap.ui.components.util

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable

object Shapes {
    val radioShape: RoundedCornerShape
    @Composable
    get() {
        return RoundedCornerShape(50)
    }
}