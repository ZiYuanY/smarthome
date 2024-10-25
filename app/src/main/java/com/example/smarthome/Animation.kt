package com.example.smarthome

//来源：https://github.com/vitaviva/compose-weather
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

// 天气动画，目前只有太阳，这里不涉及

@Composable
fun `Anima-tableSun`(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "")

    val animateTween by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(10000), RepeatMode.Restart), label = ""
    )

    Canvas(modifier.rotate(animateTween)) {

        val radius = size.width / 6
        val stroke = size.width / 20
        val centerOffset = Offset(size.width / 30, size.width / 30)

        // draw circle
        drawCircle(
            color = Color.Black,
            radius = radius + stroke / 2,
            style = Stroke(width = stroke),
            center = center + centerOffset
        )
        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill,
            center = center + centerOffset
        )

        // draw line

        val lineLength = radius * 0.6f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX + centerOffset.x
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY + centerOffset.y
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun Sun(modifier: Modifier = Modifier) {

    Canvas(modifier) {

        val radius = size.width / 6
        val stroke = size.width / 20

        // draw circle
        drawCircle(
            color = Color.Black,
            radius = radius + stroke / 2,
            style = Stroke(width = stroke),
        )
        drawCircle(
            color = Color.White,
            radius = radius,
            style = Fill,
        )

        // draw line

        val lineLength = radius * 0.2f
        val lineOffset = radius * 1.8f
        (0..7).forEach { i ->

            val radians = Math.toRadians(i * 45.0)

            val offsetX = lineOffset * cos(radians).toFloat()
            val offsetY = lineOffset * sin(radians).toFloat()

            val x1 = size.width / 2 + offsetX
            val x2 = x1 + lineLength * cos(radians).toFloat()

            val y1 = size.height / 2 + offsetY
            val y2 = y1 + lineLength * sin(radians).toFloat()

            drawLine(
                color = Color.Black,
                start = Offset(x1, y1),
                end = Offset(x2, y2),
                strokeWidth = stroke,
                cap = StrokeCap.Round
            )
        }
    }
}

@Preview
@Composable
fun PreviewAnimatableSun() {
    `Anima-tableSun`(Modifier.size(100.dp))
}




@Composable
fun GradientBackground(
    modifier: Modifier = Modifier
) {
    // 创建用于动画的变量
    val transition = rememberInfiniteTransition(label = "")
    val gradientColors = listOf(Color.Red, Color.Blue, Color.Green, Color.Yellow)

    // 动态更新渐变位置
    val animatedValue = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing)
        ), label = ""
    ).value

    // 生成渐变颜色
    val brush = Brush.linearGradient(
        colors = gradientColors.map {
            it.copy(alpha = (1 - animatedValue).coerceIn(0f, 1f))
        },
        start = Offset.Zero,
        end = Offset.Infinite
    )

    Box(
        modifier = modifier
            .background(brush)
    )
}