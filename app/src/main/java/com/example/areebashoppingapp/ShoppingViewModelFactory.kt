package com.example.areebashoppingapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoppingViewModelFactory(private val respository: ShoppingRepository) : ViewModelProvider.NewInstanceFactory()
{

    override  fun <T: ViewModel?> create(modelClass: Class<T>) :T{
        return ShoopingViewModel(respository) as T
    }
}