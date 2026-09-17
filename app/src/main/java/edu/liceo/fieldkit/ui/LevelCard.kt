package edu.liceo.fieldkit.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import edu.liceo.fieldkit.hardware.rememberAccelerometer
import kotlin.math.abs

private fun isLevel(x: Float, y: Float): Boolean {
    return abs(x) < 1.5f && abs(y) < 1.5f
}

@Composable
fun LevelCard() {
    val v = rememberAccelerometer()
    val level = isLevel(v[0], v[1])

    Column {
        Text(
            "x = %.2f y = %.2f z = %.2f".format(v[0], v[1], v[2])
        )

        Text(
            if (level) "LEVEL ✓" else "Tilted, adjust",
            color = if (level) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
    }
}