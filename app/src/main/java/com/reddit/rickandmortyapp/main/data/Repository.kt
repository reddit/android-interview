package com.reddit.rickandmortyapp.main.data

import com.reddit.rickandmortyapp.network.RickAndMortyApi
import com.reddit.rickandmortyapp.network.RickAndMortyCharactersResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: RickAndMortyApi
) {
    suspend fun getCharacters(page:Int = 1): Response<RickAndMortyCharactersResponse> {
        return apiService.fetchCharactersCoroutine(page)
    }
}
