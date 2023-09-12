package com.reddit.rickandmortyapp.main.di

import com.reddit.rickandmortyapp.network.RetryInterceptor
import com.reddit.rickandmortyapp.network.RickAndMortyApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi? {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(moshi: Moshi?): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(RetryInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi?, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RickAndMortyApi {
        return retrofit.create(RickAndMortyApi::class.java)
    }
}