package joebarker.coffee.viewModel

import kotlinx.coroutines.flow.StateFlow

interface LoadingViewModel {
    val isLoading: StateFlow<Boolean>

}
