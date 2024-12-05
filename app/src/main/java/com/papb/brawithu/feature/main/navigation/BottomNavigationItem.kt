package com.papb.brawithu.feature.main.navigation

import com.papb.brawithu.R
import com.papb.brawithu.feature.main.route.Screen

sealed class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val label: String
) {
    object Home: BottomNavigationItem(route = Screen.HomePage.route, icon = R.drawable.ic_home, label = "Home")
    object Konseling: BottomNavigationItem(route = Screen.Konseling.route, icon = R.drawable.ic_konseling, label = "Konseling")
    object Lapor: BottomNavigationItem(route = Screen.Lapor.route, icon = R.drawable.ic_lapor, label = "Lapor")
    object Profil: BottomNavigationItem(route = Screen.Profil.route, icon = R.drawable.ic_profil, label = "Profil")
}