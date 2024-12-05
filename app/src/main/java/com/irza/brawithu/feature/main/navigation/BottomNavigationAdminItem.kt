package com.irza.brawithu.feature.main.navigation

import com.irza.brawithu.R
import com.irza.brawithu.feature.main.route.Screen

sealed class BottomNavigationAdminItem(
    val route: String,
    val icon: Int,
    val label: String
) {
    object Lapor: BottomNavigationAdminItem(route = Screen.AdminPage.route, icon = R.drawable.ic_lapor, label = "Lapor")
    object Profil: BottomNavigationAdminItem(route = Screen.ProfilAdmin.route, icon = R.drawable.ic_profil, label = "Profil")
}