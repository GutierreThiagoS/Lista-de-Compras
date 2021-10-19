package com.example.listadecompras.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.data.repository.ShoppingRepositoryImp
import com.example.listadecompras.framework.presentation.shopping.ShoppingViewModel

class ShoppingListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingViewModel(ShoppingRepositoryImp()) as T
    }
}