package me.pengj.composedraw.tutorials

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import me.pengj.composedraw.ui.theme.MARGIN
import kotlin.random.Random

private const val ROWS = 7
private const val COLUMNS = 7
private const val LINE_WIDTH = 4f

@Composable
fun HypnoticSquaresPage() {
    Page("Hypnotic Squares") { HypnoticSquares() }
}

@Composable
fun HypnoticSquares() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN

        val rowStep = canvasHeight / ROWS
        val columnStep = canvasWidth / COLUMNS

        for (x in 0 until COLUMNS) {
            for (y in 0 until ROWS) {
                val direction =
                    Direction(Random.nextDouble(-0.7, 0.7), Random.nextDouble(-0.7, 0.7))
                val step = Random.nextInt(3, 7)
                val square = Square(x * columnStep, y * rowStep, columnStep, rowStep)
                drawSquare(this, step, direction, square)
            }
        }
    }
}

private fun drawSquare(
    drawScope: DrawScope,
    step: Int,
    direction: Direction,
    square: Square
) {
    if (step == 0) {
        return
    }

    drawScope.drawRect(
        color = Color.Black,
        topLeft = Offset(square.x, square.y),
        size = Size(square.w, square.h),
        style = Stroke(width = LINE_WIDTH)
    )

    val nextW = square.w - square.w / step
    val nextH = square.h - square.h / step
    var nextX: Double = (square.x + (square.w - nextW) / 2).toDouble()
    var nextY: Double = (square.y + (square.h - nextH) / 2).toDouble()
    nextX -= ((square.x - nextX) / (step)) * direction.directionX
    nextY -= ((square.y - nextY) / (step)) * direction.directionY
    val nextSquare = Square(nextX.toFloat(), nextY.toFloat(), nextW, nextH)

    drawSquare(drawScope, step - 1, direction, nextSquare)
}

data class Direction(val directionX: Double, val directionY: Double)
data class Square(val x: Float, val y: Float, val w: Float, val h: Float)
