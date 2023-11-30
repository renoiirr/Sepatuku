package com.rdt.sepatuku.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdt.sepatuku.data.ItemRepository
import com.rdt.sepatuku.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ItemRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderItems() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderItems()
                .collect { orderItem ->
                    val totalRequiredPoint =
                        orderItem.sumOf { it.Item.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderItem, totalRequiredPoint))
                }
        }
    }

    fun updateOrderItem(ItemId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderItem(ItemId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderItems()
                    }
                }
        }
    }
}