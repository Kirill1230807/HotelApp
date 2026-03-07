package com.example.hotelapp.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hotelapp.data.repositoryImp.HotelRepositoryImp
import com.example.hotelapp.domain.model.Hotel
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

    private val _selectedSortOption = MutableStateFlow("Рекомендовані")
    val selectedSortOption: StateFlow<String> = _selectedSortOption

    init {
        loadHotels()
    }

    fun onSortOptionSelected(option: String) {
        _selectedSortOption.value = option

        val currentState = _uiState.value
        if (currentState is HotelUIState.Success) {
            val sortedList = when (option) {
                "Ціна: від низької" -> currentState.hotels.sortedBy { it.pricePerNight }
                "Ціна: від високої" -> currentState.hotels.sortedByDescending { it.pricePerNight }
                "За рейтингом" -> currentState.hotels.sortedByDescending { it.rating }
                else -> currentState.hotels
            }
            _uiState.value = HotelUIState.Success(sortedList)
        }
    }

    fun loadHotels() {
        viewModelScope.launch {
            _uiState.value = HotelUIState.Loading
            try {
                val hotels = repository.getAllHotels()
                _uiState.value = HotelUIState.Success(hotels)
            } catch (e: Exception) {
                _uiState.value = HotelUIState.Error("Failed load hotels: $e")
            }
        }
    }
}