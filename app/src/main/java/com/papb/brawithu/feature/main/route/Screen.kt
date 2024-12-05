package com.papb.brawithu.feature.main.route

sealed class Screen(val route: String){
    object Splash : Screen("splash")
    object OnBoard : Screen("onBoard")
    object HomePage : Screen("homePage")
    object Login : Screen("login")
    object SignUp : Screen("signup")
    object Konseling : Screen("konseling")
    object Lapor : Screen("lapor")
    object SOS : Screen("sos")
    object Profil : Screen("profil")
    object AdminPage : Screen("adminPage")
    object ArtikelDetail : Screen("artikelDetail")
    object ProfilAdmin : Screen("profilAdmin")

}