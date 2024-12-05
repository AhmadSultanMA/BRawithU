package com.papb.brawithu.feature.main.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustPrimary4

@Composable
fun PrimaryButton(text: String,onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(100))
            .background(color = CustPrimary4)
            .clickable { onClick() },
        contentAlignment = Alignment.Center,

    ) {
        Text(
            modifier = Modifier.padding(vertical = 12.dp),
            text = text, style = MaterialTheme.typography.titleSmall,
            color = CustBased1
        )
    }
}