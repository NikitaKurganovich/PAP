package dev.babananick.pap.ui.preview.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.radiobuttongroup.AnswerRadioButton
import dev.babananick.pap.ui.components.radiobuttongroup.Radio

@Preview
@Composable
fun RadioPreview() {
    val mod = Modifier.padding(5.dp)
    Column {
        Radio(
            modifier = mod,
            checked = { true }
        )
        Radio(
            modifier = mod
        )
    }

}

@Preview
@Composable
fun RadioRowPreview() {
    val mod = Modifier.padding(5.dp)
    var currentVariant by remember {
        mutableStateOf("Test")
    }
    Column {
        AnswerRadioButton(
            modifier = mod,
            variantText = "Test",
            onVariantChange = {
                currentVariant = it
            },
            currentlySelected = { currentVariant }
        )
        AnswerRadioButton(
            modifier = mod,
            variantText = "Test\nTest\nTest",
            onVariantChange = {
                currentVariant = it
            },
            currentlySelected = { currentVariant }
        )
        AnswerRadioButton(
            modifier = mod,
            variantText = "Test\nTest",
            onVariantChange = {
                currentVariant = it
            },
            currentlySelected = { currentVariant }
        )
    }

}