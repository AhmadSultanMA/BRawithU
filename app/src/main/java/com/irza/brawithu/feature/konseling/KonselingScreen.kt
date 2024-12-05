package com.irza.brawithu.feature.konseling

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.brawithu.feature.homePage.HomePageViewModel
import com.irza.brawithu.feature.main.components.appBar.BackAppBar
import com.irza.brawithu.feature.main.components.appBar.DefaultAppBar
import com.irza.brawithu.feature.main.components.buttons.PrimaryButton
import com.irza.brawithu.feature.main.components.textFields.DefaultField
import com.irza.brawithu.feature.main.navigation.BottomNavigationBar
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustBased5
import com.irza.brawithu.ui.theme.CustNeutral4
import com.irza.brawithu.ui.theme.CustPrimary5
import com.irza.brawithu.ui.theme.SystemError
import com.irza.brawithu.ui.theme.SystemSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun KonselingScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf("Pilih Jam") }

    val nama = remember { mutableStateOf("") }
    val nim = remember { mutableStateOf("") }
    val fakultas = remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Pria") }

    val coroutineScope = rememberCoroutineScope()

    val timeOptions = listOf(
        "07.00 - 08.30",
        "09.00 - 11.30",
        "13.00 - 14.30",
        "15.00 - 16.30"
    )

    val viewModel : KonselingViewModel = viewModel()

    LaunchedEffect(viewModel.isSuccess.value) {
        if (viewModel.isSuccess.value) {
            selectedTime = "Pilih Jam"
            nama.value = ""
            nim.value = ""
            fakultas.value = ""
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
            DefaultAppBar(text = "Konseling")
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
                    "Ajukan Konseling",
                    style = MaterialTheme.typography.titleSmall,
                    color = CustPrimary5
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Lengkapi data berikut ini agar bisa melakukan konseling di Unit Layanan Terpadu Kekerasan Seksual dan Perundungan (ULTKSP) di fakultasmu. ",
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
                        "Fakultas",
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
                    placeholder = "Masukkan Fakultas",
                    value = fakultas.value,
                    onValueChange = { fakultas.value = it })

                Spacer(Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        "Pilih Jam",
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

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = Color.Gray,
                                shape = RoundedCornerShape(20)
                            )
                            .clip(RoundedCornerShape(20))
                    ) {
                        Button(
                            onClick = {
                                expanded = !expanded
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = CustBased1),
                            shape = RoundedCornerShape(20),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = selectedTime,
                                    modifier = Modifier
                                        .weight(1f),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if(selectedTime == "Pilih Jam") CustBased5 else Color.Black
                                )
                                Icon(
                                    imageVector = if (expanded)  Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                                    contentDescription = "Dropdown Icon",
                                    tint = Color.Black
                                )
                            }
                        }
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth().background(color = CustBased1)
                    ) {
                        timeOptions.forEach { time ->
                            DropdownMenuItem(
                                modifier = Modifier.drawBehind {
                                    drawLine(
                                        brush = SolidColor(Color.Gray),
                                        start = Offset(0f, size.height),
                                        end = Offset(size.width, size.height),
                                        strokeWidth = 1.dp.toPx()
                                    )
                                },
                                text = { Text(text = time, style = MaterialTheme.typography.bodySmall, color = Color.Black) },
                                onClick = {
                                    selectedTime = time
                                    expanded = false
                                }
                            )
                        }
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
                    PrimaryButton("Ajukan Konseling") {
                        if (nama.value.isEmpty() || selectedGender.isEmpty() || selectedTime.isEmpty() || nim.value.isEmpty() || fakultas.value.isEmpty()) {
                            viewModel.errMsg.value = "Harap isi semua kolom"
                        } else {
                            coroutineScope.launch {
                                try {
                                    viewModel.postKonseling(
                                        nama = nama.value,
                                        nim = nim.value,
                                        jenis_kelamin = selectedGender,
                                        jam = selectedTime,
                                        fakultas = fakultas.value
                                    )
                                } catch (e: Exception) {
                                    viewModel.errMsg.value = "Gagal Mengajukan Konseling"
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
