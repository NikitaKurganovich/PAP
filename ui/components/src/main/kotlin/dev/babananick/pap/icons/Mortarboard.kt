package dev.babananick.pap.icons

import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _Mortarboard: ImageVector? = null

val Mortarboard: ImageVector
    get() {
        if (_Mortarboard != null) {
            return _Mortarboard!!
        }
        _Mortarboard = ImageVector.Builder(
            name = "MortarboardFill",
            defaultWidth = 41.dp,
            defaultHeight = 37.dp,
            viewportWidth = 41f,
            viewportHeight = 37f
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
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(11.1118f, 18.6809f)
                curveTo(10.9456f, 18.5978f, 10.75f, 18.7187f, 10.75f, 18.9045f)
                verticalLineTo(25.5f)
                curveTo(10.75f, 25.5f, 10.75f, 31.5f, 20.75f, 31.5f)
                curveTo(30.75f, 31.5f, 30.75f, 25.5f, 30.75f, 25.5f)
                verticalLineTo(18.9045f)
                curveTo(30.75f, 18.7187f, 30.5544f, 18.5978f, 30.3882f, 18.6809f)
                lineTo(21.1972f, 23.2764f)
                curveTo(20.9157f, 23.4171f, 20.5843f, 23.4171f, 20.3028f, 23.2764f)
                lineTo(11.1118f, 18.6809f)
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
                moveTo(5.64443f, 13.0528f)
                lineTo(20.5264f, 5.6118f)
                curveTo(20.6672f, 5.5414f, 20.8328f, 5.5414f, 20.9736f, 5.6118f)
                lineTo(35.8556f, 13.0528f)
                curveTo(36.2241f, 13.237f, 36.2241f, 13.763f, 35.8556f, 13.9472f)
                lineTo(20.9736f, 21.3882f)
                curveTo(20.8328f, 21.4586f, 20.6672f, 21.4586f, 20.5264f, 21.3882f)
                lineTo(5.64443f, 13.9472f)
                curveTo(5.2759f, 13.763f, 5.2759f, 13.237f, 5.6444f, 13.0528f)
                close()
            }
        }.build()
        return _Mortarboard!!
    }

