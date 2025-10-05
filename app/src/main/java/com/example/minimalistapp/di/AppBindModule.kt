package com.example.minimalistapp.di

import com.example.minimalistapp.data.repository.AppRepositoryImpl
import com.example.minimalistapp.domain.repository.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {

    @Binds      // Auto maps impl constructor to repo, whenever repo is called (no manual work needed like in @Provides)
    abstract fun bindAppRepository(
        impl: AppRepositoryImpl
    ): AppRepository
}