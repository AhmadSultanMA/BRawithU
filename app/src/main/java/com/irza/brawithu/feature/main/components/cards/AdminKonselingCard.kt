package com.irza.brawithu.feature.main.components.cards

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.irza.brawithu.ui.theme.CustBased2
import com.irza.brawithu.ui.theme.CustNeutral4
import com.irza.brawithu.ui.theme.CustPrimary2
import com.irza.brawithu.ui.theme.CustPrimary5
import com.irza.brawithu.ui.theme.SystemSuccess

@Composable
fun AdminKonselingCard(reportId: String, status: String, nama: String, fakultas: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
            .background(CustBased2, shape = RoundedCornerShape(12.dp))
            .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "ID Laporan: $reportId",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(start = 4.dp)) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(SystemSuccess, shape = CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Status: $status",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Waktu Konseling ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = CustNeutral4
                    )
                )
                Text(
                    text = "18 Desember 2024",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Pengirim ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = CustNeutral4
                    )
                )
                Text(
                    text = nama,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Fakultas ",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                        color = CustNeutral4
                    )
                )
                Text(
                    text = fakultas,
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                )
            }

            Box(
                modifier = Modifier.width(300.dp),
                contentAlignment = Alignment.TopEnd

            ) {
                Box(
                    modifier = Modifier.clip(shape = RoundedCornerShape(100))
                        .background(color = CustPrimary2, shape = RoundedCornerShape(100)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        "Detail", style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = CustPrimary5
                        ),
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 6.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}