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

    private val _characters = MutableLiveData<List<RickAndMortyCharacter>>()
    val characters: LiveData<List<RickAndMortyCharacter>> get() = _characters

    private val _error = MutableLiveData<Error>()
    val error: LiveData<Error> get() = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading


    fun fetchCharacters(page: Int) {
        _loading.postValue(true)
        viewModelScope.launch {
            val result = repository.getCharacters()
            if (result.isSuccessful) {
                result.body()?.let {
                    _characters.postValue(it.results)
                }
            } else {
                _error.postValue(Error())
            }
            _loading.postValue(false)
        }
    }
}