package com.example.pap.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pap.model.LectureQuestion
import com.example.pap.repository.DataRepository
import kotlinx.coroutines.flow.*

class LectureScreenViewModel(
    val moduleName: String,
    val submoduleName: String
) : ViewModel() {
    private val repository = DataRepository

    private val questions = repository.getLectureQuestions(moduleName,submoduleName)
    private val questionsLoading = MutableStateFlow(false)
    val isResultsSend = MutableStateFlow(false)
    val isTheoryRead = MutableStateFlow(false)
    var score = 0

    private val theory = repository.getLectureTheory(moduleName,submoduleName)

    val state = combine(
        theory,
        isTheoryRead,
        questions,
        questionsLoading,
        isResultsSend
    ) {theory,isRead, questions, loading, isSend ->
        when {
            loading -> LectureState.Loading
            theory.isEmpty() || questions.isEmpty() -> LectureState.Empty
            isSend -> LectureState.ShowingResults(score)
            isRead -> LectureState.ShowingQuestions(questions)
            else -> {LectureState.ShowingTheory(theory)}
        }
    }.catch {
        emit(LectureState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LectureState.Loading)


    fun upsertScore(moduleName: String, submoduleName: String, score: Int){
        repository.upsertResults(moduleName, submoduleName, score)
    }

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

sealed class LectureState{
    object Loading : LectureState()
    data class ShowingTheory(val data: String) : LectureState()
    data class ShowingQuestions(val data: List<LectureQuestion>) : LectureState()
    data class ShowingResults(val score: Int): LectureState()

    object Empty : LectureState()
    data class Error(val e: Throwable): LectureState()
}