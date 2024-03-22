package dev.babananick.pap

import androidx.annotation.AnyThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val modulesInteractor: LectureChooseInteractor
) : ViewModel() {
    private val loading = MutableStateFlow(false)
    private var _results = hashMapOf<String, HashMap<String, Int>>()


    val state = combine(
        lectureModules(),
        loading
    ) {modules, loading ->
        when {
            loading -> HomeState.Loading
            modules.isEmpty() -> HomeState.Empty
            else -> HomeState.ShowingModules(modules)
        }
    }.catch {
        emit(HomeState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), HomeState.Loading)

    @AnyThread
    private fun lectureModules(): Flow<List<LectureModule>> =
        modulesInteractor.receiveLectureModules()




    fun isResultsExist(
        moduleName: String,
        submoduleName: String,
    ): Boolean {
        if (_results.isEmpty()) return false
        val map = _results[moduleName] as HashMap<String, Int>
        return map.containsKey(submoduleName)
    }
}


sealed class HomeState {
    object Loading : HomeState()
    data class ShowingModules(val data: List<LectureModule>) : HomeState()
    object Empty : HomeState()
    data class Error(val e: Throwable) : HomeState()
}