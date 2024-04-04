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
import kotlin.properties.Delegates

@HiltViewModel(assistedFactory = TestScreenViewModel.TestScreenViewModelFactory::class)
class TestScreenViewModel @AssistedInject constructor(
    testInteractor: PersonalTestInteractor,
    @Assisted("testName") private val testName: String,
) : ViewModel() {
    private val loading = MutableStateFlow(false)
    private val isFinished = MutableStateFlow(false)

    lateinit var localTest: Test
    var currentQuestionPosition by Delegates.notNull<Int>()


    var state: StateFlow<TestState> = combine(
        testInteractor.receivePersonalTest(testName),
        loading,
        isFinished,
    ) { test, isFinished, loading ->
        when {
            loading -> TestState.Base(ScreenStates.Loading)
            test.questions.isNullOrEmpty() -> TestState.Base(ScreenStates.Empty)
            isFinished -> TestState.ShowResults(test.interpretation!!)
            else ->{
                TestState.ShowTest(test).also {
                    localTest = test
                    currentQuestionPosition = 0
                }
            }
        }
    }.catch {
        println("ME HERE ${it.message}")
        emit(TestState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Base(ScreenStates.Loading))


    fun toNextQuestion(): Question =
        localTest.toNextQuestion()


    private fun Test.toNextQuestion(): Question{
        return if (currentQuestionPosition == questions!!.size - 1){
            questions!![currentQuestionPosition]
        } else {
            currentQuestionPosition++
            questions!![currentQuestionPosition]
        }
    }

    fun updateQuestionPosition() =
        if (currentQuestionPosition > 0){
            currentQuestionPosition--
        } else {
            currentQuestionPosition
        }


    @AssistedFactory
    interface TestScreenViewModelFactory {
        fun create(
            @Assisted("testName") testName: String,
        ): TestScreenViewModel
    }

}

