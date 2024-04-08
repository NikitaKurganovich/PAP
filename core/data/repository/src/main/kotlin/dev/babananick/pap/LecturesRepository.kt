package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface LecturesRepository {
    fun receiveLectureTheory(submoduleName: String): Flow<String>

}