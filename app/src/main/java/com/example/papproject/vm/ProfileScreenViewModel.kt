package com.example.papproject.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papproject.model.LectureModule
import com.example.papproject.repository.DataRepository
import kotlinx.coroutines.flow.*

class ProfileScreenViewModel :ViewModel(){
    private var _results = hashMapOf<String, HashMap<String, Int>>()
    private val repository = DataRepository
    private val loading = MutableStateFlow(false)
    private val results = MutableStateFlow(_results)


    val userResults = _results

    val state = combine(
        results,
        loading
    ) {data, loading ->
        getResults()
        when {
            loading -> ProfileState.Loading
            data.isEmpty() -> ProfileState.Empty
            else -> ProfileState.ShowingResults(data)
        }
    }.catch {
        emit(ProfileState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ProfileState.Loading)

    fun getResults(): HashMap<String, HashMap<String, Int>> {
        repository.getUserResults {
            _results = it
        }
        results.update { _results }
        return _results
    }
}

sealed class ProfileState {
    object Loading : ProfileState()
    data class ShowingResults(val data: HashMap<String, HashMap<String, Int>>) : ProfileState()
    object Empty : ProfileState()
    data class Error(val e: Throwable) : ProfileState()
}