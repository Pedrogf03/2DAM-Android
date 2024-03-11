package com.example.valorantapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.valorantapp.R


enum class Screen {
    Start,
    Roles,
    Agentes,
    Agente,
    Tipos,
    Arsenal,
    Arma
}

// AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorantAppBar(
    canNavigateBack: Boolean,
    currentScreen: String,
    navigateUp: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                    if (currentScreen != Screen.Start.name) {
                        Spacer(modifier = Modifier.width(15.dp))
                        Text(
                            text = currentScreen,
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.padding(
                                top = 5.dp
                            )
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
                if (currentScreen != Screen.Start.name) {
                    IconButton(
                        onClick = onCancelButtonClicked,
                        modifier = Modifier.align(Alignment.CenterEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if(canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValorantApp(
    modifier: Modifier = Modifier,
    viewModel: ValorantViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold (
        topBar = {
            ValorantAppBar(
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: Screen.Start.name,
                navigateUp = { navController.navigateUp() },
                onCancelButtonClicked = {
                    viewModel.reset()
                    navController.popBackStack(Screen.Start.name, inclusive = false)
                }
            )
        }
    ){ innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        val image = uiState.image

        NavHost(
            navController = navController,
            startDestination = Screen.Start.name,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(route = Screen.Start.name) {
                StartValorantScreen(
                    onRolesButtonClicked = { navController.navigate(Screen.Roles.name) },
                    onArsenalButtonClicked = { navController.navigate(Screen.Tipos.name) },
                    image = image
                )
            }

            composable(route = Screen.Roles.name) {
                RolesValorantScreen(
                    onRoleButtonClicked = {
                        viewModel.updateAgentes(it)
                        navController.navigate(Screen.Agentes.name)
                    },
                    image = image
                )
            }

            composable(route = Screen.Agentes.name) {
                AgentesValorantScreen(
                    agentes = uiState.agentes,
                    onAgenteButtonClicked = {
                        viewModel.updateAgente(it)
                        navController.navigate(Screen.Agente.name)
                    },
                    image = image
                )
            }

            composable(route = Screen.Agente.name) {
                AgenteValorantScreen(
                    agente = uiState.agente,
                    image = image
                )
            }

            composable(route = Screen.Tipos.name) {
                TiposValorantScreen(
                    onTiposButtonClicked = {
                        viewModel.updateArmas(it)
                        navController.navigate(Screen.Arsenal.name)
                    },
                    image = image
                )
            }

            composable(route = Screen.Arsenal.name) {
                ArsenalValorantScreen(
                    armas = uiState.armas,
                    onArmasButtonClicked = {
                        viewModel.updateArma(it)
                        navController.navigate(Screen.Arma.name)
                    },
                    image = image
                )
            }

            composable(route = Screen.Arma.name) {
                ArmaValorantScreen(
                    arma = uiState.arma,
                    image = image
                )
            }

        }

    }

}

@Preview
@Composable
fun Preview() {
    ValorantApp()
}