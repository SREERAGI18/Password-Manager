package com.example.passwordmanager.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordmanager.encryption.EncryptedSharedPreferenceManager.isPinSet
import com.example.passwordmanager.ui.screens.HomeScreen
import com.example.passwordmanager.ui.screens.PinVerifyScreen
import com.example.passwordmanager.ui.screens.SetPinScreen

@Composable
fun HomeGraph(
    navController: NavHostController,
) {
    val context = LocalContext.current
    val launchScreen = if(isPinSet(context)) {
        Screens.PinVerify
    } else {
        Screens.PinSet
    }

    NavHost(navController = navController, startDestination = launchScreen) {
        composable<Screens.Home> { HomeScreen() }
        composable<Screens.PinSet> { SetPinScreen(navController) }
        composable<Screens.PinVerify> { PinVerifyScreen(navController) }
    }
}