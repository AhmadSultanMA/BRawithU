package com.irza.brawithu.feature.admin

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.brawithu.feature.homePage.HomePageViewModel
import com.irza.brawithu.feature.main.components.appBar.AdminAppBar
import com.irza.brawithu.feature.main.components.cards.AdminKonselingCard
import com.irza.brawithu.feature.main.components.cards.AdminLaporCard
import com.irza.brawithu.feature.main.components.cards.AdminSOSCard
import com.irza.brawithu.feature.main.components.cards.LaporCard
import com.irza.brawithu.feature.main.navigation.BottomNavigationAdminBar
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustPrimary5
import com.irza.brawithu.ui.theme.CustSecondary4

@Composable
fun AdminScreen(navController: NavController) {
    val viewModel: AdminViewModel = viewModel()

    Scaffold(
        topBar = {
            AdminAppBar(viewModel.user.value?.nama ?: "")
        },
        bottomBar = {
            BottomNavigationAdminBar(navController)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CustBased1)
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))

                Text(
                    "Laporan SOS",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = CustPrimary5
                )
                Spacer(Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewModel.sos.size) { index ->
                        AdminSOSCard(
                            reportId = viewModel.sos[index].id,
                            status = viewModel.sos[index].status,
                            nama = viewModel.sos[index].nama,
                            fakultas = viewModel.sos[index].fakultas
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    "Pelaporan",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = CustPrimary5
                )
                Spacer(Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewModel.lapor.size) { index ->
                        AdminLaporCard(
                            reportId = viewModel.lapor[index].id,
                            status = viewModel.lapor[index].status,
                            nama = viewModel.lapor[index].nama,
                            fakultas = "Ilmu Komputer"
                        )
                    }
                }


                Spacer(Modifier.height(16.dp))

                Text(
                    "Hasil Konseling",
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                    color = CustPrimary5
                )
                Spacer(Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(viewModel.konseling.size) { index ->
                        AdminKonselingCard(
                            reportId = viewModel.konseling[index].id,
                            status = viewModel.konseling[index].status,
                            nama = viewModel.konseling[index].nama,
                            fakultas = viewModel.konseling[index].fakultas
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}