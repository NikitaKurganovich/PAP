package com.example.pap.vm

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pap.model.EmotionalIntelligenceQuestion
import com.example.pap.repository.DataRepository
import kotlinx.coroutines.flow.*

class TestScreenViewModel : ViewModel() {

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
            tests.isEmpty() || (que.isEmpty() && isChosen) -> TestState.Empty
            isChosen -> TestState.EmotionalIntelligence(que)
            else -> TestState.ShowingPersonalTests(tests)
        }
    }.catch {
        emit(TestState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Loading)

    fun collectResults(
        data: List<EmotionalIntelligenceQuestion>,
        isDialogOnConfirmOpen: MutableState<Boolean>,
        isDialogOnNotFullOpen: MutableState<Boolean>
    ){
        val map = mutableMapOf<String, Int>()
        data.forEach {
            if (map[it.related_scale] == null) {
                map[it.related_scale] = it.answeredScore
            } else {
                map[it.related_scale] = map[it.related_scale]!! + it.answeredScore
            }
            if (!it.isAnswered) {
                isDialogOnNotFullOpen.value = true
                return
            } else {
                userResults = map
                isDialogOnConfirmOpen.value = true
            }
        }
    }

    fun upsertTestResult(testName: String) {
        repository.upsertPersonalResults(testName, userResults)
    }

    fun isResultsExist(
    ): Boolean {
        return userResults.isEmpty()
    }
}

sealed class TestState {
    object Loading : TestState()
    data class ShowingPersonalTests(val data: List<String>) : TestState()
    data class EmotionalIntelligence(val data: List<EmotionalIntelligenceQuestion>) : TestState()
    object Empty : TestState()
    data class ShowingResults(val score: Map<String, Int>) : TestState()
    data class Error(val e: Throwable) : TestState()
}