package com.example.papproject.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.papproject.model.LectureQuestion
import com.example.papproject.repository.DataRepository
import kotlinx.coroutines.flow.*

class LectureScreenViewModel(
    moduleName: String,
    submoduleName: String
) : ViewModel() {
    private val repository = DataRepository
    private val questions = repository.getLectureQuestions(moduleName,submoduleName)
    private val loading = MutableStateFlow(false)

    val state = combine(
        questions,
        loading
    ) { modules, loading ->
        when {
            loading -> LectureTestState.Loading
            modules.isEmpty() -> LectureTestState.Empty
            else -> LectureTestState.ShowingModules(modules)
        }
    }.catch {
        emit(LectureTestState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LectureTestState.Loading)


}

class LectureScreenViewModelFactory(
    private val moduleName: String,
    private val submoduleName: String
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LectureScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LectureScreenViewModel(moduleName, submoduleName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class LectureTestState {
    object Loading : LectureTestState()
    data class ShowingModules(val data: List<LectureQuestion>) : LectureTestState()
    object Empty : LectureTestState()
    data class Error(val e: Throwable): LectureTestState()
}