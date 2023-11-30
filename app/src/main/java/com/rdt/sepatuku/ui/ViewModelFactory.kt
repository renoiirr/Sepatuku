package com.rdt.sepatuku.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rdt.sepatuku.data.ItemRepository
import com.rdt.sepatuku.ui.screen.cart.CartViewModel
import com.rdt.sepatuku.ui.screen.detail.DetailItemViewModel
import com.rdt.sepatuku.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: ItemRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailItemViewModel::class.java)) {
            return DetailItemViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}