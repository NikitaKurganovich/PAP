package dev.babananick.pap.icons

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

private var _Checked: ImageVector? = null

val Checked: ImageVector
		get() {
			if (_Checked != null) {
				return _Checked!!
			}
_Checked = ImageVector.Builder(
                name = "Subtract",
                defaultWidth = 21.dp,
                defaultHeight = 21.dp,
                viewportWidth = 21f,
                viewportHeight = 21f
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
					moveTo(10.5f, 20.25f)
					curveTo(15.8848f, 20.25f, 20.25f, 15.8848f, 20.25f, 10.5f)
					curveTo(20.25f, 5.1152f, 15.8848f, 0.75f, 10.5f, 0.75f)
					curveTo(5.1152f, 0.75f, 0.75f, 5.1152f, 0.75f, 10.5f)
					curveTo(0.75f, 15.8848f, 5.1152f, 20.25f, 10.5f, 20.25f)
					close()
					moveTo(10.1849f, 14.3902f)
					lineTo(15.6016f, 7.89018f)
					lineTo(14.0651f, 6.60982f)
					lineTo(9.34947f, 12.2686f)
					lineTo(6.87377f, 9.79289f)
					lineTo(5.45956f, 11.2071f)
					lineTo(8.70956f, 14.4571f)
					lineTo(9.48386f, 15.2314f)
					lineTo(10.1849f, 14.3902f)
					close()
}
}.build()
return _Checked!!
		}

