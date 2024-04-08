package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*

class LectureScreenViewModel(
    val moduleName: String,
    val submoduleName: String,
    private val theory: String
) : ViewModel() {

    val loading = MutableStateFlow(true)
    val theoryFlow = MutableStateFlow(theory)

    val state = combine(
        theoryFlow,
        loading,
    ) {theory, loading ->
        when {
            loading -> LectureState.Base(ScreenStates.Loading)
            theory.isEmpty() -> LectureState.Base(ScreenStates.Empty)
            else -> {
                LectureState.ShowingTheory(theory)
            }
        }
    }.catch {
        emit(LectureState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LectureState.Base(ScreenStates.Loading))
}

class LectureScreenViewModelFactory(
    private val moduleName: String,
    private val submoduleName: String,
    private val theory: String
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LectureScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LectureScreenViewModel(moduleName, submoduleName, theory) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

