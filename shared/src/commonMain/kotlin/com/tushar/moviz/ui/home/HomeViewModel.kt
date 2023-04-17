package com.tushar.moviz.ui.home

import com.tushar.moviz.api.MovizWebService
import com.tushar.moviz.data.getHttpClient
import com.tushar.moviz.router.SavedStateHandle
import com.tushar.moviz.router.ViewModel
import com.tushar.moviz.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel(private val savedState: SavedStateHandle): ViewModel() {
    private val _state = MutableStateFlow(savedState.get() ?: HomeState.initialState)
    val state: StateFlow<HomeState> = _state.asStateFlow()
    private val webService = MovizWebService(getHttpClient())

    init {
        fetchMovies()
        observeStateUpdates()
    }

    private fun observeStateUpdates() {
        launch {
            state.collectLatest {
                savedState.set(it)
            }
        }
    }

    fun fetchMovies() {
        launch(Dispatchers.Default) {
            _state.update { it.copy(isLoading = true, isError = false) }
            val result = webService.getTopMovies()
            _state.update { it.copy(isLoading = false) }
            if (result.isSuccess) {
                _state.update { it.copy(movies = result.getOrNull()?.results ?: emptyList()) }
            } else if (result.isFailure) {
                _state.update { it.copy(isError = true) }
            }
        }
    }
}