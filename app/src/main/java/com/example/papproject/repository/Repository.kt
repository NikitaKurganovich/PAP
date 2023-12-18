package com.example.papproject.repository

import com.example.papproject.model.LectureModule
import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>>

    fun getModules(): Flow<List<LectureModule>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>
    fun upsertResults(moduleName: String, submoduleName: String, score: Int)
    fun getUserResults(callback: (Map<String, Int>) -> Unit)
}