package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.babananick.pap.questions.Question
import dev.babananick.pap.tests.Test
import kotlinx.coroutines.flow.*
import java.util.*

@HiltViewModel(assistedFactory = TestScreenViewModel.TestScreenViewModelFactory::class)
class TestScreenViewModel @AssistedInject constructor(
    testInteractor: PersonalTestInteractor,
    @Assisted("testId") private val testId: String,
) : ViewModel() {
    private val isFinished = MutableStateFlow(false)
    private val questionsStack = Stack<Int>()
    private var questionQuantity = 1

    private val _currentQuestionPosition = MutableStateFlow(0)
    val currentQuestionPosition: StateFlow<Int> = _currentQuestionPosition

    private val _isNotInBegging = MutableStateFlow(false)
    val isNotInBegging: StateFlow<Boolean> = _isNotInBegging

    private val _isNotInEnd = MutableStateFlow(true)
    val isNotInEnd: StateFlow<Boolean> = _isNotInEnd

    private val _previousQuestionPosition = MutableStateFlow(0)
    val previousQuestionPosition: StateFlow<Int> = _previousQuestionPosition

    private val _nextQuestionPosition = MutableStateFlow(0)
    val nextQuestionPosition: StateFlow<Int> = _nextQuestionPosition

    var state: StateFlow<TestState> = combine(
        testInteractor.receivePersonalTest(testId),
        isFinished,
    ) { test, isFinished ->
        when {
            test.questions.isNullOrEmpty() -> TestState.Base(ScreenStates.Empty)
            isFinished -> TestState.ShowResults(TestAnalyzer(test))
            else -> {
                TestState.ShowTest(test).also {
                    questionQuantity = test.questions!!.size - 1
                    updateState(
                        newCurrent = 0,
                        onUpdate = {
                            pushScreen(0)
                        })
                }
            }
        }
    }.catch {
        emit(TestState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Base(ScreenStates.Loading))

    val fetcher = {
            newPosition: Int,
            onFetch: () -> Unit ->
        updateState(newPosition, onFetch)
    }

    fun peekScreen(): Int {
       return if (questionsStack.isNotEmpty()){
           questionsStack.peek()
       } else{
           0
       }
    }

    fun pushScreen(questionPosition: Int) {
        when{
            questionsStack.isEmpty() ->{
                questionsStack.push(questionPosition)
            }
            questionsStack.peek() != questionPosition ->{
                questionsStack.push(questionPosition)
            }
        }
    }

    fun popScreen() {
        if (questionsStack.isNotEmpty()) questionsStack.pop()
    }

    fun proceedTest(test: Test): Boolean {
        if (isAllQuestionsAnswered(test)){
            isFinished.update { true }
            return true
        } else {
            test.questions!!.forEach { question ->
                if (!question.isAnswered){
                    question.isSkipped = true
                }
            }
            return false
        }
    }

    fun navigateToFirstSkipped(test: Test): Question{
        test.questions!!.forEachIndexed { index, question ->
            if (question.isSkipped){
                fetcher(index){
                    pushScreen(index)
                }
                return question
            }
        }
        return test.questions!!.first()
    }

    private fun isAllQuestionsAnswered(test: Test): Boolean{
        test.questions!!.forEach {question ->
            if (!question.isAnswered){
                return false
            }
        }
        return true
    }

    private fun updateState(
        newCurrent: Int,
        onUpdate: () -> Unit,
    ) {
        updateAllPositions(newCurrent)
        updateEnables()
        onUpdate()
    }

    private fun updateEnables() {
        _isNotInEnd.update { isNotInEnd() }
        _isNotInBegging.update { isNotInBegging() }
    }

    private fun updateAllPositions(newCurrent: Int) {
        _currentQuestionPosition.update { newCurrent }
        updateNextAndPreviousIndexes()
    }

    private fun updateNextAndPreviousIndexes() {
        _nextQuestionPosition.update {
            if (isNotInEnd()) {
                _currentQuestionPosition.value + 1
            } else _currentQuestionPosition.value
        }
        _previousQuestionPosition.update {
            if (isNotInBegging()) {
                _currentQuestionPosition.value - 1
            } else _currentQuestionPosition.value
        }
    }

    private fun isNotInBegging(): Boolean =
        _currentQuestionPosition.value != 0

    private fun isNotInEnd(): Boolean =
        _currentQuestionPosition.value != questionQuantity

    @AssistedFactory
    interface TestScreenViewModelFactory {
        fun create(
            @Assisted("testId") testId: String,
        ): TestScreenViewModel
    }

}

