package me.pengj.composedraw.tutorials

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import me.pengj.composedraw.Page
import me.pengj.composedraw.ui.theme.CANVAS_PADDING
import me.pengj.composedraw.ui.theme.MARGIN
import kotlin.random.Random

private const val MIN_RADIUS = 2.0
private const val MAX_RADIUS = 200
private const val TOTAL_CIRCLE = 800
private const val CREATE_CIRCLE_ATTEMPTS = 500

@Composable
fun CirclePackingPage() {
    Page("Cubic Disarray") { CirclePacking() }
}

@Composable
fun CirclePacking() {
    Canvas(modifier = Modifier.padding(CANVAS_PADDING)) {
        val canvasWidth = size.width - MARGIN
        val canvasHeight = size.height - MARGIN

        val circles = createCircles(canvasWidth, canvasHeight)

        for (circle in circles) {
            drawCircle(
                color = Color.Black,
                radius = circle.radius.toFloat(),
                center = Offset(circle.x.toFloat(), circle.y.toFloat()),
                style = Stroke(width = 2f)
            )
        }
    }
}

private fun createCircles(width: Float, height: Float): List<Circle> {
    val circles = mutableListOf<Circle>()

    for (j in 0..TOTAL_CIRCLE) {
        var safeToDraw = false
        var circle = iniCircle(width, height)
        for (i in 0..CREATE_CIRCLE_ATTEMPTS) {
            if (!hasCollision(circle, circles, width, height)) {
                safeToDraw = true
                break
            }
            circle = iniCircle(width, height)
        }

        if (!safeToDraw) {
            continue
        }

        for (r in 2..MAX_RADIUS) {
            circle.increase()
            if (hasCollision(circle, circles, width, height)) {
                circle.decrease()
                break
            }
        }

        circles.add(circle)
    }
    return circles
}

private fun hasCollision(
    circle: Circle,
    circles: List<Circle>,
    width: Float,
    height: Float
): Boolean {
    for (c in circles) {
        val d = c.radius + circle.radius
        val x = circle.x - c.x
        val y = circle.y - c.y

        if (d * d >= (x * x + y * y)) {
            return true
        }
    }

    if (circle.x + circle.radius >= width || circle.x - circle.radius <= 0.0) {
        return true
    }

    if (circle.y + circle.radius >= height || circle.y - circle.radius <= 0.0) {
        return true
    }

    return false
}


private fun iniCircle(width: Float, height: Float): Circle {
    return Circle(
        Random.nextDouble(width.toDouble()),
        Random.nextDouble(height.toDouble()),
        MIN_RADIUS
    )
}


class Circle(val x: Double, val y: Double, var radius: Double) {
    fun increase() {
        radius += 1.0
    }

    fun decrease() {
        radius -= 1.0
    }
}