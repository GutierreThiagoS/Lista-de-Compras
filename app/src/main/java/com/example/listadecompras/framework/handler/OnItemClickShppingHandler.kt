package com.example.listadecompras.framework.handler

import com.example.listadecompras.domain.model.ProductOnItemShopping

interface OnItemClickShppingHandler {
    fun onItemClickShopping(productOnItemShopping: ProductOnItemShopping)

    fun onItemLongClickPrice(productOnItemShopping: ProductOnItemShopping, position: Int): Boolean
}