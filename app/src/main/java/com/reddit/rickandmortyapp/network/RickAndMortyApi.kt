package com.reddit.rickandmortyapp.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    fun fetchCharacters(): Response<RickAndMortyCharactersResponse>

    @GET("character")
    suspend fun fetchCharactersCoroutine(@Query(value = "page") page: Int): Response<RickAndMortyCharactersResponse>
}