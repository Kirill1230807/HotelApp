package com.example.hotelapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.repositoryImp.HotelRepositoryImp
import com.example.hotelapp.domain.reposityry.HotelRepository
import com.example.hotelapp.util.HotelUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HotelListViewModel(
    private val repository: HotelRepository = HotelRepositoryImp()
) : ViewModel() {
    private val _uiState = MutableStateFlow<HotelUIState>(HotelUIState.Loading)
    val uiState: StateFlow<HotelUIState> = _uiState

    init {
        loadHotels()
    }

    fun loadHotels() {
        viewModelScope.launch {
            _uiState.value = HotelUIState.Loading
            try {
                val hotels = repository.getAllHotels()
                _uiState.value = HotelUIState.Success(hotels)
            }
            catch (e: Exception) {
                _uiState.value = HotelUIState.Error("Failed load hotels: $e")
            }
        }
    }
}