package com.example.papproject.datasource

import com.example.papproject.model.EmotionalIntelligenceQuestion
import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface DataSource {
    val lectureModules: Flow<List<LectureModule>>
    val personalTests: Flow<List<String>>
    fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>
    fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>>
    fun getModules(): Flow<List<LectureModule>>
    fun getTests(): Flow<List<String>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>
    fun getUserResults(callback: (Map<String, Int>) -> Unit)
    fun upsertResults(moduleName: String, submoduleName: String, score: Int)
    fun upsertPersonalResults(testName: String, results: Map<String, Int>)
}