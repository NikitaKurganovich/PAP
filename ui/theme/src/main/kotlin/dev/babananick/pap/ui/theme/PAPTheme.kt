package dev.babananick.pap.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.dimensionResource
import androidx.core.view.WindowCompat

@Composable
fun PAPTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val shapes = Shapes(
        extraLarge = RoundedCornerShape(dimensionResource(R.dimen.extra_large_rounding)),
        large = RoundedCornerShape(dimensionResource(R.dimen.large_rounding)),
        medium = RoundedCornerShape(dimensionResource(R.dimen.medium_rounding)),
        small = RoundedCornerShape(dimensionResource(R.dimen.small_rounding)),
        extraSmall = RoundedCornerShape(dimensionResource(R.dimen.extra_small_rounding))
    )

    MaterialTheme(
        colorScheme = colorScheme,
        shapes = shapes,
        typography = PAPTypography,
        content = content
    )
}

