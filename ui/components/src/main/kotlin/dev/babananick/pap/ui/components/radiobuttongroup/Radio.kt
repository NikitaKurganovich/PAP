package dev.babananick.pap.ui.components.radiobuttongroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.components.icons.Checked
import dev.babananick.pap.ui.theme.onSecondaryContainerLight
import dev.babananick.pap.ui.theme.secondaryContainerDark

@Composable
fun Radio(
    modifier: Modifier = Modifier,
    checked: () -> Boolean = remember { { false } }
) {
    val iconTint by rememberIconTint(checked)
    val borderColor by rememberBorderColor(checked)
    Icon(
        modifier = modifier
            .size(dimensionResource(R.dimen.radio_size))
            .background(Color.Transparent, MaterialTheme.shapes.extraLarge)
            .border(
                width = dimensionResource(R.dimen.radio_border_stroke),
                color = borderColor,
                shape = MaterialTheme.shapes.extraLarge
            )
            .clip(MaterialTheme.shapes.extraLarge),
        imageVector = Checked,
        contentDescription = null,
        tint = iconTint
    )
}

@Composable
private fun rememberBorderColor(
    checked: () -> Boolean
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                onSecondaryContainerLight
            } else {
                secondaryContainerDark
            }
        }
    }
    return animateColorAsState(
        targetValue = color,
        label = stringResource(R.string.border_color_label)
    )
}


@Composable
private fun rememberIconTint(
    checked: () -> Boolean
): State<Color> {
    val color by remember(checked) {
        derivedStateOf {
            if (checked()) {
                onSecondaryContainerLight
            } else {
                Color.Transparent
            }
        }
    }
    return animateColorAsState(
        targetValue = color,
        label = stringResource(R.string.icon_tint_label)
    )
}
