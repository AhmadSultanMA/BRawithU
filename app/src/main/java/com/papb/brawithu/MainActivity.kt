package com.papb.brawithu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.papb.brawithu.feature.main.navigation.Navigation
import com.papb.brawithu.ui.theme.BRAWithUTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BRAWithUTheme {
                Navigation()
            }
        }
    }
}
