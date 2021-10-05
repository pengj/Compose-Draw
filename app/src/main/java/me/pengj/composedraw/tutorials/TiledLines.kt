package me.pengj.composedraw.tutorials

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.MARGIN
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import kotlin.random.Random

private const val GRID = 50f

@Composable
fun TileLinesPage() {
    Page("Tile Lines") { TiledLines() }
}

@Composable
fun TiledLines() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN

        for(x in (0..canvasWidth.toInt()).step(GRID.toInt())) {
            for (y in (0..canvasHeight.toInt()).step(GRID.toInt())) {
                if (Random.nextBoolean()) {
                    drawLine(
                        start = Offset(x = x.toFloat(), y = y.toFloat()),
                        end = Offset(x = x + GRID, y = y + GRID),
                        strokeWidth = Stroke.DefaultMiter,
                        color = Color.Black
                    )
                } else {
                    drawLine(
                        start = Offset(x = x + GRID, y = y.toFloat()),
                        end = Offset(x = x.toFloat(), y = y + GRID),
                        strokeWidth = Stroke.DefaultMiter,
                        color = Color.Black
                    )
                }
            }
        }
    }
}