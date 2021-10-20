package com.example.listadecompras.di

import com.example.listadecompras.data.repository.ProductRepositoryImp
import com.example.listadecompras.domain.repository.ProductRepository
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single { ProductRepositoryImp(
        productDao = get(),
        categoryDao = get(),
        itemShoppingDao = get()
    ) } bind ProductRepository::class
}