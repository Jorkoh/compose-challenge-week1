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
package com.example.androiddevchallenge.data.network

import com.example.androiddevchallenge.BuildConfig
import com.example.androiddevchallenge.data.utils.HttpRequestInterceptor
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {
    @GET("images/search?size=med")
    suspend fun fetchCats(
        @Query("limit") count: Int = 30
    ): ApiResponse<List<CatResponse>>

    companion object {
        private const val BASE_URL = "https://api.thecatapi.com/v1/"

        fun create(): CatService {
            return Retrofit.Builder()
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(
                            Interceptor { chain ->
                                // Add the API token to the request
                                val requestWithToken = chain.request().newBuilder()
                                    .addHeader("x-api-key", BuildConfig.CAT_API_TOKEN)
                                    .build()
                                chain.proceed(requestWithToken)
                            }
                        )
                        .addInterceptor(HttpRequestInterceptor())
                        .build()
                )
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory())
                .build()
                .create(CatService::class.java)
        }
    }
}
