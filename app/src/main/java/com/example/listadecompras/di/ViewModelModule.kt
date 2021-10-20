package com.example.listadecompras.di

import com.example.listadecompras.framework.presentation.add_product.AddNewProductViewModel
import com.example.listadecompras.framework.presentation.product_list.ListProductViewModel
import com.example.listadecompras.framework.presentation.setting.SettingViewModel
import com.example.listadecompras.framework.presentation.shopping.ShoppingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ListProductViewModel(
            repository = get()
        )
    }

    viewModel {
        AddNewProductViewModel(
            repository = get()
        )
    }

    viewModel {
        ShoppingViewModel(
            repository = get()
        )
    }

    viewModel { SettingViewModel() }
}