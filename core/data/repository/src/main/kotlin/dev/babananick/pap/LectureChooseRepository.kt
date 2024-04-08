package dev.babananick.pap

import dev.babananick.pap.modules.LectureModule
import kotlinx.coroutines.flow.Flow

interface LectureChooseRepository {
    fun receiveLectureModules(): Flow<List<LectureModule>>

}