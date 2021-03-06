package com.example.listadecompras.domain.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.ProductOnItemShopping

interface ShoppingRepository {

    fun getList(): LiveData<List<ProductOnItemShopping>?>

    fun getTotal(): LiveData<Float>

    fun update(itemShopping: ItemShopping): Int

    fun editPriceProduct(productOnItemShopping: ProductOnItemShopping): Int
}