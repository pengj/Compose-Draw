package me.pengj.composedraw.tutorials

import android.graphics.Point
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.DstOut
import androidx.compose.ui.graphics.BlendMode.Companion.DstOver
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.MARGIN
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import kotlin.math.abs
import kotlin.math.max
import kotlin.random.Random

private const val STEP = 10
private const val LINES = 24

@Composable
fun JoyDivisionPage() {
    Page("Joy Division") { JoyDivision() }
}

@Composable
fun JoyDivision() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN
        val lineGap = canvasHeight / LINES

        val lines = mutableListOf<MutableList<Point>>()
        for (y in (STEP..canvasHeight.toInt()).step(lineGap.toInt())) {
            val line = mutableListOf<Point>()
            for (x in (STEP..canvasWidth.toInt()).step(STEP)) {
                val distanceToCenter = abs(x - canvasWidth / 2)
                val variance = max(canvasWidth / 2 - STEP * LINES / 2 - distanceToCenter, 0f)
                val random = Random.nextDouble() * variance / 2 * -1
                val point = Point(x, (y + random).toInt())
                line.add(point)
            }
            lines.add(line)
        }

        for (i in (2 until lines.size - 1)) {
            val path = Path()
            val line = lines[i]
            val len = line.size

            path.moveTo(line[0].x.toFloat(), line[0].y.toFloat())
            for (j in (0 until len - 2)) {
                val xc = (line[j].x + line[j + 1].x) / 2
                val yc = (line[j].y + line[j + 1].y) / 2
                path.quadraticBezierTo(
                    line[j].x.toFloat(),
                    line[j].y.toFloat(),
                    xc.toFloat(),
                    yc.toFloat()
                )
            }

            path.quadraticBezierTo(
                line[len - 2].x.toFloat(), line[len - 2].y.toFloat(),
                line[len - 1].x.toFloat(), line[len - 1].y.toFloat()
            )

            drawPath(
                path = path,
                color = Color.Black,
                style = Stroke(width = 5f),
                blendMode = DstOut
            )
        }
    }
}