package dev.babananick.pap.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.babananick.pap.*


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideLectureChooseRepository(lectureChooseRepositoryImpl: LectureChooseRepositoryImpl): LectureChooseRepository

    @Binds
    fun provideLecturesRepository(lecturesRepositoryImpl: LecturesRepository): LecturesRepository

    @Binds
    fun provideLectureTestRepository(lectureTestRepositoryImpl: LectureTestRepository): LectureTestRepository
}