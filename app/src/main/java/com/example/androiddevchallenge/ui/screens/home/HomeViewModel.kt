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
package com.example.androiddevchallenge.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.CatRepository
import com.example.androiddevchallenge.data.models.Cat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catRepository: CatRepository
) : ViewModel() {
    private val _catsUIState = MutableStateFlow<CatsUIState>(CatsUIState.Loading)
    val catsUIState: StateFlow<CatsUIState> = _catsUIState

    init {
        viewModelScope.launch {
            getCats()
        }
    }

    private suspend fun getCats() {
        catRepository.fetchCats(
            onStart = { _catsUIState.value = CatsUIState.Loading },
            onError = { _catsUIState.value = CatsUIState.Error(it) }
        ).collect { cats ->
            _catsUIState.value = CatsUIState.Success(cats)
        }
    }
}

sealed class CatsUIState {
    object Loading : CatsUIState()
    data class Success(val cats: List<Cat>) : CatsUIState()
    data class Error(val message: String) : CatsUIState()
}
