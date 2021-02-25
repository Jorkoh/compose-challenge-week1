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

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.androiddevchallenge.MainActivity
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.Gender
import dagger.hilt.android.EntryPointAccessors
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun CatDetail(
    viewModel: CatDetailViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    CatDetailScreen(
        catDetailUIState = viewModel.catDetailUIState.collectAsState(),
        modifier = modifier,
        onUpButtonPressed = { navController.navigateUp() }
    )
}

@Composable
fun CatDetailScreen(
    catDetailUIState: State<CatDetailUIState>,
    modifier: Modifier = Modifier,
    onUpButtonPressed: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { CatDetailTopBar(catDetailUIState, onUpButtonPressed) },
        floatingActionButton = { CatDetailFAB(catDetailUIState) }
    ) { innerPadding ->
        when (val state = catDetailUIState.value) {
            is CatDetailUIState.Loading -> Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is CatDetailUIState.Error -> Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Text(text = state.message)
            }
            is CatDetailUIState.Success -> CatDetailContent(
                state = state,
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun CatDetailContent(state: CatDetailUIState.Success, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            CoilImage(
                data = state.cat.imageURL,
                contentDescription = "Cat image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth(),
                fadeIn = true
            )
        }
        item {
            Text(
                text = state.cat.story,
                modifier = Modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp),
                style = MaterialTheme.typography.body1
            )
        }
        item {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .height(80.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Breed: ${state.cat.breed}")
                Text(text = "Location: ${state.cat.location}")
                Row {
                    Text(text = "${state.cat.age} y/o")
                    Spacer(modifier = Modifier.width(12.dp))
                    Gender(
                        gender = state.cat.gender,
                        textStyle = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}

@Composable
fun CatDetailFAB(catDetailUIState: State<CatDetailUIState>) {
    if (catDetailUIState.value is CatDetailUIState.Success) {
        FloatingActionButton(onClick = { /*TODO*/ }) {
            Text(
                text = "ADOPT ME!",
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }
    }
}

@Composable
fun CatDetailTopBar(
    catDetailUIState: State<CatDetailUIState>,
    onUpButtonPressed: () -> Unit
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            IconButton(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = { onUpButtonPressed() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.label_back)
                )
            }
            Text(
                text = when (val state = catDetailUIState.value) {
                    is CatDetailUIState.Success -> state.cat.name
                    else -> ""
                },
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun catDetailViewModel(catId: Long): CatDetailViewModel {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        MainActivity.ViewModelFactoryProvider::class.java
    ).catDetailViewModelFactory()

    return viewModel(factory = CatDetailViewModel.provideFactory(factory, catId))
}
