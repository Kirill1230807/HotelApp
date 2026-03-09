package com.example.hotelapp.presentation.viewmodel

import androidx.compose.runtime.State
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
    /** Стан екрану private (доступний тільки у ViewModel). **/
    private val _uiState = MutableStateFlow<HotelUIState>(HotelUIState.Loading)

    /** Стан екрану uiState (доступний в екрані, який доступний йому). **/
    val uiState: StateFlow<HotelUIState> = _uiState

    /** Стан сортування private (доступний тільки у ViewModel). **/
    private val _selectedSortOption = MutableStateFlow("Рекомендовані")

    /** Стан сортування (доступний в екрані, який доступний йому). **/
    val selectedSortOption: StateFlow<String> = _selectedSortOption

    private val _favouriteHotelState = MutableStateFlow<Set<Int>>(emptySet())
    val favouriteHotelState: StateFlow<Set<Int>> = _favouriteHotelState

    /** Блок init викликається, коли створюється екземпляр ViewModel. Викликає метод loadHotels(),
     * який завантажує список готелів. **/
    init {
        loadHotels()
    }

    /** Метод завантаження даних. Викликає з репозиторію HotelRepository метод getAllHotels(), який
     * повертає список об'єктів.  **/
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

    fun onFavourite(hotelId: Int) {
        val currentState = _favouriteHotelState.value.toMutableSet()
        if (currentState.contains(hotelId)) {
            currentState.remove(hotelId)
        } else {
            currentState.add(hotelId)
        }
        _favouriteHotelState.value = currentState
    }
}