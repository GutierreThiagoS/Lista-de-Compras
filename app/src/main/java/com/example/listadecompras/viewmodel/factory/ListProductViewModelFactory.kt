package com.example.listadecompras.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.data.repository.ProductRepositoryImp
import com.example.listadecompras.framework.presentation.product_list.ListProductViewModel

class ListProductViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListProductViewModel(ProductRepositoryImp()) as T
    }

}