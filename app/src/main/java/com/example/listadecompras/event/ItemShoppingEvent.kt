package com.example.listadecompras.event

import java.util.*

object ItemShoppingEvent: Observable()  {

    fun notifyUpdateShopping(message: String){
        notifyObservers(arrayOf(Events.UPDATE_SHOPPING, message))
    }

    fun notifyErrorUpdate(message: String){
        notifyObservers(arrayOf(Events.ERRO, message))
    }

    override fun hasChanged() = true
}

