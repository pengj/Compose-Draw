package me.pengj.composedraw

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Frame(modifier: Modifier = Modifier,
          content: @Composable () -> Unit) {
    Surface(
        modifier = modifier,
        color = Color.White,
        border = BorderStroke(24.dp, Color.Black),
        shape = RoundedCornerShape(1.dp),
        elevation = 8.dp,
        content = content
    )
}