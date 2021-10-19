package com.example.listadecompras.event

import com.example.listadecompras.domain.model.Product
import java.util.*

object ProductEvent: Observable() {

    fun notifyOnClickItemProduct(product: Product){
        notifyObservers(arrayOf(Events.ONCLICK_ITEM_PRODUCT, product))
    }

    fun notifyInsertProduct(message: String){
        notifyObservers(arrayOf(Events.INSERT_PRODUCT_SHOPPING, message))
    }

    fun notifyErrorInsert(message: String){
        notifyObservers(arrayOf(Events.ERROR_INSERT, message))
    }

    override fun hasChanged() = true
}