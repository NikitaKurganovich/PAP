package dev.babananick.pap.radiobuttongroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AnswerRadioButton(
    modifier: Modifier = Modifier,
    variantText: String,
    onVariantChange: (String) -> Unit,
    currentlySelected: () -> String?,
) {
    val checked: () -> Boolean by remember(variantText, currentlySelected) {
        derivedStateOf { { variantText == currentlySelected() } }
    }
    val selected: Boolean = remember(currentlySelected, variantText) {
        currentlySelected() == variantText
    }
    val backgroundColor by rememberBackgroundColor(checked)
    Row(
        modifier = modifier
            .background(backgroundColor)
            .selectable(
                selected = selected,
                onClick = remember(onVariantChange, variantText) {
                    {
                        onVariantChange(variantText)
                    }

                }
            )
    ) {
        Radio(
            modifier = Modifier.padding((14.5).dp),
            checked = checked
        )
        Text(variantText)
    }
}

@Composable
private fun rememberBackgroundColor(
    checked: () -> Boolean,
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                Color(0xFF31674D)
            } else {
                Color(0xFFBBDACB)
            }
        }
    }
    return animateColorAsState(color)
}