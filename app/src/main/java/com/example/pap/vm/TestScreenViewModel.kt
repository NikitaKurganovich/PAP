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

    private var _results = hashMapOf<String, Int>()
    var userResults = MutableStateFlow(getResults())

    val state = combine(
        tests,
        loading,
        isChosen,
        questions,
        isResultsSend,
    ) { tests, loading, isChosen, que, isSend ->
        when {
            loading -> TestState.Loading
            isSend -> TestState.ShowingResults(userResults.value)
            tests.isEmpty() || (que.isEmpty() && isChosen) -> TestState.Empty
            isChosen -> TestState.EmotionalIntelligence(que)
            else -> TestState.ShowingPersonalTests(tests)
        }
    }.catch {
        emit(TestState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Loading)

    fun collectResults(
        data: List<EmotionalIntelligenceQuestion>,
        isDialogOnNotFullOpen: MutableState<Boolean>,
        isDialogOnConfirmOpen: MutableState<Boolean>
    ){
        val map = hashMapOf<String, Int>()

        data.forEach { question->
            if (map[question.related_scale] == null) {
                map[question.related_scale] = question.answeredScore
            } else {
                map[question.related_scale] = map[question.related_scale]!! + question.answeredScore
            }
            if (!question.isAnswered) {
                isDialogOnNotFullOpen.value = true
                return
            }
        }
        if (!isDialogOnNotFullOpen.value) {
            userResults.update { map }
            isDialogOnConfirmOpen.value = true
        }
    }

    fun upsertTestResult(testName: String) {
        repository.upsertPersonalResults(testName, userResults.value)
    }

    fun isResultsExist(): Boolean {
        return userResults.value.containsKey("Эмоциональный интеллект")
    }

    private fun getResults(): HashMap<String, Int>{
        repository.getUserResults{
            _results = it["Эмоциональный интеллект"] ?: hashMapOf()
            userResults.update { _results }
        }
        return _results
    }
}

sealed class TestState {
    object Loading : TestState()
    data class ShowingPersonalTests(val data: List<String>) : TestState()
    data class EmotionalIntelligence(val data: List<EmotionalIntelligenceQuestion>) : TestState()
    object Empty : TestState()
    data class ShowingResults(val score: HashMap<String, Int>) : TestState()
    data class Error(val e: Throwable) : TestState()
}