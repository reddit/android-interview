package com.reddit.rickandmortyapp.network

// link to API Documentation: https://rickandmortyapi.com/documentation#character

data class RickAndMortyCharactersResponse(val results: List<RickAndMortyCharacter>)

data class RickAndMortyCharacter(val name: String)