package joebarker.coffee.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel : ViewModel(), LoadingViewModel {
    protected val _isLoading = MutableStateFlow(true)
    override val isLoading: StateFlow<Boolean> = _isLoading
    protected val _error = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error
}