package dev.babananick.pap.core.data.repository

import kotlinx.coroutines.flow.Flow

interface LecturesRepository {
    fun receiveLectureTheory(submoduleName: String): Flow<String>

}