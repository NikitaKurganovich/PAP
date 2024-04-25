package dev.babananick.pap.core.data.repository.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.babananick.pap.core.data.repository.*


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideLectureChooseRepository(lectureChooseRepositoryImpl: LectureChooseRepositoryImpl): LectureChooseRepository

    @Binds
    fun provideLecturesRepository(lecturesRepositoryImpl: LecturesRepositoryImpl): LecturesRepository

    @Binds
    fun provideLectureTestRepository(lectureTestRepositoryImpl: LectureTestRepositoryImpl): LectureTestRepository

    @Binds
    fun providePersonalTestRepository(personalTestRepository: PersonalTestRepositoryImpl): PersonalTestRepository

    @Binds
    fun providePersonalTestChooseRepository(personalTestChooseRepositoryImpl: PersonalTestChooseRepositoryImpl): PersonalTestChooseRepository
}