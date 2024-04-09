package dev.babananick.pap

import androidx.compose.runtime.staticCompositionLocalOf
import coil.ImageLoader


val LocalImageLoaderAmbient = staticCompositionLocalOf<ImageLoader> {
    error("No ImageLoader provided")
}