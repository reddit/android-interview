package com.reddit.rickandmortyapp.main.di

import com.reddit.rickandmortyapp.main.data.Repository
import com.reddit.rickandmortyapp.network.RickAndMortyApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideMoshi(rickAndMortyApi: RickAndMortyApi): Repository {
        return Repository(rickAndMortyApi)
    }
}
