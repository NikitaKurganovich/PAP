package com.example.papproject.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papproject.model.LectureModule
import com.example.papproject.repository.DataRepository
import kotlinx.coroutines.flow.*

class HomeScreenViewModel : ViewModel() {
    private val repository = DataRepository
    private val loading = MutableStateFlow(false)
    private val modules = repository.getModules()

    val state = combine(
        modules,
        loading
    ) { modules, loading ->
        when {
            loading -> HomeState.Loading
            modules.isEmpty() -> HomeState.Empty
            else -> HomeState.ShowingModules(modules)
        }
    }.catch {
        emit(HomeState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState.Loading)

}


sealed class HomeState {

    object Loading : HomeState()
    data class ShowingModules(val data: List<LectureModule>) : HomeState()
    object Empty : HomeState()
    data class Error(val e: Throwable): HomeState()
}