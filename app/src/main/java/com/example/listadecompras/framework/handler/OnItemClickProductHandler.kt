package com.example.listadecompras.framework.handler

import com.example.listadecompras.domain.model.ProductOnItemShopping

interface OnItemClickProductHandler {
    fun onItemClick(product: ProductOnItemShopping)
    fun onItemEditClick(product: ProductOnItemShopping)
    fun onItemRemoveClick(product: ProductOnItemShopping)
}