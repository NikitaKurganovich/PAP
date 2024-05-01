package dev.babananick.pap.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import dev.babananick.pap.core.model.modules.LectureInModule
import dev.babananick.pap.core.model.modules.TestInModule
import dev.babananick.pap.ui.components.R

@Composable
fun SubmoduleButton(
    modifier: Modifier = Modifier,
    module: TestInModule,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.test_in_module_horizontal_padding))
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                onClick = { onClick(module.id!!) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.test_in_module_text_start_padding))
                .padding(vertical = dimensionResource(R.dimen.test_module_text_vertical_padding))
                .weight(1f)
        ) {
            Text(
                text = module.name!!,
                style = MaterialTheme.typography.labelMedium
                    .copy(lineBreak = LineBreak.Paragraph),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = pluralStringResource(
                    R.plurals.question_quantity_text,
                    module.question_quantity!!.toInt(),
                    module.question_quantity!!.toInt(),
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.test_in_module_icon_end_padding))
                .padding(vertical = dimensionResource(R.dimen.test_in_module_icon_vertical_padding))
                .size(dimensionResource(R.dimen.test_in_module_icon_size)),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun SubmoduleButton(
    modifier: Modifier = Modifier,
    module: LectureInModule,
    onClick: (String) -> Unit,
) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(R.dimen.test_in_module_horizontal_padding))
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                onClick = { onClick(module.name!!) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .padding(start = dimensionResource(R.dimen.test_in_module_text_start_padding))
                .padding(vertical = dimensionResource(R.dimen.test_module_text_vertical_padding))
                .weight(1f)
        ) {
            Text(
                text = module.name!!,
                style = MaterialTheme.typography.labelMedium
                    .copy(lineBreak = LineBreak.Paragraph),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                text = pluralStringResource(
                    R.plurals.question_quantity_text,
                    module.question_quantity!!.toInt(),
                    module.question_quantity!!.toInt(),
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
        Icon(
            modifier = Modifier
                .padding(end = dimensionResource(R.dimen.test_in_module_icon_end_padding))
                .padding(vertical = dimensionResource(R.dimen.test_in_module_icon_vertical_padding))
                .size(dimensionResource(R.dimen.test_in_module_icon_size)),
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}