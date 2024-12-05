package com.papb.brawithu.feature.lapor

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.papb.brawithu.feature.main.components.appBar.DefaultAppBar
import com.papb.brawithu.feature.main.components.buttons.PrimaryButton
import com.papb.brawithu.feature.main.components.textFields.DefaultField
import com.papb.brawithu.feature.main.navigation.BottomNavigationBar
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustBased5
import com.papb.brawithu.ui.theme.CustNeutral4
import com.papb.brawithu.ui.theme.CustPrimary5
import com.papb.brawithu.ui.theme.SystemError
import com.papb.brawithu.ui.theme.SystemSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LaporScreen(navController: NavController) {
    val nama = remember { mutableStateOf("") }
    val nim = remember { mutableStateOf("") }
    val lokasi = remember { mutableStateOf("") }
    val detail = remember { mutableStateOf("") }
    var isDragging by remember { mutableStateOf(false) }
    var selectedGender by remember { mutableStateOf("Pria") }

    val viewModel : LaporViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(viewModel.isSuccess.value) {
        if (viewModel.isSuccess.value) {
            detail.value = ""
            nama.value = ""
            nim.value = ""
            lokasi.value = ""
            selectedGender = "Pria"
        }
    }

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(5000)
            viewModel.errMsg.value = ""
        }
    }

    Scaffold(
        topBar = {
            DefaultAppBar(text = "Pelaporan")
        },
        bottomBar = {
            BottomNavigationBar(navController)
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
                Spacer(Modifier.height(16.dp))
                Text(
                    "Detail Laporan Kejadian",
                    style = MaterialTheme.typography.titleSmall,
                    color = CustPrimary5
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Lengkapi data berikut ini agar bisa melakukan pelaporan secara online. Pastikan data sudah benar dan sesuai format yang diminta.  ",
                    style = MaterialTheme.typography.bodySmall,
                    color = CustNeutral4,
                    textAlign = TextAlign.Justify
                )
                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Nama Lengkap",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }
                Spacer(Modifier.height(8.dp))
                DefaultField(
                    placeholder = "Masukkan Nama",
                    value = nama.value,
                    onValueChange = { nama.value = it })
                Spacer(Modifier.height(8.dp))
                Text(
                    "Contoh: Budi Jatmika",
                    style = MaterialTheme.typography.bodySmall,
                    color = CustBased5
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Jenis Kelamin",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = selectedGender == "Pria",
                            onClick = { selectedGender = "Pria" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CustPrimary5,
                                unselectedColor = Color.Gray
                            )
                        )
                        Text(
                            text = "Pria",
                            color = if (selectedGender == "Pria") CustPrimary5 else Color.Black,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable { selectedGender = "Pria" }
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        RadioButton(
                            selected = selectedGender == "Wanita",
                            onClick = { selectedGender = "Wanita" },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = CustPrimary5,
                                unselectedColor = Color.Gray
                            )
                        )
                        Text(
                            text = "Wanita",
                            color = if (selectedGender == "Wanita") CustPrimary5 else Color.Black,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.clickable { selectedGender = "Wanita" }
                        )
                    }
                }

                Spacer(Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "NIM",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }
                Spacer(Modifier.height(8.dp))
                DefaultField(
                    placeholder = "Masukkan NIM",
                    value = nim.value,
                    onValueChange = { nim.value = it })

                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Lokasi Kejadian",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }
                Spacer(Modifier.height(8.dp))
                DefaultField(
                    placeholder = "Masukkan Lokasi",
                    value = lokasi.value,
                    onValueChange = { lokasi.value = it })

                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Detail Kejadian",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }
                Spacer(Modifier.height(8.dp))
                DefaultField(
                    placeholder = "Ceritakan Detail Kejadian",
                    value = detail.value,
                    onValueChange = { detail.value = it })

                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Bukti Kejadian",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Black
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "*",
                        style = MaterialTheme.typography.titleSmall,
                        color = CustPrimary5
                    )
                }

                Spacer(Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .border(
                            width = 1.dp,
                            color = if (isDragging) Color.Gray else Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(color = if (isDragging) Color(0xFFE0E0E0) else Color(0xFFF5F5F5))
                        .clickable {
                            // Buka picker file
                            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
                                type = "*/*"
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = "Upload Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Unggah File, Gambar atau\nVideo di sini",
                            textAlign = TextAlign.Center,
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(Modifier.height(24.dp))

                if (viewModel.isLoading.value){
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator(
                            color = CustPrimary5
                        )
                    }
                }else {
                    PrimaryButton("Ajukan Laporan") {
                        if (nama.value.isEmpty() || selectedGender.isEmpty() || lokasi.value.isEmpty() || nim.value.isEmpty() || detail.value.isEmpty()) {
                            viewModel.errMsg.value = "Harap isi semua kolom"
                        } else {
                            coroutineScope.launch {
                                try {
                                    viewModel.postLapor(
                                        nama = nama.value,
                                        nim = nim.value,
                                        jenis_kelamin = selectedGender,
                                        detail = detail.value,
                                        lokasi = lokasi.value,
                                    )
                                } catch (e: Exception) {
                                    viewModel.errMsg.value = "Gagal Mengirim Laporan"
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(6.dp))
                Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp), contentAlignment = Alignment.Center){
                    Text(text = viewModel.errMsg.value,style = MaterialTheme.typography.bodySmall, color = if(viewModel.isSuccess.value) SystemSuccess else SystemError)
                }

                Spacer(Modifier.height(12.dp))

            }
        }
    }

}
