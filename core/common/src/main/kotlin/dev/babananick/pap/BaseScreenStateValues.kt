package dev.babananick.pap

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun BaseScreenStateValues(
    state: ScreenStates,
) = when (state) {
    is ScreenStates.Error -> {
        val error = state.error
        Text("Error: ${error.message}")
    }

    is ScreenStates.Loading -> {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }

    is ScreenStates.Empty -> {
        Text("Пусто")
    }
}
