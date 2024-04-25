package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.babananick.pap.core.model.modules.TestModule
import dev.babananick.pap.ui.components.icons.composable.ModuleIcon

@Composable
fun TestModuleButton(
    modifier: Modifier = Modifier,
    module: TestModule,
    onClick: () -> Unit,
    expanded: Boolean,
) {
    val shape = RoundedCornerShape(10.dp)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(shape)
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
                style = dev.babananick.pap.ui.theme.PAPTypo.moduleNameTextStyle
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