package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class TestChooseViewModel @Inject constructor(
    availableTests: PersonalTestChooseInteractor
): ViewModel(){
    private val testList = availableTests.receivePersonalTestNames()
    private val loading = MutableStateFlow(true)

    val state = combine(
        testList,
        loading
    ) {tests, loading ->
        when {
            loading -> TestChooseState.Base(ScreenStates.Loading)
            tests.isEmpty() -> TestChooseState.Base(ScreenStates.Empty)
            else -> TestChooseState.ShowingTestsChoose(tests)
        }
    }.catch {
        emit(TestChooseState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestChooseState.Base(ScreenStates.Loading))
}