package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.babananick.pap.tests.Test
import kotlinx.coroutines.flow.*

@HiltViewModel(assistedFactory = TestScreenViewModel.TestScreenViewModelFactory::class)
class TestScreenViewModel @AssistedInject constructor(
    testInteractor: PersonalTestInteractor,
    @Assisted("testName") private val testName: String,
) : ViewModel() {
    private val loading = MutableStateFlow(false)
    private val isFinished = MutableStateFlow(false)

    private lateinit var localTest: Test
    private val _currentQuestionPosition = MutableStateFlow(0)
    val currentQuestionPosition: StateFlow<Int> = _currentQuestionPosition

    private val _isNotInBegging = MutableStateFlow(false)
    val isNotInBegging: StateFlow<Boolean> = _isNotInBegging

    private val _isNotInEnd = MutableStateFlow(true)
    val isNotInEnd: StateFlow<Boolean> = _isNotInEnd

    var state: StateFlow<TestState> = combine(
        testInteractor.receivePersonalTest(testName),
        loading,
        isFinished,
    ) { test, isFinished, loading ->
        when {
            loading -> TestState.Base(ScreenStates.Loading)
            test.questions.isNullOrEmpty() -> TestState.Base(ScreenStates.Empty)
            isFinished -> TestState.ShowResults(test.interpretation!!)
            else -> {
                TestState.ShowTest(test).also {
                    localTest = test
                }
            }
        }
    }.catch {
        println("ME HERE ${it.message}")
        emit(TestState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Base(ScreenStates.Loading))

    fun isNotInBegging(): Boolean =
        _currentQuestionPosition.value != 0

    fun isNotInEnd(): Boolean =
        _currentQuestionPosition.value != localTest.questions!!.size

    fun decreasePosition() {
        renewPositions()
        if (isNotInBegging()) {
            _currentQuestionPosition.value -= 1
        }
    }

    fun increasePosition() {
        renewPositions()
        if (isNotInEnd()) {
            _currentQuestionPosition.value += 1
        }
    }

    private fun renewPositions() {
        _isNotInEnd.update { isNotInEnd() }
        _isNotInBegging.update { isNotInBegging() }
    }

    fun fetchPosition(): MutableStateFlow<Int> {
        return _currentQuestionPosition
    }

    @AssistedFactory
    interface TestScreenViewModelFactory {
        fun create(
            @Assisted("testName") testName: String,
        ): TestScreenViewModel
    }

}

