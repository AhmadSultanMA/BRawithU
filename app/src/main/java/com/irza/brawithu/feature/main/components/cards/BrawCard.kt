package com.irza.brawithu.feature.main.components.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.irza.brawithu.R
import com.irza.brawithu.ui.theme.CustPrimary5
import com.irza.brawithu.ui.theme.CustSecondary2

@Composable
fun BrawCard(
) {
    Box(
        modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = CustSecondary2, shape = RoundedCornerShape(10.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Jaga Diri, Jaga Sesama",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = CustPrimary5,
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "Bersama",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = CustPrimary5,
                            fontWeight = FontWeight.Normal,
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.logo_red_text),
                        contentDescription = "logo",
                        contentScale = ContentScale.FillWidth
                    )
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    text = "Brawijaya Lebih Aman",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = CustPrimary5,
                        fontWeight = FontWeight.Normal,
                    )
                )
            }
            // Mock phone image
            Image(
                painter = painterResource(id = R.drawable.phone_mockup),
                contentDescription = "App preview",
                modifier = Modifier
                    .size(160.dp)
                    .padding(16.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}
