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
package com.example.androiddevchallenge.data

import androidx.annotation.WorkerThread
import com.example.androiddevchallenge.data.database.CatDao
import com.example.androiddevchallenge.data.models.Cat
import com.example.androiddevchallenge.data.network.CatResponse
import com.example.androiddevchallenge.data.network.CatService
import com.example.androiddevchallenge.data.utils.CatStuff
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class CatRepository @Inject constructor(
    private val catService: CatService,
    private val catDao: CatDao
) {
    @WorkerThread
    fun fetchCats(
        onStart: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        Timber.d("Fetching cats")
        catDao.getCats().distinctUntilChanged().collect { cats ->
            if (cats.isEmpty()) {
                catService.fetchCats().suspendOnSuccess {
                    data?.let { catsResponse ->
                        val newCats = catsResponse.map {
                            it.mapToCat()
                        }
                        catDao.insertCats(newCats)
                    }
                }.onError {
                    onError("Request failed with code ${statusCode.code}: $raw")
                }.onException {
                    onError("Error while requesting: $message")
                }
            } else {
                emit(cats)
            }
        }
    }.onStart { onStart() }.flowOn(Dispatchers.IO)

    @WorkerThread
    fun fetchCat(
        catId: Long,
        onStart: () -> Unit,
        onError: (String) -> Unit
    ) = catDao.getCat(catId)
        .catch { onError(it.message ?: "Error while fetching cat") }
        .onStart { onStart() }
        .flowOn(Dispatchers.IO)
}

private fun CatResponse.mapToCat() = Cat(
    apiId = id,
    imageURL = imageURL,
    name = CatStuff.randomName(),
    age = CatStuff.randomAge(),
    gender = CatStuff.randomGender(),
    breed = CatStuff.randomBreed(),
    location = CatStuff.randomLocation(),
    story = CatStuff.randomStory()
)
