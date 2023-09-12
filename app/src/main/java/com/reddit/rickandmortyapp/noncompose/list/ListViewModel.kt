package com.reddit.rickandmortyapp.noncompose.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reddit.rickandmortyapp.main.data.Repository
import com.reddit.rickandmortyapp.network.RickAndMortyCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject


@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var pageSize: Int = 0
    var currentPage = 0
    var isPageLoading: Boolean = false

    private val _characters = MutableLiveData<List<RickAndMortyCharacter>>()
    val characters: LiveData<List<RickAndMortyCharacter>> get() = _characters

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _moreCharacters = MutableLiveData<List<RickAndMortyCharacter>>()
    val moreCharacters: LiveData<List<RickAndMortyCharacter>> get() = _moreCharacters


    fun fetchCharacters() {
        _loading.postValue(true)
        viewModelScope.launch {
            val result = repository.getCharacters()
            if (result.isSuccessful) {
                result.body()?.let {
                    pageSize = it.results.size
                    currentPage = 1
                    _characters.postValue(it.results)
                }
            } else {
                _error.postValue(Error())
            }
            _loading.postValue(false)
        }
    }

    fun nextPage() {
        isPageLoading = true
        viewModelScope.launch {
            currentPage += 1
            val result = repository.getCharacters(currentPage)
            if (result.isSuccessful) {
                result.body()?.let {
                    _moreCharacters.postValue(it.results)
                }
            } else {
                _error.postValue(Error())
            }
            isPageLoading = false
        }
    }
}