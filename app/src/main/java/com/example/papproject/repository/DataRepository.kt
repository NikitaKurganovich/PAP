package com.example.papproject.repository

import com.example.papproject.datasource.DataSource
import com.example.papproject.datasource.FirebaseDataSource
import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

object DataRepository: Repository {
    private val firebaseSource: DataSource = FirebaseDataSource

    private val modulesFirebase = firebaseSource.getModules()

    private val _modules = MutableStateFlow<List<LectureModule>>(emptyList())
    private val modules: Flow<List<LectureModule>> = _modules.asStateFlow()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            modulesFirebase.collect { modules ->
                _modules.emit(modules)
            }
        }
    }
    override fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>> {
        return firebaseSource.getLectureQuestions(moduleName, testName)
    }

    override fun getModules(): Flow<List<LectureModule>> {
        return modules
    }


}
