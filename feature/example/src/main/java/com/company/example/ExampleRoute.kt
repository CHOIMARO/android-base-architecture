package com.company.example

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
internal fun ExampleRoute(
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    ExampleScreen()
}