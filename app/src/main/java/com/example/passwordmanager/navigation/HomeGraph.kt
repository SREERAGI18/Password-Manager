package com.example.passwordmanager.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.passwordmanager.ui.screens.HomeScreen

@Composable
fun HomeGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screens.Home) {
        composable<Screens.Home> { HomeScreen() }
    }
}