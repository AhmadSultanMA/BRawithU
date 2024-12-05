package com.irza.brawithu.feature.homePage

import android.widget.Space
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.brawithu.R
import com.irza.brawithu.feature.main.components.appBar.HomeAppBar
import com.irza.brawithu.feature.main.components.cards.BrawCard
import com.irza.brawithu.feature.main.components.cards.LaporCard
import com.irza.brawithu.feature.main.components.cards.ArtikelCard
import com.irza.brawithu.feature.main.navigation.BottomNavigationBar
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustPrimary5
import com.irza.brawithu.ui.theme.CustSecondary4

@Composable
fun HomePageScreen(navController: NavController) {
    val viewModel : HomePageViewModel = viewModel()

    Scaffold(
        topBar = {
            HomeAppBar(viewModel.user.value?.nama ?: "")
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CustBased1)
                .padding(paddingValues)
                .padding(start = 16.dp)
        ) {
            item {
                Spacer(Modifier.height(16.dp))
                BrawCard()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Riwayat Laporan", style = MaterialTheme.typography.titleSmall, color = CustPrimary5)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(viewModel.lapor.size) { index ->
                        LaporCard(
                            reportId = viewModel.lapor[index].id,
                            status = viewModel.lapor[index].status,
                            statusColor = if (viewModel.lapor[index].status == "Sedang Ditangani") CustSecondary4 else CustPrimary5
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Baca Artikel Terbaru", style = MaterialTheme.typography.titleSmall, color = CustPrimary5)
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow(
                    Modifier.fillMaxWidth()
                ) {
                    items(viewModel.berita.size) { berita ->
                        val berita = viewModel.berita[berita]
                        ArtikelCard(
                            navController = navController,
                            id = berita.id,
                            title = berita.judul,
                            imageResId = R.drawable.article_2,
                            date = berita.tanggal,
                            description = berita.isi
                        )
                    }
                }
                Spacer(Modifier.height(30.dp))
            }
        }
    }
}