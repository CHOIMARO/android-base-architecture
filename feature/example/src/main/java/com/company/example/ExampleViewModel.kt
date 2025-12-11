package com.company.example

import androidx.lifecycle.viewModelScope
import com.company.domain.model.ImageItem
import com.company.domain.usecase.ExampleUseCase
import com.company.ui.base.BaseViewModel
import com.company.ui.base.Intent
import com.company.ui.base.SideEffect
import com.company.ui.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExampleState(
    val isLoading: Boolean = false,
    var searchQuery: String = "",
    var images: List<ImageItem> = emptyList(),
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

                    reduce { copy(isLoading = true) }

                    runCatching {
                        val result = exampleUseCase(query)
                        reduce {
                            copy(
                                isLoading = false,
                                images = result
                            )
                        }
                    }.onFailure { e ->
                        reduce { copy(isLoading = false) }
                        postSideEffect { ExampleSideEffect.ShowToast(e.message ?: "알 수 없는 오류가 발생했습니다.") }
                    }
                }
                else -> {}
            }
        }
    }

}