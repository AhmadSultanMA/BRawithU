package com.irza.brawithu.feature.main.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.irza.brawithu.ui.theme.CustBased2
import com.irza.brawithu.ui.theme.CustNeutral4

@Composable
fun LaporCard(reportId: String, status: String, statusColor: Color) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
            .background(CustBased2, shape = RoundedCornerShape(12.dp))
            .padding(start = 8.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
    ) {
        Column {
            Text(
                text = "ID Laporan: #$reportId",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.Bottom, modifier = Modifier.padding(start = 4.dp)) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(statusColor, shape = CircleShape)
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
        }
    }
}

