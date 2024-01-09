package com.example.pap.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pap.model.LectureModule
import com.example.pap.repository.DataRepository
import kotlinx.coroutines.flow.*

class HomeScreenViewModel : ViewModel() {
    private val repository = DataRepository
    private val loading = MutableStateFlow(false)
    private val modules = repository.getModules()

    private var _results = hashMapOf<String, HashMap<String, Int>>()
    val userResults = _results

    val state = combine(
        modules,
        loading
    ) { modules, loading ->
        getResults()
        when {
            loading -> HomeState.Loading
            modules.isEmpty() -> HomeState.Empty
            else -> HomeState.ShowingModules(modules)
        }
    }.catch {
        emit(HomeState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState.Loading)

    fun getResults(): HashMap<String, HashMap<String, Int>> {
        repository.getUserResults {
            _results = it
        }
        return _results
    }

    fun isResultsExist(
        moduleName: String,
        submoduleName: String,
    ): Boolean {
        val map = _results[moduleName] as Map<String, Int>
        return map.containsKey(submoduleName)
    }
}


sealed class HomeState {
    object Loading : HomeState()
    data class ShowingModules(val data: List<LectureModule>) : HomeState()
    object Empty : HomeState()
    data class Error(val e: Throwable) : HomeState()
}