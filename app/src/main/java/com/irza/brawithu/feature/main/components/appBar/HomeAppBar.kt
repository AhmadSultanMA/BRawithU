package com.irza.brawithu.feature.main.components.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import com.irza.brawithu.R
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustPrimary1
import com.irza.brawithu.ui.theme.CustPrimary5
import androidx.compose.foundation.layout.Arrangement as Arrangement1

@Composable
fun HomeAppBar(nama: String) {
    fun firstwWord(nama: String): String {
        return nama.split(" ").firstOrNull() ?: ""
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                CustPrimary5,
                shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Spacer(Modifier.height(30.dp))
            Surface(
                color = CustPrimary1,
                shape = RoundedCornerShape(100),
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location), // Ikon lokasi
                        contentDescription = "Location Icon",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Klojen, Malang",
                        style = MaterialTheme.typography.bodySmall,
                        color = CustPrimary5
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hello ${firstwWord(nama)}",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White, fontWeight = FontWeight.Bold)
                )
                Spacer(Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement1.Start
                ) {
                    Text(
                        text = "Wujudkan Brawijaya Aman dengan",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = CustBased1,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Image(
                        modifier = Modifier.width(90.dp),
                        painter = painterResource(id = R.drawable.logo_white_text),
                        contentDescription = "logo",
                        contentScale = ContentScale.FillWidth
                    )
                }
            }

            Spacer(Modifier.height(30.dp))
        }

    }
}
