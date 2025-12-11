package com.company.example

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.company.domain.model.ImageItem
import com.company.example.component.HomeSearchBarSection
import com.company.example.component.HomeSearchResultSection

@Composable
internal fun ExampleScreen(
    exampleState: ExampleState,
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
                imageItems = exampleState.images,
                onClickItem = onClickItem,
                onToggleFavorite = onToggleFavorite
            )
        }
    }
}
