package com.rdt.sepatuku.ui.screen.cart

import com.rdt.sepatuku.model.OrderItem

data class CartState(
    val orderItem: List<OrderItem>,
    val totalRequiredPoint: Int
)