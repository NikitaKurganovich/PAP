package dev.babananick.pap.ui.components.radiobuttongroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.text.VariantText

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
    val shape by remember { mutableStateOf(RoundedCornerShape(15.dp)) }
    val textColor by rememberTextColor(checked)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .height(55.dp)
            .background(backgroundColor, shape)
            .clip(shape)
            .selectable(
                selected = selected,
                onClick = remember(onVariantChange, variantText) {
                    {
                        onVariantChange(variantText)
                    }
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Radio(
            modifier = Modifier.padding((14.5).dp),
            checked = checked
        )
        VariantText(
            variant = variantText,
            color = textColor
        )
    }
}

@Composable
private fun rememberBackgroundColor(
    checked: () -> Boolean,
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                Color(0xFFBBDACB)
            } else {
                Color(0xFFEEFDEF)
            }
        }
    }
    return animateColorAsState(color)
}

@Composable
private fun rememberTextColor(
    checked: () -> Boolean,
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                Color(0xFF31674D)
            } else {
                Color(0xFF434743)
            }
        }
    }
    return animateColorAsState(color)
}