package dev.babananick.pap.icons.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter

@Composable
fun ModuleIcon(
    modifier: Modifier = Modifier,
    imageLoader: ImageLoader,
    imageUrl: String,
) {
    val painter = rememberAsyncImagePainter(
        imageUrl,
        imageLoader = imageLoader,
    )
    val shape = RoundedCornerShape(10.dp)
    Box(
        modifier = modifier
            .size(48.dp)
            .background(Color(0xFF31674D),shape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painter,
            contentDescription = null,
            tint = Color.White
        )
    }
}


