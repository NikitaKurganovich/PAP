package dev.babananick.pap.vm

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.babananick.pap.model.LectureQuestion
import dev.babananick.pap.repository.DataRepository
import kotlinx.coroutines.flow.*
import kotlin.properties.Delegates

class LectureScreenViewModel(
    val moduleName: String,
    val submoduleName: String
) : ViewModel() {
    private val repository = DataRepository

    private val questions = repository.getLectureQuestions(moduleName,submoduleName)
    private val questionsLoading = MutableStateFlow(false)
    val isResultsSend = MutableStateFlow(false)
    val isTheoryRead = MutableStateFlow(false)
    var score = 0
    var questionNumber by Delegates.notNull<Int>()
    private val theory = repository.getLectureTheory(moduleName,submoduleName)

    fun collectResults(
        data: List<LectureQuestion>,
        isDialogOnNotFullOpen: MutableState<Boolean>,
        isDialogOnConfirmOpen: MutableState<Boolean>
    ){
        data.forEach {
            if (it.isAnsweredCorrectly) {
                score++
            }
            if (!it.isAnswered) {
                isDialogOnNotFullOpen.value = true
                return
            }
        }
        if (!isDialogOnNotFullOpen.value) {
            isDialogOnConfirmOpen.value = true
        }
    }

    val state = combine(
        theory,
        isTheoryRead,
        questions,
        questionsLoading,
        isResultsSend
    ) {theory,isRead, questions, loading, isSend ->
        questionNumber = questions.size
        when {
            loading -> LectureState.Loading
            theory.isEmpty() || questions.isEmpty() -> LectureState.Empty
            isSend -> LectureState.ShowingResults(score, questionNumber)
            isRead -> LectureState.ShowingQuestions(questions)
            else -> {
                LectureState.ShowingTheory(theory)
            }
        }
    }.catch {
        emit(LectureState.Error(it))
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), LectureState.Loading)


    fun upsertScore(moduleName: String, submoduleName: String, score: Int){
        repository.upsertResults(moduleName, submoduleName, score)
    }

}

class LectureScreenViewModelFactory(
    private val moduleName: String,
    private val submoduleName: String
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LectureScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LectureScreenViewModel(moduleName, submoduleName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

sealed class LectureState{
    object Loading : LectureState()
    data class ShowingTheory(val data: String) : LectureState()
    data class ShowingQuestions(val data: List<LectureQuestion>) : LectureState()
    data class ShowingResults(val score: Int, val questionNumber: Int): LectureState()

    object Empty : LectureState()
    data class Error(val e: Throwable): LectureState()
}