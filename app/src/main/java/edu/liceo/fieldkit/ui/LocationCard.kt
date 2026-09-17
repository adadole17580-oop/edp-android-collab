package edu.liceo.fieldkit.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import edu.liceo.fieldkit.hardware.currentLocation

@Composable
fun LocationCard() {
    val context = LocalContext.current

    var access by remember { mutableStateOf("None") }
    var text by remember { mutableStateOf("No location yet.") }

    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { result ->
        access = when {
            result[Manifest.permission.ACCESS_FINE_LOCATION] == true -> "Precise"
            result[Manifest.permission.ACCESS_COARSE_LOCATION] == true -> "Approximate"
            else -> "None"
        }
    }

    Card(
        Modifier
    ) {
        Column(
            Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Where am I?",
                style = MaterialTheme.typography.titleMedium
            )

            Text("Location access: $access")

            Button(
                onClick = {
                    if (access == "None") {
                        launcher.launch(
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            )
                        )
                    } else {
                        context.currentLocation { loc ->
                            text = loc?.let {
                                "%.5f, %.5f (±%.0f m)".format(
                                    it.latitude,
                                    it.longitude,
                                    it.accuracy
                                )
                            } ?: "No fix yet. Is Location turned on?"
                        }
                    }
                }
            ) {
                Text("Tag my location")
            }

            if (access == "None") {
                Text("Location denied. Allow it in Settings.")
            }

            Text(text)
        }
    }
}