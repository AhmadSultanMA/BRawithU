package com.papb.brawithu.feature.main.components.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.papb.brawithu.R
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustPrimary5
import androidx.compose.foundation.layout.Arrangement as Arrangement1

@Composable
fun AdminAppBar(nama: String) {
    fun firstWord(nama: String): String {
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
            Spacer(Modifier.height(48.dp))
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hello ${firstWord(nama)}!",
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
