package com.example.listadecompras.domain.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.domain.model.ProductOnItemShopping

interface ProductRepository {

    fun insertProduct(product: Product): Long

    fun productList(): LiveData<List<ProductOnItemShopping>?>

    fun consultCategory(name: String): Category?

    fun consultCategoryList(): LiveData<List<Category>>

    fun insertCategory(category: Category): Long

    fun insertShopping(shopping: ItemShopping): ItemShopping?

    fun removerProduct(product: ProductOnItemShopping): String
}