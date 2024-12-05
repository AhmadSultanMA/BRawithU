package com.irza.brawithu.feature.onBoarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.brawithu.R
import com.irza.brawithu.feature.main.components.buttons.PrimaryButton
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustBased1

@Composable
fun OnBoarding(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustBased1)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(Modifier.height(50.dp))
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.onboard),
                contentDescription = "logo"
            )
            Column(Modifier.fillMaxWidth().offset(y = -100.dp).padding(horizontal = 24.dp)) {
                Text("Selamat Datang di BRAWithU!", style = MaterialTheme.typography.labelLarge)
                Spacer(Modifier.height(16.dp))
                Text(
                    "Lindungi diri dan laporkan pelecehan dengan cepat melalui BRAWithU—aplikasi aman yang siap mendukungmu dengan fitur darurat, pelaporan, dan konseling terpercaya.",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(Modifier.height(70.dp))
            PrimaryButton("Masuk") {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.OnBoard.route) {
                        inclusive = true
                    }
                }
            }
        }
    }
}