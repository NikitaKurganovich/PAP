package dev.babananick.pap.ui.components.icons

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp



private var _User: ImageVector? = null

val User: ImageVector
    get() {
        if (_User != null) {
            return _User!!
        }
        _User = ImageVector.Builder(
            name = "UserFill",
            defaultWidth = 33.dp,
            defaultHeight = 33.dp,
            viewportWidth = 33f,
            viewportHeight = 33f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFBBDACB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(26.73f, 26.3159f)
                curveTo(27.3196f, 26.1931f, 27.6706f, 25.5774f, 27.3957f, 25.0415f)
                curveTo(26.5986f, 23.4876f, 25.2754f, 22.1221f, 23.5551f, 21.0954f)
                curveTo(21.4594f, 19.8446f, 18.8916f, 19.1667f, 16.25f, 19.1667f)
                curveTo(13.6084f, 19.1667f, 11.0406f, 19.8446f, 8.9449f, 21.0954f)
                curveTo(7.2246f, 22.122f, 5.9013f, 23.4875f, 5.1043f, 25.0415f)
                curveTo(4.8294f, 25.5774f, 5.1804f, 26.1931f, 5.77f, 26.3159f)
                lineTo(8.08912f, 26.7993f)
                curveTo(13.4719f, 27.921f, 19.0281f, 27.921f, 24.4108f, 26.7993f)
                lineTo(26.73f, 26.3159f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFBBDACB)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(22.91667f, 11.1667f)
                arcTo(6.66667f, 6.66667f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16.25f, 17.833370000000002f)
                arcTo(6.66667f, 6.66667f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.58333f, 11.1667f)
                arcTo(6.66667f, 6.66667f, 0f, isMoreThanHalf = false, isPositiveArc = true, 22.91667f, 11.1667f)
                close()
            }
        }.build()
        return _User!!
    }

