package com.example.papproject.repository

import com.example.papproject.datasource.DataSource
import com.example.papproject.datasource.FirebaseDataSource
import com.example.papproject.model.EmotionalIntelligenceQuestion
import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

object DataRepository : Repository {
    private val firebaseSource: DataSource = FirebaseDataSource

    private val modulesFirebase = firebaseSource.getModules()
    private val testsFirebase = firebaseSource.getTests()

    private val _modules = MutableStateFlow<List<LectureModule>>(emptyList())
    private val modules: Flow<List<LectureModule>> = _modules.asStateFlow()

    private val _tests = MutableStateFlow<List<String>>(emptyList())
    private val tests: Flow<List<String>> = _tests.asStateFlow()
    init {
        CoroutineScope(Dispatchers.IO).launch {
            testsFirebase.collect { tests ->
                _tests.emit(tests)
            }
            modulesFirebase.collect { modules ->
                _modules.emit(modules)
            }

        }
    }

    override fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>> {
        return firebaseSource.getLectureQuestions(moduleName, testName)
    }

    override fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>> {
        return firebaseSource.getPersonalTestQuestions(testName)
    }

    override fun getModules(): Flow<List<LectureModule>> {
        return modules
    }

    override fun getTests(): Flow<List<String>> {
        return tests
    }

    override fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String> {
        return firebaseSource.getLectureTheory(moduleName, submoduleName)
    }

    override fun upsertResults(moduleName: String, submoduleName: String, score: Int) {
        firebaseSource.upsertResults(moduleName, submoduleName, score)
    }

    override fun upsertPersonalResults(testName: String, results: Map<String, Int>) {
        firebaseSource.upsertPersonalResults(testName,results)
    }

    override fun getUserResults(callback: (Map<String, Int>) -> Unit) {
        firebaseSource.getUserResults(callback)
    }


}
