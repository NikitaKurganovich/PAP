package dev.babananick.pap

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.NopCollector.emit
import javax.inject.Inject

@HiltViewModel
class TestScreenResultViewModel @Inject
constructor() :
    ViewModel() {

    private val loading = MutableStateFlow(false)
    val isChosen = MutableStateFlow(false)

    private var _results = hashMapOf<String, Int>()
    var userResults = MutableStateFlow(getResults())

    val state = combine(
        loading,
        ,
    ) { loading, tests ->
        when {
            loading -> TestResultState.Base(ScreenStates.Loading)
            tests.isEmpty() -> TestResultState.Base(ScreenStates.Empty)
            else -> TestResultState.ShowingResults(userResults.value)
        }
    }.catch {
        emit(TestResultState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), TestResultState.Base(ScreenStates.Loading))

}

