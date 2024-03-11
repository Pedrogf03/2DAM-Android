/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource.accompanimentMenuItems
import com.example.lunchtray.datasource.DataSource.entreeMenuItems
import com.example.lunchtray.datasource.DataSource.sideDishMenuItems
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// Screen enum
enum class Screen {
    Start,
    Entree,
    SideDish,
    Accompaniment,
    Checkout
}

// AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayAppBar(
    canNavigateBack: Boolean,
    currentScreen: String,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen) },
        modifier = modifier,
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun LunchTrayApp(
    modifier: Modifier = Modifier,
    // Create ViewModel
    viewModel: OrderViewModel = viewModel(),
    // Create Controller and initialization
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        topBar = {
            // AppBar
            LunchTrayAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: Screen.Start.name,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: Navigation host
        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = Screen.Start.name) {
                StartOrderScreen(
                    onStartOrderButtonClicked = { navController.navigate(Screen.Entree.name) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                )
            }
            composable(route = Screen.Entree.name) {
                EntreeMenuScreen(
                    options = entreeMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onNextButtonClicked = { navController.navigate(Screen.SideDish.name) },
                    onSelectionChanged = { viewModel.updateEntree(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                )
            }
            composable(route = Screen.SideDish.name) {
                SideDishMenuScreen(
                    options = sideDishMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onNextButtonClicked = { navController.navigate(Screen.Accompaniment.name) },
                    onSelectionChanged = { viewModel.updateSideDish(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                )
            }
            composable(route = Screen.Accompaniment.name) {
                AccompanimentMenuScreen(
                    options = accompanimentMenuItems,
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onNextButtonClicked = { navController.navigate(Screen.Checkout.name) },
                    onSelectionChanged ={ viewModel.updateAccompaniment(it) },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(innerPadding)
                )
            }
            composable(route = Screen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onNextButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    onCancelButtonClicked = {
                        cancelOrderAndNavigateToStart(viewModel, navController)
                    },
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding(),
                            start = dimensionResource(id = R.dimen.padding_medium),
                            end = dimensionResource(id = R.dimen.padding_medium)
                        )
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: OrderViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(Screen.Start.name, inclusive = false)
}
