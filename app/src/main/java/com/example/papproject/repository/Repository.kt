package com.example.papproject.repository

import com.example.papproject.model.EmotionalIntelligenceQuestion
import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>
    fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>>
    fun getModules(): Flow<List<LectureModule>>
    fun getTests(): Flow<List<String>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>
    fun getUserResults(callback: (HashMap<String, HashMap<String, Int>>) -> Unit)
    fun upsertResults(moduleName: String, submoduleName: String, score: Int)
    fun upsertPersonalResults(testName: String, results: Map<String, Int>)

}