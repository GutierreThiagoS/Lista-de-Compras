package com.example.listadecompras.handler

import com.example.listadecompras.domain.model.ProductOnItemShopping

interface OnItemClickShppingHandler {
    fun onItemClickShopping(productOnItemShopping: ProductOnItemShopping)
}