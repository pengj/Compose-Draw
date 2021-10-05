package me.pengj.composedraw.tutorials

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import me.pengj.composedraw.ui.theme.MARGIN
import kotlin.math.PI
import kotlin.random.Random


private const val LINE_WIDTH = 4f
private const val SQUARE_SIZE = 80f

@Composable
fun CubicDisarrayPage() {
    Page("Cubic Disarray") { CubicDisarray() }
}

@Composable
fun CubicDisarray() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN


        for (x in (0 until canvasWidth.toInt()).step(SQUARE_SIZE.toInt())) {
            for (y in (0 until canvasHeight.toInt()).step(SQUARE_SIZE.toInt())) {

                val factor = y / canvasHeight * SQUARE_SIZE
                val xOffset = factor * Random.nextDouble(-0.1, 0.1) + x
                val yOffset = factor * Random.nextDouble(-0.1, 0.1) + y
                val rotation = factor * Random.nextDouble(-PI / 6.0, PI / 6.0)
                rotate(
                    degrees = rotation.toFloat(),
                    pivot = Offset(xOffset.toFloat(), yOffset.toFloat())
                ) {
                    drawRect(
                        color = Color.Black, topLeft = Offset(x.toFloat(), y.toFloat()),
                        size = Size(SQUARE_SIZE, SQUARE_SIZE),
                        style = Stroke(width = LINE_WIDTH)
                    )
                }
            }
        }


    }
}