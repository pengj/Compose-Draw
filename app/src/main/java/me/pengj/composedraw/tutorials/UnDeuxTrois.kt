package me.pengj.composedraw.tutorials

import android.graphics.Point
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

private const val ROWS = 20
private const val COLUMNS = 14
private const val LINE_WIDTH = 10f

@Composable
fun UnDeuxTroisPage() {
    Page("Un Deux Trois") { UnDeuxTrois() }
}

@Composable
fun UnDeuxTrois() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN

        val rowStep = canvasHeight / ROWS
        val columnStep = canvasWidth / COLUMNS

        val lines =  mutableListOf<MutableList<Point>>()

        for (y in 0 until ROWS) {
            val line = mutableListOf<Point>()
            for (x in 0 until COLUMNS) {

                val steps = when {
                    y < ROWS/3 -> {
                        listOf(0.5)
                    }
                    y < ROWS/3 * 2 -> {
                        listOf(0.2, 0.8)
                    }
                    else -> {
                        listOf(0.1, 0.5, 0.9)
                    }
                }

                for(step in steps) {
                    val nextX = (x * columnStep) + (step - 0.5) * columnStep;
                    val nextY = y * rowStep
                    line.add(Point(nextX.toInt(), nextY.toInt()));
                }
            }
            lines.add(line)
        }

        for (line in lines) {
            for(point in line) {
                val rotation  = 100 * Random.nextFloat()
                rotate(degrees = rotation,
                pivot = Offset(point.x.toFloat() + LINE_WIDTH/2, point.y.toFloat() + rowStep/2)) {
                    drawRect(color = Color.Black,
                        topLeft = Offset(point.x.toFloat(), point.y.toFloat()),
                        size = Size(LINE_WIDTH, rowStep),
                    )
                }
            }
        }


    }


}