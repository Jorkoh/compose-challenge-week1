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
package com.example.androiddevchallenge.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.screens.detail.CatDetail
import com.example.androiddevchallenge.ui.screens.detail.catDetailViewModel
import com.example.androiddevchallenge.ui.screens.home.Home
import com.example.androiddevchallenge.ui.theme.CatsAppTheme
import com.example.androiddevchallenge.ui.utils.LocalSysUiController
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

@Composable
fun CatsApp() {
    val navController = rememberNavController()

    LocalSysUiController.current.setSystemBarsColor(
        color = MaterialTheme.colors.surface.copy(alpha = 0.85f)
    )

    ProvideWindowInsets {
        CatsAppTheme {
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    Home(it.hiltNavGraphViewModel(), navController)
                }
                composable(
                    route = "detail/{catId}",
                    arguments = listOf(
                        navArgument("catId") {
                            type = NavType.LongType
                        }
                    )
                ) { backStackEntry ->
                    val catId = requireNotNull(
                        backStackEntry.arguments?.getLong("catId")
                    )
                    CatDetail(catDetailViewModel(catId), navController)
                }
            }
        }
    }
}

// https://kotlinlang.slack.com/archives/CJLTWPH7S/p1604071670473700?thread_ts=1604043017.440100&cid=CJLTWPH7S
@Composable
inline fun <reified VM : ViewModel> NavBackStackEntry.hiltNavGraphViewModel(): VM {
    val viewModelFactory = HiltViewModelFactory(LocalContext.current, this)
    return ViewModelProvider(this, viewModelFactory).get(VM::class.java)
}
