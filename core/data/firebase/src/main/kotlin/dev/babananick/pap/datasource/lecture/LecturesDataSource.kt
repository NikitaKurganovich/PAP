package dev.babananick.pap.datasource.lecture

import kotlinx.coroutines.flow.Flow

interface LecturesDataSource {
    fun receiveLectureTheory(moduleName: String, submoduleName: String): Flow<String>

}