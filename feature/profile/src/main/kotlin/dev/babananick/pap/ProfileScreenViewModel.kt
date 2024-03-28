package dev.babananick.pap

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.babananick.pap.repository.DataRepository
import kotlinx.coroutines.flow.*

class ProfileScreenViewModel :ViewModel(){
    private var _results = hashMapOf<String, HashMap<String, Int>>()
    private val repository = DataRepository
    private val loading = MutableStateFlow(false)
    private val results = MutableStateFlow(getResults())

    val state = combine(
        results,
        loading
    ) {data, loading ->
        when {
            loading -> ProfileState.Base(ScreenStates.Loading)
            data.isEmpty() -> ProfileState.Base(ScreenStates.Empty)
            else -> ProfileState.ShowingResults(data)
        }
    }.catch {
        emit(ProfileState.Base(ScreenStates.Error(it)))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), ProfileState.Base(ScreenStates.Loading))

    private fun getResults(): HashMap<String, HashMap<String, Int>> {
        repository.getUserResults {
            _results = it
            results.update { _results }
        }
        return _results
    }


}
