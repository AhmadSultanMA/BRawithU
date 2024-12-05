package com.papb.brawithu.feature.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.papb.brawithu.R
import com.papb.brawithu.feature.main.components.buttons.GoogleButton
import com.papb.brawithu.feature.main.components.buttons.PrimaryButton
import com.papb.brawithu.feature.main.components.textFields.DefaultField
import com.papb.brawithu.feature.main.components.textFields.PasswordField
import com.papb.brawithu.feature.main.route.Screen
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustPrimary5
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(navController: NavController) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    val viewModel : LoginViewModel = viewModel()

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(3000)
            viewModel.errMsg.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isLogin.value){
        if(viewModel.isLogin.value){
            if(viewModel.role.value == "user"){
                navController.navigate(Screen.HomePage.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            }else{
                navController.navigate(Screen.AdminPage.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustBased1)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(80.dp))
        Image(
            modifier = Modifier.size(170.dp),
            painter = painterResource(id = R.drawable.logo_red_text),
            contentDescription = "logo"
        )
        Text(
            "Masuk",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Left
        )
        Spacer(Modifier.height(8.dp))

        DefaultField(placeholder = "Email", value = email.value, onValueChange = {
            email.value = it
        }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        PasswordField(placeholder = "Kata sandi",value = password.value, onValueChange = {
            password.value = it
        }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(8.dp))

        Text(
            "Lupa kata sandi?",
            style = MaterialTheme.typography.bodySmall,
            color = CustPrimary5,
            modifier = Modifier.fillMaxWidth().clickable { },
            textAlign = TextAlign.Right
        )

        Spacer(Modifier.height(24.dp))

        if (viewModel.isLoading.value){
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                CircularProgressIndicator(
                    color = CustPrimary5
                )
            }
        }else {
            PrimaryButton(text = "Log In") {
                if(email.value == "" || password.value == ""){
                    viewModel.errMsg.value = "Harap isi semua kolom"
                }else{
                    viewModel.signIn(
                        email.value,password.value
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(5.dp))
        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp), contentAlignment = Alignment.Center){
            Text(text = viewModel.errMsg.value,style = MaterialTheme.typography.bodySmall, color = CustPrimary5)
        }
        Spacer(Modifier.height(11.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.weight(1f).height(1.dp).background(color = CustPrimary5))
            Spacer(Modifier.width(16.dp))
            Text("Atau", style = MaterialTheme.typography.bodySmall)
            Spacer(Modifier.width(16.dp))
            Spacer(Modifier.weight(1f).height(1.dp).background(color = CustPrimary5))
        }

        Spacer(Modifier.height(16.dp))

        GoogleButton {  }

        Spacer(Modifier.height(36.dp))

        Row( verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Belum mempunyai akun? ", style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold))

            Text(
                text = "Daftar",
                style = MaterialTheme.typography.bodySmall.copy(color = CustPrimary5),
                modifier = Modifier.clickable {
                    navController.navigate(Screen.SignUp.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}