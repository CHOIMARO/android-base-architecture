package com.company.example

import com.company.ui.base.BaseViewModel
import com.company.ui.base.Intent
import com.company.ui.base.SideEffect
import com.company.ui.base.State
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class ExampleState(
    val isLoading: Boolean = true,
) : State()

sealed class ExampleIntent : Intent() {
}

sealed class ExampleSideEffect : SideEffect() {
    data class ShowToast(val message: String) : ExampleSideEffect()
}

@HiltViewModel
class ExampleViewModel @Inject constructor(

) : BaseViewModel<ExampleState, ExampleIntent, ExampleSideEffect>(
    initialState = ExampleState()
) {
    override fun handleIntent(intent: ExampleIntent) {
        when (intent) {

            else -> {}
        }
    }

}