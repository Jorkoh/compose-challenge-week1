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

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.components.CatListItem
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun Home(
    viewModel: HomeViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    HomeScreen(
        catsUIState = viewModel.catsUIState.collectAsState(),
        modifier = modifier,
        onCatSelected = { catId ->
            navController.navigate("detail/$catId")
        }
    )
}

@Composable
fun HomeScreen(
    catsUIState: State<CatsUIState>,
    modifier: Modifier = Modifier,
    onCatSelected: (Long) -> Unit
) {
    LazyColumn(modifier.fillMaxSize()) {
        item { Spacer(modifier = Modifier.statusBarsPadding()) }
        item { Title("Welcome, let's find you a new friend!") }
        when (val state = catsUIState.value) {
            is CatsUIState.Loading -> item {
                Box(Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }
            is CatsUIState.Error -> item { Text(state.message) }
            is CatsUIState.Success -> {
                items(items = state.cats, key = { it.id }) { cat ->
                    CatListItem(cat = cat, onCatSelected = onCatSelected)
                }
            }
        }
    }
}

@Composable
fun Title(value: String) {
    Text(
        text = value,
        style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(start = 24.dp, bottom = 12.dp, top = 24.dp)
    )
}
