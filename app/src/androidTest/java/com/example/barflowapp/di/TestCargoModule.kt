package com.example.barflowapp.di

import com.example.barflowapp.data.datasource.fake.FakeDataSource
import com.example.barflowapp.domain.usecase.FakeCargoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CargoModule::class],
)
object TestCargoModule {
    @Provides
    @Singleton
    fun provideFakeCargoDataSource(): FakeCargoRepository = FakeCargoRepository()

    @Provides
    @Singleton
    fun provideFakeDataSource(): FakeDataSource = FakeDataSource()
}