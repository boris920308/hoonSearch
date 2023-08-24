package hoon.example.hoonsearch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hoon.example.hoonsearch.data.repository.NaverSearchRepository
import hoon.example.hoonsearch.data.repository.NaverSearchRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindNaverSearchRepository(
        naverSearchRepositoryImpl: NaverSearchRepositoryImpl
    ): NaverSearchRepository
}