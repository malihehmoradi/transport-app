package com.example.barflowapp.di

import com.example.barflowapp.data.datasource.fake.FakeDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CargoModule {
    @Provides
    fun provideFakeDataSource(): FakeDataSource = FakeDataSource()
}