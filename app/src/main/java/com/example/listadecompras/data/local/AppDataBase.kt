package com.example.listadecompras.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listadecompras.data.local.dao.CategoryDao
import com.example.listadecompras.data.local.dao.ItemShoppingDao
import com.example.listadecompras.data.local.dao.ProductDao
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.Product

@Database(entities = [
    Product::class,
    Category::class,
    ItemShopping::class,
                     ], version = 1, exportSchema = false)
abstract class AppDataBase :RoomDatabase() {
    abstract fun getProductDao(): ProductDao
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getItemShoppingDao(): ItemShoppingDao
}