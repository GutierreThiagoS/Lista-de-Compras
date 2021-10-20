package com.example.listadecompras.framework.presentation.shopping

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.event.ItemShoppingEvent
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.domain.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingViewModel(
    private val repository: ShoppingRepository
    ): ViewModel() {

    var productOnShoppingLive: LiveData<List<ProductOnItemShopping>?> = repository.getList()

    fun checkedShopping(productOnItemShopping: ProductOnItemShopping){
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }){
            val state = repository.update(
                ItemShopping(
                idItem = productOnItemShopping.idItem,
                idProductFK = productOnItemShopping.idProduct,
                quantity = productOnItemShopping.quantity,
                selected = productOnItemShopping.selected
            )
            )
            withContext(Dispatchers.Main){
                if (state > 0) {
                    ItemShoppingEvent.notifyUpdateShopping("Atualizado")
                }
                else ItemShoppingEvent.notifyErrorUpdate("Erro!!!")
            }
        }
    }
}