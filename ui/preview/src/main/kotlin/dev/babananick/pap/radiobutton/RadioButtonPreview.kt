package dev.babananick.pap.radiobutton

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.babananick.pap.radiobuttongroup.AnswerRadioButton
import dev.babananick.pap.radiobuttongroup.Radio

@Preview
@Composable
fun RadioPreview(){
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
fun RadioRowPreview(){
    val mod = Modifier.padding(5.dp)
    Column {
        AnswerRadioButton(
            modifier = mod,
            variantText = "Test",
            onVariantChange = {},
            currentlySelected = {"Test"}
        )
        AnswerRadioButton(
            modifier = mod,
            variantText = "Test\nTest\nTest",
            onVariantChange = {},
            currentlySelected = {"Non"}
        )
    }

}