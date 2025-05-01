package com.example.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.passwordmanager.encryption.EncryptedSharedPreferenceManager
import com.example.passwordmanager.encryption.EncryptedSharedPreferenceManager.isPinSet
import com.example.passwordmanager.navigation.HomeGraph
import com.example.passwordmanager.navigation.Screens
import com.example.passwordmanager.ui.theme.MainBg
import com.example.passwordmanager.ui.theme.PasswordManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PasswordManagerTheme {
                val navController = rememberNavController()

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    HomeGraph(navController)
                }
            }
        }
    }
}