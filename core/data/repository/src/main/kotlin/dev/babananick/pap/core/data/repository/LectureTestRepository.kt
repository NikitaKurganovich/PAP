package dev.babananick.pap.core.data.repository

import kotlinx.coroutines.flow.Flow

interface LectureTestRepository {
    fun receiveLectureQuestions( submoduleName: String): Flow<dev.babananick.pap.core.model.tests.TestWithRightAnswer>

}