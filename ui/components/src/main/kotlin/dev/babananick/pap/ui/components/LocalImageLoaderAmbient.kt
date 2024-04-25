package dev.babananick.pap.ui.components

import androidx.compose.runtime.staticCompositionLocalOf
import coil.ImageLoader


val LocalImageLoaderAmbient = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}