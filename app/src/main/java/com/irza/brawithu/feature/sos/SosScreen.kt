package com.irza.brawithu.feature.sos

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.irza.brawithu.feature.main.components.appBar.BackAppBar
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustPrimary5
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.irza.brawithu.R
import com.irza.brawithu.feature.admin.AdminViewModel
import kotlinx.coroutines.launch

@Composable
fun SOS(navController: NavController) {
    val context = LocalContext.current

    val viewModel: SOSViewModel = viewModel()

    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.sound)
    }

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            BackAppBar(
                onClick = {
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.SOS.route) {
                            inclusive = true
                        }
                    }
                }, text = "SOS"
            )
        }
    ) { paddingValues ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .background(color = CustBased1)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(Modifier.height(40.dp))
                Text(
                    "Anda dalam Bahaya?",
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = CustPrimary5,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "Tekan tombol SOS, maka suara nyaring akan keluar untuk memastikan keamanan Anda!",
                    modifier = Modifier.padding(horizontal = 24.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(120.dp))

                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(200.dp)
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(100),
                                clip = false
                            )
                            .clip(CircleShape)
                            .background(CustPrimary5)
                            .clickable(onClick = {
                                if (mediaPlayer.isPlaying) {
                                    mediaPlayer.pause()
                                    mediaPlayer.seekTo(0)
                                }
                                mediaPlayer.start()

                                coroutineScope.launch {
                                    try {
                                        viewModel.postSOS(
                                            nama = viewModel.user.value?.nama ?: "",
                                            fakultas = viewModel.user.value?.fakultas ?: ""
                                        )
                                    } catch (e: Exception) {
                                        Log.e("Error", e.toString())
                                    }
                                }
                            })
                    ) {
                        Text(
                            text = "SOS",
                            style = MaterialTheme.typography.headlineLarge,
                            color = CustBased1
                        )
                    }
                }

            }
        }
    }
}