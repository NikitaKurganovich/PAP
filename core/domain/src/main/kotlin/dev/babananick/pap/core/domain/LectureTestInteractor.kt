package dev.babananick.pap.core.domain

import dev.babananick.pap.core.data.repository.LectureTestRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LectureTestInteractor @Inject constructor(
    private val lectures: LectureTestRepository
) {
     fun receiveLectureTest(submoduleName: String): Flow<dev.babananick.pap.core.model.tests.TestWithRightAnswer> {
        return lectures.receiveLectureQuestions( submoduleName)
    }
}