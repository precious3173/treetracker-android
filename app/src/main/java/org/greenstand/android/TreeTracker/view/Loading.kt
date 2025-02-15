package org.greenstand.android.TreeTracker.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun showLoadingSpinner(isLoading: Boolean) {
    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(height = 70.dp, width = 70.dp),
                color = AppColors.Green
            )
        }
    }
}

@Composable
fun DeterminateProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val strokeWidth = 8.dp
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Butt)
    }

    Canvas(
        modifier = modifier
            .progressSemantics(progress)
            .fillMaxWidth()
    ) {
        val diameterOffset = stroke.width / 2
        val arcDimension = size.width - 2 * diameterOffset

        // Function to draw a default styled arc.
        fun drawProgress(
            color: Color,
            sweepAngle: Float
        ) = drawArc(
            color = color,
            startAngle = 180f,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(diameterOffset, diameterOffset),
            size = Size(arcDimension, arcDimension),
            style = stroke
        )

        drawProgress(AppColors.MediumGray, 360f)  // Background progress.
        drawProgress(AppColors.Orange, progress * 360f)  // Foreground progress.
    }
}
