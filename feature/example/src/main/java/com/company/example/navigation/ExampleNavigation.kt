package com.company.example.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.company.example.ExampleRoute
import kotlinx.serialization.Serializable

@Serializable
object ExampleRoute

fun NavGraphBuilder.exampleScreen(

) {
    composable<ExampleRoute> {
        ExampleRoute()
    }
}