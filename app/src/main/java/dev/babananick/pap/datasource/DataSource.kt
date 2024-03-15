package dev.babananick.pap.datasource

import dev.babananick.pap.model.EmotionalIntelligenceQuestion
import dev.babananick.pap.model.LectureModule
import dev.babananick.pap.model.LectureQuestion
import kotlinx.coroutines.flow.Flow

interface DataSource {
    val lectureModules: Flow<List<LectureModule>>
    val personalTests: Flow<List<String>>
    fun getLectureQuestions(moduleName: String, submoduleName: String): Flow<List<LectureQuestion>>
    fun getPersonalTestQuestions(testName: String): Flow<List<EmotionalIntelligenceQuestion>>
    fun getModules(): Flow<List<LectureModule>>
    fun getTests(): Flow<List<String>>
    fun getLectureTheory(moduleName: String, submoduleName: String): Flow<String>
    fun getUserResults(callback: (HashMap<String, HashMap<String, Int>>) -> Unit)
    fun upsertResults(moduleName: String, submoduleName: String, score: Int)
    fun upsertPersonalResults(testName: String, results: HashMap<String,Int>)
}