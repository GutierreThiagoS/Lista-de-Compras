package com.example.listadecompras.data.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.App
import com.example.listadecompras.data.local.dao.ProductDao
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.domain.repository.ProductRepository

class ProductRepositoryImp(
    productDao: ProductDao
): ProductRepository {

    fun insertProduct(product: Product): Long{
        return App.db.getProductDao().insert(product)
    }

    fun productList(): LiveData<List<ProductOnItemShopping>?>{
        return App.db.getProductDao().listAllProductOnItemShopping()
    }


    fun consultCategory(name: String): Category?{
        return App.db.getCategoryDao().consultCategory(name)
    }

    fun consultCategoryList(): LiveData<List<Category>>{
        return App.db.getCategoryDao().categoryList()
    }

    fun insertCategory(category: Category): Long{
        return App.db.getCategoryDao().insert(category)
    }

    fun insertShopping(shopping: ItemShopping): ItemShopping?{

        if (shopping.quantity <= 0)
            App.db.getItemShopping().delete(App.db.getItemShopping().consultItemShopping(shopping.idProductFK)!!)
        else if(App.db.getItemShopping().update(shopping) == 0)
            App.db.getItemShopping().insert(shopping)

        return App.db.getItemShopping().consultItemShopping(shopping.idProductFK)
    }
}