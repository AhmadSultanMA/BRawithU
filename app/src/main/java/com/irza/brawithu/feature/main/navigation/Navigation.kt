package com.irza.brawithu.feature.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.irza.brawithu.feature.admin.AdminScreen
import com.irza.brawithu.feature.admin.ProfileAdminScreen
import com.irza.brawithu.feature.artikelDetail.ArtikelDetailScreen
import com.irza.brawithu.feature.homePage.HomePageScreen
import com.irza.brawithu.feature.konseling.KonselingScreen
import com.irza.brawithu.feature.lapor.LaporScreen
import com.irza.brawithu.feature.login.LoginScreen
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.feature.onBoarding.OnBoarding
import com.irza.brawithu.feature.profile.ProfileScreen
import com.irza.brawithu.feature.signUp.SignUpScreen
import com.irza.brawithu.feature.sos.SOS
import com.irza.brawithu.feature.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(route = Screen.Splash.route) {
            SplashScreen(navController = navController)
        }

        composable(route = Screen.OnBoard.route) {
            OnBoarding(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController)
        }

        composable(route = Screen.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(route = Screen.HomePage.route) {
            HomePageScreen(navController)
        }

        composable(route = Screen.Konseling.route) {
            KonselingScreen(navController)
        }

        composable(route = Screen.Lapor.route) {
            LaporScreen(navController)
        }

        composable(route = Screen.SOS.route) {
            SOS(navController)
        }

        composable(route = Screen.Profil.route) {
            ProfileScreen(navController)
        }

        composable(route = Screen.AdminPage.route) {
            AdminScreen(navController)
        }

        composable(route = Screen.ProfilAdmin.route) {
            ProfileAdminScreen(navController)
        }

        composable(route = "${Screen.ArtikelDetail.route}/{artikel_id}",
            arguments = listOf(
                navArgument("artikel_id") {
                    type = NavType.StringType
                }
            ))
        {
            val artikel_id = it.arguments?.getString("artikel_id") ?: ""
            ArtikelDetailScreen(navController = navController, artikel_id)
        }
    }
}