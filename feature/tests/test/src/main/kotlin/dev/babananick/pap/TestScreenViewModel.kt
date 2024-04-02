package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*

@HiltViewModel(assistedFactory = TestScreenViewModel.TestScreenViewModelFactory::class)
class TestScreenViewModel @AssistedInject constructor(
    testInteractor: PersonalTestInteractor,
    @Assisted private val test: String
) : ViewModel() {
    private val loading = MutableStateFlow(false)
    private val isFinished = MutableStateFlow(false)


    var state: StateFlow<TestState> =
        combine(
            testInteractor.receivePersonalTest(test),
            loading,
            isFinished,
        ) { test, isFinished, loading ->
            when {
                loading -> TestState.Base(ScreenStates.Loading)
                test.questions.isEmpty() -> TestState.Base(ScreenStates.Empty)
                isFinished -> TestState.ShowResults(test.interpretation)
                else -> TestState.ShowTest(test)
            }
        }.catch {
            emit(TestState.Base(ScreenStates.Error(it)))
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Base(ScreenStates.Loading))


@AssistedFactory
interface TestScreenViewModelFactory {
    fun create(test: String): TestScreenViewModel
}

}

