package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Named

class TestScreenViewModel @AssistedInject constructor(
    testInteractor: PersonalTestInteractor,
    @Assisted private val test: String
) : ViewModel() {
    private val loading = MutableStateFlow(false)
    private val isFinished = MutableStateFlow(false)

    val state = combine(
        loading,
        isFinished,
        testInteractor.receivePersonalTest(test),
    ) { loading, isFinished, test ->
        when {
            loading -> TestState.Base(ScreenStates.Loading)
            test.questions.isEmpty()-> TestState.Base(ScreenStates.Empty)
            isFinished -> TestState.ShowResults(test.interpretation)
            else -> TestState.ShowTest(test)
        }
    }.catch {
        emit(TestState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestState.Base(ScreenStates.Loading))

    @AssistedFactory
    interface Factory {
        fun create(@Named("test") test: String): TestScreenViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: Factory,
            test: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(test) as T
            }
        }
    }
}

