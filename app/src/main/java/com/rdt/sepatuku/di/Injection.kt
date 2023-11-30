package com.rdt.sepatuku.di

import com.rdt.sepatuku.data.ItemRepository


object Injection {
    fun provideRepository(): ItemRepository {
        return ItemRepository.getInstance()
    }
}