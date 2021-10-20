package com.example.listadecompras.di

import com.example.listadecompras.data.local.AppDataBase
import org.koin.dsl.module

val daoModule = module {
    single { get<AppDataBase>().getCategoryDao() }
    single { get<AppDataBase>().getItemShoppingDao() }
    single { get<AppDataBase>().getProductDao() }
}