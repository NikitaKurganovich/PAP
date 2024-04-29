package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.babananick.pap.core.model.modules.TestInModule

@Composable
fun TestInModuleButton(
    modifier: Modifier = Modifier,
    module: TestInModule,
    onClick: (String) -> Unit,
) {
    val shape = RoundedCornerShape(15.dp)
    Row(
        modifier = modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(Color(0xFFEEFDEF), shape)
            .clip(shape)
            .clickable(
                onClick = {
                    onClick(module.id!!)
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f)
        ) {
            Text(
                text = module.name!!,
            )
            Text(
                text = "${module.question_quantity!!} вопросов",
            )
        }
        Icon(
            modifier = Modifier
                .size(24.dp)
                .padding(end = 6.dp),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null
        )
    }
}