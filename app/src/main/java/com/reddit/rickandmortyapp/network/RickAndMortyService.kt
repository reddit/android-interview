package com.reddit.rickandmortyapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

class RickAndMortyService {

    private val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api: RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
}

interface RickAndMortyApi {
    @GET("character")
    fun fetchCharacters(): Call<RickAndMortyCharactersResponse>

    @GET("character")
    suspend fun fetchCharactersCoroutine(): RickAndMortyCharactersResponse
}