package com.example.barflowapp.di

import com.example.barflowapp.data.datasource.fake.FakeDataSource
import com.example.barflowapp.data.repository.CargoRepositoryImpl
import com.example.barflowapp.domain.repository.CargoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CargoInterfaceModule {

    @Binds
    abstract fun bindCargoRepository(impl: CargoRepositoryImpl): CargoRepository
}