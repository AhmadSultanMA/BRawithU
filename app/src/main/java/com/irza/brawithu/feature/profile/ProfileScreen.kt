package com.irza.brawithu.feature.profile

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.brawithu.feature.homePage.HomePageViewModel
import com.irza.brawithu.feature.main.components.appBar.BackAppBar
import com.irza.brawithu.feature.main.components.appBar.ProfileAppBar
import com.irza.brawithu.feature.main.navigation.BottomNavigationBar
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustNeutral1
import com.irza.brawithu.ui.theme.CustNeutral4
import com.irza.brawithu.ui.theme.CustPrimary5

@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel : ProfileViewModel = viewModel()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = viewModel.isSuccess.value){
        if(viewModel.isSuccess.value){
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Profil.route) {
                    inclusive = true
                }
            }
        }
    }

    Scaffold(
        topBar = {
            ProfileAppBar(onClick = {
                showDialog = true
            }, text = "Profil")
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        CustomAlertDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                viewModel.logout()
            },
            title = "Konfirmasi Logout",
            text = "Apakah Anda yakin ingin logout?",
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = CustBased1)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "Mahasiswa UB",
                style = MaterialTheme.typography.titleSmall,
                color = CustPrimary5,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = viewModel.user.value?.nim ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = CustPrimary5,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = viewModel.user.value?.email ?: "",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = CustNeutral4,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = CustNeutral1
            )
            Spacer(modifier = Modifier.height(8.dp))

            MahasiswaInfoRow(label = "Nama", value = viewModel.user.value?.nama ?: "")
            MahasiswaInfoRow(label = "Angkatan", value = viewModel.user.value?.angkatan ?: "")
            MahasiswaInfoRow(label = "Status Akademik", value = viewModel.user.value?.status ?: "")
            MahasiswaInfoRow(label = "Fakultas", value = viewModel.user.value?.fakultas ?: "")
            MahasiswaInfoRow(label = "Jurusan", value = viewModel.user.value?.jurusan ?: "")
            MahasiswaInfoRow(
                label = "Jenjang dan Program Studi",
                value = "${viewModel.user.value?.jenjang ?: ""} - ${viewModel.user.value?.progstud ?: ""}"
            )
        }
    }
}

@Composable
fun MahasiswaInfoRow(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {

        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = CustNeutral4,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = CustNeutral1
        )
    }
}

@Composable
fun CustomAlertDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    title: String,
    text: String,
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = CustBased1,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = title,
                        color = CustPrimary5,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = text,
                        color = CustPrimary5.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text(
                                text = "Tidak",
                                color = CustPrimary5,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        TextButton(onClick = onConfirm) {
                            Text(
                                text = "Ya",
                                color = CustPrimary5,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }
    }
}
