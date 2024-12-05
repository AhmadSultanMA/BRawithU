package com.irza.brawithu.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.irza.brawithu.R
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustPrimary5
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel: SplashViewModel = viewModel()

    val route = when {
        viewModel.auth.uid != null && viewModel.user.value?.role == "admin" -> Screen.AdminPage.route
        viewModel.auth.uid != null && viewModel.user.value?.role == "user" -> Screen.HomePage.route
        else -> Screen.OnBoard.route
    }


    LaunchedEffect(Unit) {
        delay(2000)
        navController.navigate(route) {
            popUpTo(Screen.Splash.route) {
                inclusive = true
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustPrimary5)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.size(300.dp),
                painter = painterResource(id = R.drawable.logo_white_text),
                contentDescription = "logo"
            )

        }
    }
}