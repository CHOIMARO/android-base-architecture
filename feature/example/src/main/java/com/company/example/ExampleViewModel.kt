package com.company.example

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.company.domain.model.ImageItem
import com.company.domain.usecase.ExamplePagingUseCase
import com.company.domain.usecase.ExampleUseCase
import com.company.ui.base.BaseViewModel
import com.company.ui.base.Intent
import com.company.ui.base.SideEffect
import com.company.ui.base.State
import com.company.ui.common.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExampleState(
    val uiStatus: UiStatus = UiStatus.Idle,
    var searchQuery: String = "",
    var images: Flow<PagingData<ImageItem>> = flowOf(PagingData.empty()),
) : State()

sealed class ExampleIntent : Intent() {
    data class OnSearchQueryChanged(val query: String) : ExampleIntent()
    object OnSearch : ExampleIntent()
}

sealed class ExampleSideEffect : SideEffect() {
    data class ShowToast(val message: String) : ExampleSideEffect()
}

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleUseCase: ExampleUseCase,
    private val examplePagingUseCase: ExamplePagingUseCase,
) : BaseViewModel<ExampleState, ExampleIntent, ExampleSideEffect>(
    initialState = ExampleState()
) {
    override fun handleIntent(intent: ExampleIntent) {
        viewModelScope.launch {
            when (intent) {
                is ExampleIntent.OnSearchQueryChanged -> {
                    reduce { copy(searchQuery = intent.query) }
                }
                ExampleIntent.OnSearch -> {
                    val query = currentState.searchQuery
                    if (query.isEmpty()) {
                        postSideEffect { ExampleSideEffect.ShowToast("검색어를 입력해주세요.") }
                        return@launch
                    }

                    reduce { copy(uiStatus = UiStatus.Loading) }

                    runCatching {
                        val perPage = 20
                        val newPagingFlow = examplePagingUseCase(query, perPage)
                            .cachedIn(viewModelScope)

                        reduce {
                            copy(
                                uiStatus = UiStatus.Content,
                                images = newPagingFlow
                            )
                        }

                    }.onFailure { e ->
                        reduce { copy(uiStatus = UiStatus.Error()) }
                        postSideEffect { ExampleSideEffect.ShowToast(e.message ?: "알 수 없는 오류가 발생했습니다.") }
                    }
                }
                else -> {}
            }
        }
    }
}