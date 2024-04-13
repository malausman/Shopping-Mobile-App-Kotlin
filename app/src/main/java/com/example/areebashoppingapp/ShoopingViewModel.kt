package com.example.areebashoppingapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoopingViewModel(private val respository : ShoppingRepository) : ViewModel()
{

    fun insert(items:ShoppingItem) = GlobalScope.launch {
        respository.insert(items)
    }
    fun delete(items:ShoppingItem) = GlobalScope.launch {
        respository.delete(items)
    }

    fun getAllItems() = respository.getAllItems()
}