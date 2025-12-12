package com.company.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.company.domain.model.ImageItem
import com.company.example.component.HomeSearchBarSection
import com.company.example.component.HomeSearchResultSection

@Composable
internal fun ExampleScreen(
    exampleState: ExampleState,
    images: LazyPagingItems<ImageItem>,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    onClickItem: (String, Boolean) -> Unit,
    onToggleFavorite: (item: ImageItem, isFavorited: Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            HomeSearchBarSection(
                searchQuery = exampleState.searchQuery,
                clearButtonVisible = exampleState.searchQuery.isNotBlank(),
                onValueChange = onValueChange,
                onSearch = onSearch,
            )

            HomeSearchResultSection(
                exampleState = exampleState,
                imageItems = images,
                onClickItem = onClickItem,
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}
