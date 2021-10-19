package com.example.listadecompras.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.domain.model.ProductOnItemShopping

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(product: Product): Long

    @Update
    fun update(product: Product)

    @Query("SELECT * FROM Product ORDER BY description ASC")
    fun listAll(): LiveData<List<Product>?>

    @Query("""
        SELECT S.idItem as idItem, P.idProduct, P.description, P.imgProduct, P.brandProduct,
        C.idCategory, C.nameCategory, P.ean, P.price, S.quantity, S.selected FROM Product P 
        LEFT JOIN ItemShopping S ON (S.idProductFK = P.idProduct)
        LEFT JOIN Category C ON C.idCategory = P.idCategoryFK
        GROUP BY P.idProduct
        ORDER BY description ASC
        """)
    fun listAllProductOnItemShopping(): LiveData<List<ProductOnItemShopping>?>
}