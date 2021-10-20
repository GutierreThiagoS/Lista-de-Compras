package com.example.listadecompras.framework.presentation.product_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.event.ProductEvent
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.domain.repository.ProductRepository
import kotlinx.coroutines.*

class ListProductViewModel(
    private val repository: ProductRepository
    ): ViewModel() {

    val productListLive: LiveData<List<ProductOnItemShopping>?> = repository.productList()


    fun insertProductInShoppingList(product: ProductOnItemShopping){
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }){
            val state = repository.insertShopping(
                shopping = ItemShopping(
                    product.idItem, product.idProduct, product.quantity, product.selected
                )
            )
            withContext(Dispatchers.Main){
                Log.e("QUANTITY", " $state")
                if (state != null){
                    if (state.quantity == 0) ProductEvent.notifyErrorInsert("Deletado!!")
                    else ProductEvent.notifyInsertProduct("Produto Inserido na Lista")
                }
                else ProductEvent.notifyErrorInsert("Erro!!")
            }
        }

    }

    fun removeProduct(product: ProductOnItemShopping, alert: (message: String) -> Unit){
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }) {
            val message = repository.removerProduct(product)
            withContext(Dispatchers.Main) {
                alert(message)
            }
        }
    }
}