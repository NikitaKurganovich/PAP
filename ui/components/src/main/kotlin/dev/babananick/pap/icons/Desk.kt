package dev.babananick.pap.icons

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _Desk: ImageVector? = null

val Desk: ImageVector
    get() {
        if (_Desk != null) {
            return _Desk!!
        }
        _Desk = ImageVector.Builder(
            name = "DeskAltFill",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF31674D)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(13.3333f, 3.66669f)
                curveTo(11.6765f, 3.6667f, 10.3333f, 5.0098f, 10.3333f, 6.6667f)
                horizontalLineTo(9.99998f)
                curveTo(8.1274f, 6.6667f, 7.1911f, 6.6667f, 6.5185f, 7.1161f)
                curveTo(6.2273f, 7.3107f, 5.9773f, 7.5607f, 5.7827f, 7.8518f)
                curveTo(5.3333f, 8.5244f, 5.3333f, 9.4607f, 5.3333f, 11.3334f)
                verticalLineTo(24f)
                curveTo(5.3333f, 26.5142f, 5.3333f, 27.7713f, 6.1144f, 28.5523f)
                curveTo(6.8954f, 29.3334f, 8.1525f, 29.3334f, 10.6666f, 29.3334f)
                horizontalLineTo(21.3333f)
                curveTo(23.8475f, 29.3334f, 25.1045f, 29.3334f, 25.8856f, 28.5523f)
                curveTo(26.6666f, 27.7713f, 26.6666f, 26.5142f, 26.6666f, 24f)
                verticalLineTo(11.3334f)
                curveTo(26.6666f, 9.4607f, 26.6666f, 8.5244f, 26.2172f, 7.8518f)
                curveTo(26.0227f, 7.5607f, 25.7727f, 7.3107f, 25.4815f, 7.1161f)
                curveTo(24.8089f, 6.6667f, 23.8726f, 6.6667f, 22f, 6.6667f)
                horizontalLineTo(21.6666f)
                curveTo(21.6666f, 5.0098f, 20.3235f, 3.6667f, 18.6666f, 3.6667f)
                horizontalLineTo(13.3333f)
                close()
                moveTo(13.6666f, 6.66669f)
                curveTo(13.6666f, 6.1144f, 14.1144f, 5.6667f, 14.6666f, 5.6667f)
                horizontalLineTo(17.3333f)
                curveTo(17.8856f, 5.6667f, 18.3333f, 6.1144f, 18.3333f, 6.6667f)
                curveTo(18.3333f, 7.219f, 17.8856f, 7.6667f, 17.3333f, 7.6667f)
                horizontalLineTo(14.6666f)
                curveTo(14.1144f, 7.6667f, 13.6666f, 7.219f, 13.6666f, 6.6667f)
                close()
                moveTo(12f, 15f)
                curveTo(11.4477f, 15f, 11f, 15.4477f, 11f, 16f)
                curveTo(11f, 16.5523f, 11.4477f, 17f, 12f, 17f)
                horizontalLineTo(20f)
                curveTo(20.5523f, 17f, 21f, 16.5523f, 21f, 16f)
                curveTo(21f, 15.4477f, 20.5523f, 15f, 20f, 15f)
                horizontalLineTo(12f)
                close()
                moveTo(12f, 20.3334f)
                curveTo(11.4477f, 20.3334f, 11f, 20.7811f, 11f, 21.3334f)
                curveTo(11f, 21.8856f, 11.4477f, 22.3334f, 12f, 22.3334f)
                horizontalLineTo(17.3333f)
                curveTo(17.8856f, 22.3334f, 18.3333f, 21.8856f, 18.3333f, 21.3334f)
                curveTo(18.3333f, 20.7811f, 17.8856f, 20.3334f, 17.3333f, 20.3334f)
                horizontalLineTo(12f)
                close()
            }
        }.build()
        return _Desk!!
    }

