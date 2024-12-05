package com.papb.brawithu.feature.artikelDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.papb.brawithu.R
import com.papb.brawithu.feature.main.components.appBar.BackAppBar
import com.papb.brawithu.feature.main.route.Screen
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustNeutral2
import com.papb.brawithu.ui.theme.CustPrimary5
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun ArtikelDetailScreen(
    navController: NavController,
    id: String
) {
    val viewModel : ArtikelDetailViewModel = viewModel()

    LaunchedEffect(key1 = true){
        viewModel.getBeritaById(id)
    }

    fun formatTanggal(tanggal: String?): String {
        if (tanggal.isNullOrEmpty()) {
            return "Tanggal tidak tersedia"
        }

        val inputFormat = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
        val outputFormat = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id", "ID"))

        return try {
            val date = inputFormat.parse(tanggal)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            "Format tanggal tidak valid"
        }
    }


    Scaffold(
        topBar = {
            BackAppBar(
                onClick = {
                    navController.navigate(Screen.HomePage.route) {
                        popUpTo(Screen.ArtikelDetail.route) {
                            inclusive = true
                        }
                    }
                }, text = "Artikel"
            )
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
                Spacer(Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_white), // Ganti dengan ikon profil
                        contentDescription = "Profile Icon",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(CustPrimary5)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Oleh BRAwithU",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Diterbitkan ${formatTanggal(viewModel.berita.value?.tanggal ?: "")}",
                            style = MaterialTheme.typography.bodySmall,
                            color = CustNeutral2
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = painterResource(id = R.drawable.article_2), // Ganti dengan gambar Anda
                    contentDescription = "Main Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.LightGray),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = viewModel.berita.value?.judul ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text =  viewModel.berita.value?.isi ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Justify,
                    color = Color.Black
                )
            }
        }
    }
}
