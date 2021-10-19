package com.example.listadecompras.data.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.App
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ShoppingRepositoryImp {
    fun getList(): LiveData<List<ProductOnItemShopping>?> {
        return App.db.getItemShopping().getListProductOnItemShopping()
    }

    fun update(itemShopping: ItemShopping): Int{
        if (itemShopping.quantity <= 0)
            App.db.getItemShopping().delete(App.db.getItemShopping().consultItemShopping(itemShopping.idProductFK)!!)
        else if (App.db.getItemShopping().update(itemShopping) == 0)
            App.db.getItemShopping().insert(itemShopping)
        return App.db.getItemShopping().update(itemShopping)
    }
}