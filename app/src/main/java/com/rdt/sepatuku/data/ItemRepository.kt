package com.rdt.sepatuku.data

import com.rdt.sepatuku.model.FakeItemDataSource
import com.rdt.sepatuku.model.OrderItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class ItemRepository {

    private val orderItems = mutableListOf<OrderItem>()

    init {
        if (orderItems.isEmpty()) {
            FakeItemDataSource.dummyItems.forEach {
                orderItems.add(OrderItem(it, 0))
            }
        }
    }

    fun getAllItems(): Flow<List<OrderItem>> {
        return flowOf(orderItems)
    }

    fun getOrderItemById(ItemId: Long): OrderItem {
        return orderItems.first {
            it.Item.id == ItemId
        }
    }

    fun updateOrderItem(ItemId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderItems.indexOfFirst { it.Item.id == ItemId }
        val result = if (index >= 0) {
            val orderItem = orderItems[index]
            orderItems[index] =
                orderItem.copy(Item = orderItem.Item, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderItems(): Flow<List<OrderItem>> {
        return getAllItems()
            .map { orderItems ->
                orderItems.filter { orderItem ->
                    orderItem.count != 0
                }
            }
    }

    fun searchItems(query: String): Flow<List<OrderItem>>{
        return flow {
            val searchResult = orderItems.filter {
                it.Item.title.contains(query, ignoreCase = true)
            }
            emit(searchResult)
        }
    }

    companion object {
        @Volatile
        private var instance: ItemRepository? = null

        fun getInstance(): ItemRepository =
            instance ?: synchronized(this) {
                ItemRepository().apply {
                    instance = this
                }
            }
    }
}