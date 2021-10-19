package com.example.listadecompras.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.data.repository.ProductRepositoryImp
import com.example.listadecompras.framework.presentation.add_product.AddNewProductViewModel

class AddNewProductViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AddNewProductViewModel(ProductRepositoryImp()) as T
    }
}