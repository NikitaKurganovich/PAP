package dev.babananick.pap.core.data.firebase.datasource.lecture

import kotlinx.coroutines.flow.Flow

interface LecturesDataSource {
    fun receiveLectureTheory(submoduleName: String): Flow<String>

}