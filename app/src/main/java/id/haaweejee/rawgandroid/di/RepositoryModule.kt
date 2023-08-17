package id.haaweejee.rawgandroid.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.haaweejee.rawgandroid.data.repository.RawgRepositoryImpl
import id.haaweejee.rawgandroid.domain.repository.RawgRepository

@Module(includes = [KtorModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideGameRepository(repo: RawgRepositoryImpl): RawgRepository
}