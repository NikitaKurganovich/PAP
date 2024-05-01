package dev.babananick.pap.ui.components.icons.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberAsyncImagePainter
import dev.babananick.pap.ui.components.LocalImageLoaderAmbient
import dev.babananick.pap.ui.components.R

@Composable
fun ModuleIcon(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        imageLoader = LocalImageLoaderAmbient.current,
    )
    Box(
        modifier = modifier
            .size(dimensionResource(R.dimen.module_icon_container_size))
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(dimensionResource(R.dimen.module_icon_size)),
            painter = painter,
            contentDescription = null,
            tint = Color.White
        )
    }
}


