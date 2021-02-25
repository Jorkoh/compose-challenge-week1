/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.data.models.Cat
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CatDetailViewModel @AssistedInject constructor(
    private val catRepository: CatRepository,
    @Assisted private val catId: Long
) : ViewModel() {

    private val _catDetailUIState = MutableStateFlow<CatDetailUIState>(CatDetailUIState.Loading)
    val catDetailUIState: StateFlow<CatDetailUIState> = _catDetailUIState

    init {
        viewModelScope.launch {
            getCat(catId)
        }
    }

    private suspend fun getCat(catId: Long) {
        catRepository.fetchCat(
            catId = catId,
            onStart = {
                _catDetailUIState.value = CatDetailUIState.Loading
            },
            onError = { message ->
                _catDetailUIState.value = CatDetailUIState.Error(message)
            }
        ).collect { cat ->
            _catDetailUIState.value = CatDetailUIState.Success(cat)
        }
    }

    companion object {
        fun provideFactory(
            assistedFactory: CatDetailViewModelFactory,
            catId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(catId) as T
            }
        }
    }
}

sealed class CatDetailUIState() {
    object Loading : CatDetailUIState()
    class Success(val cat: Cat) : CatDetailUIState()
    class Error(val message: String) : CatDetailUIState()
}

@AssistedFactory
interface CatDetailViewModelFactory {
    fun create(catId: Long): CatDetailViewModel
}
