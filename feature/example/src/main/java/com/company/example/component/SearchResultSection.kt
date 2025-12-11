package com.company.example.component

import android.media.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.company.domain.model.ImageItem
import com.company.example.ExampleState

@Composable
fun HomeSearchResultSection(
    exampleState: ExampleState,
    imageItems: List<ImageItem>,
    onClickItem: (String, Boolean) -> Unit,
    onToggleFavorite: (item: ImageItem, isFavorited: Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            exampleState.isLoading -> {
                CircularProgressIndicator()
            }

            else -> {
                HomeSearchResult(
                    imageItems = imageItems,
                    onClickItem = onClickItem,
                    onToggleFavorite = onToggleFavorite,
                )
            }
        }
    }
}

@Composable
fun HomeSearchResult(
    imageItems: List<ImageItem>,
    onClickItem: (id: String, isVideo: Boolean) -> Unit,
    onToggleFavorite: (item: ImageItem, isFavorited: Boolean) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                text = "Search Results",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        HandleImage(
            imageItems = imageItems,
            onClickItem = onClickItem,
            onToggleFavorite = onToggleFavorite
        )
    }
}

@Composable
fun InitialSearchScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Search",
            modifier = Modifier.size(100.dp),
            tint = Color.Gray
        )
        Text(
            text = "Search for videos and images",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray
        )
    }
}

/**
 * 첫 페이지 로드(refresh) 상태를 처리하는 Composable
 */
private fun LazyGridScope.HandleImage(
    imageItems: List<ImageItem>,
    onClickItem: (id: String, isVideo: Boolean) -> Unit,
    onToggleFavorite: (item: ImageItem, isFavorited: Boolean) -> Unit
) {
    items(
        count = imageItems.size,
    ) { index ->
        val image = imageItems[index]
        if (image != null) {
            ImageGridItem(
                imageItem = image,
                onClick = { onClickItem(image.id, false) },
                onToggleFavorite = { isFavorited ->
                    onToggleFavorite(image, isFavorited)
                }
            )
        }
    }
}