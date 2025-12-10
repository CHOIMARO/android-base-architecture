package com.company.android_base_architecture.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.company.example.navigation.ExampleRoute
import com.company.example.navigation.exampleScreen

@Composable
fun ExampleNavHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ExampleRoute
    ) {
        exampleScreen()
    }
}