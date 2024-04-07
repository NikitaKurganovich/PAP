package dev.babananick.pap.radiobuttongroup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import dev.babananick.pap.icons.Checked

@Composable
fun Radio(
    modifier: Modifier = Modifier,
    checked: () -> Boolean = remember { { false } }
){
    val iconTint by rememberIconTint(checked)
    val shape by remember { mutableStateOf(RoundedCornerShape(50)) }
    val borderColor by rememberBorderColor(checked)
    Icon(
        modifier = modifier
            .size(20.dp)
            .background(Color.Transparent, shape)
            .border(2.dp, borderColor,shape)
            .clip(shape),
        imageVector = Checked,
        contentDescription = null,
        tint = iconTint
    )
}

@Composable
private fun rememberBorderColor(
    checked: () -> Boolean
): State<Color>{
    val color by remember(checked){
        derivedStateOf{
            if (checked()){
                Color(0xFF31674D)
            } else {
                Color(0xFFBBDACB)
            }
        }
    }
    return animateColorAsState(color)
}


@Composable
private fun rememberIconTint(
    checked: () -> Boolean
): State<Color> {
    val color by remember(checked){
        derivedStateOf{
            if (checked()){
                Color(0xFF31674D)
            } else {
                Color.Transparent
            }
        }
    }
    return animateColorAsState(color)
}
