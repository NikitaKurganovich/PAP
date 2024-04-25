package dev.babananick.pap.ui.preview.button

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.buttons.NavigateBorder
import dev.babananick.pap.ui.components.buttons.NavigateFilled

@Preview
@Composable
fun NavigatePreview(){
    val mod = Modifier.padding(5.dp)
    Column {
        NavigateBorder(
            modifier = mod,
            isEnabled = false,
            onClick = {},
            text = "Test"
        )
        NavigateFilled(
            modifier = mod,
            onClick = {},
            text = "Test"
        )
    }
}