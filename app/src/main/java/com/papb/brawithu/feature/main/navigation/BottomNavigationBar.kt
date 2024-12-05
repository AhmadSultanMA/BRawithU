package com.papb.brawithu.feature.main.navigation

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
import com.papb.brawithu.R
import com.papb.brawithu.feature.main.route.Screen
import com.papb.brawithu.ui.theme.CustBased1
import com.papb.brawithu.ui.theme.CustNeutral2
import com.papb.brawithu.ui.theme.CustPrimary5

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar(
        containerColor = CustBased1,
        tonalElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Ikon kiri pertama
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Home.icon),
                    contentDescription = BottomNavigationItem.Home.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Home.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Home.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationItem.Home.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Home.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
        )


        // Ikon kedua
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Konseling.icon),
                    contentDescription = BottomNavigationItem.Konseling.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Konseling.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Konseling.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationItem.Konseling.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Konseling.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )

        // Spacer untuk tombol tengah
        Spacer(modifier = Modifier.weight(1f))

        // Ikon ketiga
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Lapor.icon),
                    contentDescription = BottomNavigationItem.Lapor.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Lapor.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Lapor.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationItem.Lapor.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Lapor.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )

        // Ikon keempat
        NavigationBarItem(
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = CustPrimary5,
                unselectedIconColor = Color.Gray,
                indicatorColor = CustBased1
            ),
            icon = {
                Icon(
                    painter = painterResource(id = BottomNavigationItem.Profil.icon),
                    contentDescription = BottomNavigationItem.Profil.label,
                    modifier = Modifier.size(24.dp)
                )
            },
            label = {
                Text(
                    text = BottomNavigationItem.Profil.label,
                    style = TextStyle(
                        fontFamily = FontFamily(Font(R.font.maison)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 10.sp,
                    ),
                    color = if (currentRoute == BottomNavigationItem.Profil.route) CustPrimary5 else CustNeutral2
                )
            },
            selected = currentRoute == BottomNavigationItem.Profil.route,
            onClick = {
                navController.navigate(BottomNavigationItem.Profil.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .offset(y = (-10).dp),

        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.SOS.route) {
                    popUpTo(navController.graph.id) { inclusive = true }
                }
            },
            shape = CircleShape,
            containerColor = CustPrimary5
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo_white),
                contentDescription = "Scan Now",
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
