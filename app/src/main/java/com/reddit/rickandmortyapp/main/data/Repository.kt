package com.reddit.rickandmortyapp.main.data

import androidx.lifecycle.MutableLiveData
import com.reddit.rickandmortyapp.network.RickAndMortyApi
import com.reddit.rickandmortyapp.network.RickAndMortyCharacter
import com.reddit.rickandmortyapp.network.RickAndMortyCharactersResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: RickAndMortyApi
) {

    private val data = mutableMapOf<Int, List<RickAndMortyCharacter>>()
    suspend fun getCharacters(page:Int = 1): Response<RickAndMortyCharactersResponse> {
        if(data[page] == null) {
            val pageResult = apiService.fetchCharactersCoroutine(page)
            if(pageResult.isSuccessful) {
                pageResult.body()?.let {
                    data[page] = it.results
                }
            } else {
                return pageResult
            }
        }
        return Response.success(RickAndMortyCharactersResponse(data[page] ?: emptyList()))
    }
}
