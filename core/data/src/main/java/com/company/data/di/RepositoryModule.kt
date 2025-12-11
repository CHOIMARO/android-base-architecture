package com.company.data.di

import com.company.data.repository.ExampleRepositoryImpl
import com.company.domain.repository.ExampleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindExampleRepository(
        exampleRepositoryImpl: ExampleRepositoryImpl
    ): ExampleRepository
}