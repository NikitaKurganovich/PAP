package dev.babananick.pap.ui.components.results

import android.content.res.Resources
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat.getFloat
import dev.babananick.pap.ui.components.R
import dev.babananick.pap.ui.theme.R as papRes

@Composable
fun ResultsPrimary(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .size(
            width = 340.dp,
            height = 394.dp
        ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .rotate(getFloat(Resources.getSystem(), R.dimen.rotate_primary_first))
                .offset(
                    x = dimensionResource(R.dimen.offset_x_primary_first),
                    y = dimensionResource(R.dimen.offset_y_primary_first)
                )
                .background(
                    color = MaterialTheme.colorScheme.inversePrimary,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .clip(MaterialTheme.shapes.extraLarge)
        )

        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .rotate(getFloat(Resources.getSystem(), R.dimen.rotate_primary_second))
                .offset(
                    x = dimensionResource(R.dimen.offset_x_primary_second),
                    y = dimensionResource(R.dimen.offset_y_primary_second)
                )
                .background(
                    color = MaterialTheme.colorScheme.tertiary,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .clip(MaterialTheme.shapes.extraLarge)
        )
        Box(
            modifier = Modifier
                .size(
                    height = 350.dp,
                    width = 280.dp
                )
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.extraLarge
                )
                .clip(MaterialTheme.shapes.extraLarge),
            contentAlignment = Alignment.Center,
        ){
            Box(
                modifier = Modifier
                    .padding(dimensionResource(id = papRes.dimen.screen_content_horizontal_padding)),
                content = content
            )
        }
    }
}

