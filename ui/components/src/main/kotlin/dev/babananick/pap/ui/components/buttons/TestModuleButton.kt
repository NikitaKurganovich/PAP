package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.components.icons.composable.ModuleIcon

@Composable
fun TestModuleButton(
    modifier: Modifier = Modifier,
    module: TestModule,
    onClick: () -> Unit,
    expanded: Boolean,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.test_module_height))
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ModuleIcon(
                imageUrl = module.resources!!.icon!!
            )
            Text(
                modifier = Modifier
                    .padding(start = dimensionResource(R.dimen.test_module_text_start_padding)),
                text = module.test_module!!,
                style = MaterialTheme.typography.labelLarge
                    .copy(fontWeight = FontWeight.W600),
                color = MaterialTheme.colorScheme.primary
            )
        }
        Icon(
            imageVector = if (expanded)
                Icons.Filled.KeyboardArrowUp
            else Icons.Filled.KeyboardArrowDown,
            contentDescription = null
        )
    }
}