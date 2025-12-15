package com.company.example

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

@Composable
internal fun ExampleRoute(
    exampleViewModel: ExampleViewModel = hiltViewModel()
) {
    val exampleState by exampleViewModel.uiState.collectAsStateWithLifecycle()
    val images = exampleState.images.collectAsLazyPagingItems()

    HandleExampleSideEffect(exampleViewModel.sideEffect)

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

@Composable
fun HandleExampleSideEffect(
    sideEffectFlow: Flow<ExampleSideEffect>,
    onNavigateToDetail: (String) -> Unit = {} // 나중을 위해 미리 파라미터로 뚫어둠
) {
    val context = LocalContext.current

    LaunchedEffect(sideEffectFlow) {
        sideEffectFlow.collect { effect ->
            when (effect) {
                is ExampleSideEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                // 추후 네비게이션이 필요하면 이렇게 사용
                // is ExampleSideEffect.NavigateToDetail -> {
                //     onNavigateToDetail(effect.id)
                // }
            }
        }
    }
}
