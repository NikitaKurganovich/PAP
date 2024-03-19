package dev.babananick.pap.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.babananick.pap.datasource.*


@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun provideLectureChooseDataSource(lectureChooseDataSourceImpl: LectureChooseDataSourceImpl): LectureChooseDataSource

    @Binds
    fun provideLecturesDataSource(lecturesDataSourceImpl: LecturesDataSourceImpl): LecturesDataSource

    @Binds
    fun provideLectureTestDataSource(lectureTestDataSourceImpl: LectureTestDataSourceImpl): LectureTestDataSource
}