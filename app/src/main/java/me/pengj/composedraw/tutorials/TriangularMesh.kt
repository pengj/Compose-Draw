package me.pengj.composedraw.tutorials

import android.graphics.Point
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import me.pengj.composedraw.ui.theme.MARGIN
import kotlin.random.Random

private const val ROWS = 9
private const val COLUMNS = 8

@Composable
fun TriangularMeshPage() {
    Page("Triangular Mesh") { TriangularMesh() }
}

@Composable
fun TriangularMesh() {

    fun getGreyColor(): Color {
        val c = Random.nextInt(0, 256)
        return Color(c, c, c)
    }

    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN
        val rowStep = canvasHeight / ROWS
        val columnStep = canvasWidth / COLUMNS

        val lines = mutableListOf<MutableList<Point>>()
        var odd = true
        for (y in 0 until ROWS) {
            odd = !odd
            val line = mutableListOf<Point>()
            for (x in 0 until COLUMNS) {
                val stepX = if (odd) {
                    columnStep / 2 + columnStep * x
                } else {
                    columnStep * x
                }

                val nextX = stepX + Random.nextDouble(-0.4, 0.4) * columnStep
                val nextY = (y * rowStep) + Random.nextDouble(-0.4, 0.4) * rowStep

                val point = Point(nextX.toInt(), nextY.toInt())
                line.add(point)

                drawCircle(
                    color = Color.DarkGray,
                    2f,
                    Offset(nextX.toFloat(), nextY.toFloat()),
                    style = Stroke(width = 1f)
                )
            }
            lines.add(line)
        }

        odd = true
        for (y in 0 until (ROWS - 1)) {
            odd = !odd
            val line = mutableListOf<Point>()
            val currentLine = lines[y]
            val nextLine = lines[y + 1]
            for (x in 0 until COLUMNS) {
                if (odd) {
                    line.add(currentLine[x])
                    line.add(nextLine[x])
                } else {
                    line.add(nextLine[x])
                    line.add(currentLine[x])
                }
            }

            for (i in 0..line.size - 3) {
                val path = Path()
                path.moveTo(line[i].x.toFloat(), line[i].y.toFloat())
                path.lineTo(line[i + 1].x.toFloat(), line[i + 1].y.toFloat())
                drawLine(
                    color = Color.Black, start = Offset(line[i].x.toFloat(), line[i].y.toFloat()),
                    end = Offset(line[i + 1].x.toFloat(), line[i + 1].y.toFloat()), strokeWidth = 2f
                )
                path.lineTo(line[i + 2].x.toFloat(), line[i + 2].y.toFloat())
                drawLine(
                    color = Color.Black,
                    start = Offset(line[i + 1].x.toFloat(), line[i + 1].y.toFloat()),
                    end = Offset(line[i + 2].x.toFloat(), line[i + 2].y.toFloat()),
                    strokeWidth = 2f
                )
                drawPath(path = path, color = getGreyColor())
            }
        }
    }
}