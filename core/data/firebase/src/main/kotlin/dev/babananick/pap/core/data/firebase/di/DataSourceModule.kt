package dev.babananick.pap.core.data.firebase.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.babananick.pap.core.data.firebase.datasource.lecture.LectureChooseDataSource
import dev.babananick.pap.core.data.firebase.datasource.lecture.LectureChooseDataSourceImpl
import dev.babananick.pap.core.data.firebase.datasource.lecture.LecturesDataSource
import dev.babananick.pap.core.data.firebase.datasource.lecture.LecturesDataSourceImpl
import dev.babananick.pap.core.data.firebase.datasource.tests.*


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideLectureChooseDataSource(lectureChooseDataSourceImpl: LectureChooseDataSourceImpl): LectureChooseDataSource

    @Binds
    fun provideLecturesDataSource(lecturesDataSourceImpl: LecturesDataSourceImpl): LecturesDataSource

    @Binds
    fun provideLectureTestDataSource(lectureTestDataSourceImpl: LectureTestDataSourceImpl): LectureTestDataSource

    @Binds
    fun provideTestChooseDataSource(personalTestChooseDataSourceImpl: PersonalTestChooseDataSourceImpl): PersonalTestChooseDataSource

    @Binds
    fun provideTestDataSource(personalTestDataSource: PersonalTestDataSourceImpl): PersonalTestDataSource
}