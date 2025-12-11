package com.company.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ExampleRoute(
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    val exampleState by exampleViewModel.uiState.collectAsStateWithLifecycle()
    ExampleScreen(
        exampleState = exampleState,
        onValueChange = { value ->
            exampleViewModel.postIntent(ExampleIntent.OnSearchQueryChanged(value))
        },
        onSearch = {
            exampleViewModel.postIntent(ExampleIntent.OnSearch)
        },
        onClickItem = { string, boolean ->

        },
        onToggleFavorite = { item, isFavorited ->

        }
    )
}