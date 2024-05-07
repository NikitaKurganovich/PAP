package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import dev.babananick.pap.ui.components.text.ButtonText
import dev.babananick.pap.ui.components.R

@Composable
fun NavigateBorder(
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
    text: String,
) {
    Box(
        modifier = modifier
            .size(height = 45.dp, width = 150.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable(
                enabled = isEnabled,
                onClick = onClick
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge
            )
            .border(
                width = dimensionResource(R.dimen.radio_border_stroke),
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.extraLarge
            ),
        contentAlignment = Alignment.Center
        ) {
        ButtonText(
            text = text,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}