package dev.babananick.pap.ui.components.radiobuttongroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.theme.onSecondaryContainerDark
import dev.babananick.pap.ui.theme.onSecondaryContainerLight
import dev.babananick.pap.ui.theme.onSecondaryDark
import dev.babananick.pap.ui.theme.secondaryDark

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
    val textColor by rememberTextColor(checked)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .background(backgroundColor, MaterialTheme.shapes.large)
            .clip(MaterialTheme.shapes.large)
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
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.radio_padding),
                start = dimensionResource(R.dimen.radio_padding),
                bottom = dimensionResource(R.dimen.radio_padding),
                end = dimensionResource(R.dimen.empty_padding)
            ),
            checked = checked
        )
        Text(
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.answer_radio_text_vertical_padding),
                    horizontal = dimensionResource(R.dimen.answer_radio_text_horizontal_padding)
                ),
            text = variantText,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium
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
                secondaryDark
            } else {
                onSecondaryContainerDark
            }
        }
    }
    return animateColorAsState(
        targetValue = color,
        label = stringResource(R.string.background_color_label)
    )
}

@Composable
private fun rememberTextColor(
    checked: () -> Boolean,
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                onSecondaryContainerLight
            } else {
                onSecondaryDark
            }
        }
    }
    return animateColorAsState(
        targetValue = color,
        label = stringResource(R.string.text_color_label)
    )
}