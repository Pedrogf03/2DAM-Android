package com.example.valorantapp.ui

import androidx.lifecycle.ViewModel
import com.example.valorantapp.model.Agente
import com.example.valorantapp.model.Arma
import com.example.valorantapp.model.ValorantUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ValorantViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ValorantUiState())
    val uiState: StateFlow<ValorantUiState> = _uiState.asStateFlow()

    fun updateAgentes(agentes: List<Agente>) {
        _uiState.update { currentState ->
            currentState.copy(agentes = agentes)
        }
    }

    fun updateAgente(agente: Agente) {
        _uiState.update { currentState ->
            currentState.copy(agente = agente)
        }
    }

    fun updateArmas(armas: List<Arma>) {
        _uiState.update { currentState ->
            currentState.copy(armas = armas)
        }
    }

    fun updateArma(arma: Arma) {
        _uiState.update { currentState ->
            currentState.copy(arma = arma)
        }
    }

    fun reset() {
        _uiState.update { currentState ->
            currentState.copy(
                agentes = null,
                agente = null,
                armas = null,
                arma = null,
            )
        }
    }

}