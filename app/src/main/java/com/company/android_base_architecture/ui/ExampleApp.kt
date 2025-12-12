package com.company.android_base_architecture.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.company.android_base_architecture.navigation.Destination
import com.company.android_base_architecture.navigation.ExampleNavHost

@Composable
fun ExampleApp() {
    val navHostController = rememberNavController()
    val backStackEntry by navHostController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            BottomBarNavigation(
                navController = navHostController,
                destinations = Destination.entries, // Enum 리스트 전달
                onItemClick = { destination ->
                    // 내비게이션 이동 로직
                    navHostController.navigate(destination.route) {
                        popUpTo(navHostController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding)
        ) {
            ExampleNavHost(
                modifier = Modifier.fillMaxSize(),
                navController = navHostController,
            )
        }
    }
}