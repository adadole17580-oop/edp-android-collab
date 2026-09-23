package com.liceo.account.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liceo.account.domain.model.User
import java.time.LocalDate
import java.time.Period

@Composable
fun ProfileScreen(
    user: User,
    onLogout: () -> Unit
) {

    val age = calculateAge(user.birthdate)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "My Profile",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    text = "You successfully logged in!"
                )

                Text(
                    text = "Welcome back, ${user.fullName}."
                )
            }
        }

        ProfileRow(
            label = "Full name",
            value = user.fullName
        )

        ProfileRow(
            label = "Email",
            value = user.email
        )

        ProfileRow(
            label = "Birthdate",
            value = user.birthdate
        )

        ProfileRow(
            label = "Age",
            value = if (age != null) {
                "$age years old"
            } else {
                "N/A"
            }
        )

        ProfileRow(
            label = "User ID",
            value = user.id
        )

        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}

@Composable
private fun ProfileRow(
    label: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = label
        )

        Text(
            text = value
        )
    }
}

private fun calculateAge(
    birthdate: String
): Int? {

    return try {

        val birthDate = LocalDate.parse(birthdate)
        val today = LocalDate.now()

        Period.between(
            birthDate,
            today
        ).years

    } catch (e: Exception) {

        null
    }
}
