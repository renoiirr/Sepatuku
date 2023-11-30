package com.rdt.sepatuku.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdt.sepatuku.data.ItemRepository
import com.rdt.sepatuku.model.OrderItem
import com.rdt.sepatuku.model.Item
import com.rdt.sepatuku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailItemViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderItem>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderItem>>
        get() = _uiState

    fun getItemById(ItemId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderItemById(ItemId))
        }
    }

    fun addToCart(Item: Item, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(Item.id, count)
        }
    }
}