package com.example.papproject.datasource

import com.example.papproject.model.LectureQuestion
import kotlinx.coroutines.flow.Flow
import java.util.*

interface DataSource {
    fun getLectureQuestions(moduleName: String, testName: String): Flow<List<LectureQuestion>>


}