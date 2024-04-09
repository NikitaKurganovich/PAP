package dev.babananick.pap.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.babananick.pap.icons.composable.ModuleIcon
import dev.babananick.pap.modules.TestModule
import dev.babananick.pap.theme.PAPTypo

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
            .height(48.dp)
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
                    .padding(start = 15.dp),
                text = module.test_module!!,
                style = PAPTypo.moduleNameTextStyle
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