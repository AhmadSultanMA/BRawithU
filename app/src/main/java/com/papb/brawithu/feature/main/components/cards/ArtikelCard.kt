package com.papb.brawithu.feature.main.components.cards

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.papb.brawithu.feature.main.route.Screen
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustNeutral4
import com.papb.brawithu.ui.theme.CustPrimary5

@Composable
fun ArtikelCard(
    navController: NavController,
    id: String,
    title: String,
    @DrawableRes imageResId: Int,
    date: String,
    description: String
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .width(200.dp)
            .padding(end = 16.dp)
            .clickable {
                navController.navigate("${Screen.ArtikelDetail.route}/${id}") {
                    popUpTo(Screen.HomePage.route) {
                        inclusive = true
                    }
                }
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().background(color = CustBased1)
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = date,
                style = MaterialTheme.typography.bodySmall.copy(color = CustPrimary5),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(
                    color = CustNeutral4
                ),
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
