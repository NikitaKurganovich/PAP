package dev.babananick.pap

import kotlinx.coroutines.flow.Flow

interface LecturesRepository {
    fun receiveLectureTheory(moduleName: String, submoduleName: String): Flow<String>

}