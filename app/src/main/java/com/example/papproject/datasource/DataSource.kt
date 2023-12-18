package com.example.papproject.datasource

import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface DataSource {
    val lectureModules: Flow<List<LectureModule>>
    fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>
    fun getModules(): Flow<List<LectureModule>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>

}