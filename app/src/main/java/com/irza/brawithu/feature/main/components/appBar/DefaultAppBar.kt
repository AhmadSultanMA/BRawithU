package com.irza.brawithu.feature.main.components.appBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.irza.brawithu.R
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustPrimary5

@Composable
fun DefaultAppBar(text: String) {
    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier.fillMaxWidth().background(color = CustPrimary5).height(80.dp)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = CustBased1,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
        }
    }
}