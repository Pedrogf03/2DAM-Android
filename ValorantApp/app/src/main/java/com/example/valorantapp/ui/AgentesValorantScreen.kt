package com.example.valorantapp.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.valorantapp.model.Agente

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AgentesValorantScreen(
    agentes: List<Agente>?,
    modifier: Modifier = Modifier,
    onAgenteButtonClicked: (Agente) -> Unit,
    @DrawableRes image: Int
) {

    val visibleState = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)
        ),
        exit = fadeOut(),
        modifier = Modifier
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.fillMaxSize()
                )
                LazyColumn {
                    itemsIndexed(agentes.orEmpty()) { index, agente ->
                        AgentesCard(
                            agente = agente,
                            onAgenteButtonClicked = onAgenteButtonClicked,
                            modifier = Modifier.animateEnterExit(
                                enter = slideInVertically(
                                    animationSpec = spring(
                                        stiffness = Spring.StiffnessVeryLow,
                                        dampingRatio = Spring.DampingRatioLowBouncy
                                    ),
                                    initialOffsetY = { it * (index + 1) }
                                )
                            )
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun AgentesCard(
    modifier: Modifier = Modifier,
    agente: Agente,
    onAgenteButtonClicked: (Agente) -> Unit
) {

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = agente.imagen),
                contentDescription = null,
                modifier = Modifier
                    .padding(20.dp)
                    .size(30.dp)
            )
            Text(
                text = stringResource(id = agente.nombre),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 5.dp)
            )
            IconButton(
                onClick = { onAgenteButtonClicked(agente) }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
    }

}


