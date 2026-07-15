package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private val ForestGreen = Color(0xFF2E7D32)
private val LeafGreen = Color(0xFF66BB6A)
private val SageGreen = Color(0xFFA5D6A7)
private val MintGreen = Color(0xFFC8E6C9)
private val LightMint = Color(0xFFE8F5E9)
private val DarkText = Color(0xFF355E3B)
private val WhiteCard = Color.White.copy(alpha = 0.85f)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    BusinessCard()
                }
            }
        }
    }
}

@Composable
fun BusinessCard() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.naturebackground),
            contentDescription = "Nature Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.10f),
                            Color.Black.copy(alpha = 0.18f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Card(
                shape = RoundedCornerShape(28.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White.copy(alpha = 0.30f)
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.profile_photo),
                        contentDescription = "Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(170.dp)
                            .clip(CircleShape)
                            .border(
                                5.dp,
                                ForestGreen,
                                CircleShape
                            )
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "🍃 Anghel Dadole",
                        color = ForestGreen,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Bachelor of Science in Information Technology",
                        color = DarkText,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Aspiring Software Developer",
                        color = ForestGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "\"Growing ideas through technology while caring for nature.\"",
                        color = DarkText,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    ContactCard(
                        icon = Icons.Default.Call,
                        text = "0912-345-6789"
                    )}

                    Spacer(modifier = Modifier.height(28.dp))

                    ContactCard(
                        icon = Icons.Default.Share,
                        text = "github.com/angheldadole"
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    ContactCard(
                        icon = Icons.Default.Email,
                        text = "dadoleanghel@email.com"
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "🌱 Keep Learning • Keep Growing 🌱",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                }

            }

        }

    }

    @Composable
    fun ContactCard(
        icon: ImageVector,
        text: String
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = WhiteCard,
                    shape = RoundedCornerShape(18.dp)
                )
                .border(
                    width = 1.dp,
                    color = SageGreen,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(horizontal = 18.dp),

            verticalAlignment = Alignment.CenterVertically
        ){

            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = ForestGreen,
                modifier = Modifier.size(22.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Text(
                text = text,
                modifier = Modifier.weight(1f),
                color = DarkText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

        }

    }

    @Preview(showBackground = true)
    @Composable
    fun PreviewBusinessCard() {

        MaterialTheme {
            BusinessCard()
        }

    }
