package com.example.papproject.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.papproject.model.EmotionalIntelligenceQuestion
import com.example.papproject.repository.DataRepository
import kotlinx.coroutines.flow.*

class TestScreenViewModel: ViewModel() {

    private val repository = DataRepository
    private val loading = MutableStateFlow(false)
    private val tests = repository.getTests()
    private val questions = repository.getPersonalTestQuestions("Эмоциональный интеллект")
    val isChosen = MutableStateFlow(false)
    val isResultsSend = MutableStateFlow(false)

    var userResults = emptyMap<String, Int>()

    val state = combine(
        tests,
        loading,
        isChosen,
        questions,
        isResultsSend
    ) { tests, loading, isChosen, que, isSend ->
        when {
            loading -> TestState.Loading
            isSend -> TestState.ShowingResults(userResults)
            tests.isEmpty() || (que.isEmpty() && isChosen)-> TestState.Empty
            isChosen -> TestState.EmotionalIntelligence(que)
            else -> TestState.ShowingPersonalTests(tests)
        }
    }.catch {
        emit(TestState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Loading)


    fun upsertTestResult(testName: String){
        repository.upsertPersonalResults(testName, userResults)
    }
}

sealed class TestState {
    object Loading : TestState()
    data class ShowingPersonalTests(val data: List<String>) : TestState()
    data class EmotionalIntelligence(val data: List<EmotionalIntelligenceQuestion>): TestState()
    object Empty : TestState()
    data class ShowingResults(val score: Map<String,Int>): TestState()
    data class Error(val e: Throwable) : TestState()
}