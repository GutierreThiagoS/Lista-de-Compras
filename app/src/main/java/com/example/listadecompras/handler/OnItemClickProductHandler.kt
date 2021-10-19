package com.example.listadecompras.handler

import com.example.listadecompras.domain.model.ProductOnItemShopping

interface OnItemClickProductHandler {
    fun onItemClick(product: ProductOnItemShopping)
}