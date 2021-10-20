package com.example.listadecompras.data.repository

import androidx.lifecycle.LiveData
import com.example.listadecompras.data.local.dao.CategoryDao
import com.example.listadecompras.data.local.dao.ItemShoppingDao
import com.example.listadecompras.data.local.dao.ProductDao
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.domain.repository.ProductRepository

class ProductRepositoryImp(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val itemShoppingDao: ItemShoppingDao
): ProductRepository {

    override fun insertProduct(product: Product): Long{
        return productDao.insert(product)
    }

    override fun productList(): LiveData<List<ProductOnItemShopping>?>{
        return productDao.listAllProductOnItemShopping()
    }


    override fun consultCategory(name: String): Category?{
        return categoryDao.consultCategory(name)
    }

    override fun consultCategoryList(): LiveData<List<Category>>{
        return categoryDao.categoryList()
    }

    override fun insertCategory(category: Category): Long{
        return categoryDao.insert(category)
    }

    override fun insertShopping(shopping: ItemShopping): ItemShopping?{

        if (shopping.quantity <= 0)
            itemShoppingDao.delete(itemShoppingDao.consultItemShopping(shopping.idProductFK)!!)
        else if(itemShoppingDao.update(shopping) == 0)
            itemShoppingDao.insert(shopping)

        return itemShoppingDao.consultItemShopping(shopping.idProductFK)
    }

    override fun removerProduct(product: ProductOnItemShopping): String {
        return if (itemShoppingDao.consultItemShopping(productId = product.idProduct) == null){
            productDao.deleteId(productId = product.idProduct)
            "Produto Deletado!"
        } else "Esse produto esta No Carrinho!"
    }
}