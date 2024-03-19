package dev.babananick.pap.repository

import dev.babananick.pap.EmotionalIntelligenceQuestion
import dev.babananick.pap.LectureModule
import dev.babananick.pap.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>
    fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>>
    fun getModules(): Flow<List<LectureModule>>
    fun getTests(): Flow<List<String>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>
    fun getUserResults(callback: (HashMap<String, HashMap<String, Int>>) -> Unit)
    fun upsertResults(moduleName: String, submoduleName: String, score: Int)
    fun upsertPersonalResults(testName: String, results: HashMap<String,Int>)

}