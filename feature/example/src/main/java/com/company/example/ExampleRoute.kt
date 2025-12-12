package com.company.example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
internal fun ExampleRoute(
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    val exampleState by exampleViewModel.uiState.collectAsStateWithLifecycle()
    val images = exampleState.images.collectAsLazyPagingItems()
    ExampleScreen(
        exampleState = exampleState,
        images = images,
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