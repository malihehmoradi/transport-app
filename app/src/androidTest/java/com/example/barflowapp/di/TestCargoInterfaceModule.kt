package com.example.barflowapp.di

import com.example.barflowapp.domain.usecase.FakeCargoRepository
import com.example.barflowapp.domain.usecase.FakeGetCargoUseCase
import com.example.barflowapp.domain.usecase.GetCargoUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Inject
import javax.inject.Singleton

//@Module
//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [CargoInterfaceModule::class]
//)
//abstract class TestCargoInterfaceModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindFakeGetCargoUseCase(
//        fakeGetCargoUseCase: FakeGetCargoUseCase
//    ): FakeGetCargoUseCase
//
//    @Binds
//    @Singleton
//    abstract fun bindFakeCargoRepository(): FakeCargoRepository
//
//
//
//
//}

// Define your interface and fake implementation (example)
interface CargoRepository {
    fun getCargo(): String
}

// This should be your fake implementation for testing
// It MUST implement CargoRepository
@Singleton // Or appropriate scope if needed
class FakeCargoRepository @Inject constructor() : CargoRepository {
    override fun getCargo(): String {
        return "Fake Cargo Data"
    }
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CargoInterfaceModule::class]
)
abstract class TestCargoInterfaceModule {

    @Binds
    abstract fun bindCargoRepository(
        fakeCargoRepository: FakeCargoRepository // Exactly ONE parameter, the implementation
    ): FakeCargoRepository // Return type is the interface

    @Binds
    @Singleton // Or appropriate scope
    abstract fun bindGetCargoUseCase(
        fakeCargoRepository: FakeCargoRepository
    ): FakeGetCargoUseCase
}