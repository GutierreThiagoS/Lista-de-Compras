package com.example.listadecompras.data.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.data.local.dao.ItemShoppingDao
import com.example.listadecompras.data.local.dao.ProductDao
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.domain.repository.ShoppingRepository

class ShoppingRepositoryImp(
    private val itemShoppingDao: ItemShoppingDao,
    private val productDao: ProductDao
): ShoppingRepository {

    override fun getList(): LiveData<List<ProductOnItemShopping>?> {
        return itemShoppingDao.getListProductOnItemShopping()
    }

    override fun getTotal(): LiveData<Float> {
        return itemShoppingDao.getTotalProductOnItemShopping()
    }

    override fun update(itemShopping: ItemShopping): Int{
        if (itemShopping.quantity <= 0)
            itemShoppingDao.delete(itemShoppingDao.consultItemShopping(itemShopping.idProductFK)!!)
        else if (itemShoppingDao.update(itemShopping) == 0)
            itemShoppingDao.insert(itemShopping)
        return itemShoppingDao.update(itemShopping)
    }

    override fun editPriceProduct(productOnItemShopping: ProductOnItemShopping): Int {
        with(productOnItemShopping) {
            return productDao.update(
                Product(
                    idProduct,
                    description,
                    imgProduct,
                    brandProduct,
                    idCategory,
                    ean,
                    price
                )
            )
        }
    }
}