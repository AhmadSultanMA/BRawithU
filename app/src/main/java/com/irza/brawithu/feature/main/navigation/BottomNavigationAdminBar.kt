package com.irza.brawithu.feature.main.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.irza.brawithu.R
import com.irza.brawithu.feature.main.route.Screen
import com.irza.brawithu.ui.theme.CustBased1
import com.irza.brawithu.ui.theme.CustNeutral2
import com.irza.brawithu.ui.theme.CustPrimary5

@Composable
fun BottomNavigationAdminBar(navController: NavController) {

    NavigationBar(
        containerColor = CustBased1,
        tonalElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Ikon ketiga
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationAdminItem.Lapor.icon),
                    contentDescription = BottomNavigationAdminItem.Lapor.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationAdminItem.Lapor.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationAdminItem.Lapor.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationAdminItem.Lapor.route,
            onClick = {
                navController.navigate(BottomNavigationAdminItem.Lapor.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Ikon keempat
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationAdminItem.Profil.icon),
                    contentDescription = BottomNavigationAdminItem.Profil.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationAdminItem.Profil.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationAdminItem.Profil.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationAdminItem.Profil.route,
            onClick = {
                navController.navigate(BottomNavigationAdminItem.Profil.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )
    }

}