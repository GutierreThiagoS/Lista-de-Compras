package com.example.listadecompras.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.listadecompras.domain.model.ItemShopping
import com.example.listadecompras.domain.model.ProductOnItemShopping

@Dao
interface ItemShoppingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(itemShopping: ItemShopping): Long

    @Update
    fun update(itemShopping: ItemShopping): Int

    @Delete
    fun delete(itemShopping: ItemShopping)

    @Query("SELECT * FROM ItemShopping")
    fun listItemShoppingLive(): LiveData<List<ItemShopping>>

    @Query("""
        SELECT P.price * I.quantity FROM ItemShopping I LEFT JOIN Product P 
        ON I.idProductFK = P.idProduct
        WHERE I.selected = 1
    """)
    fun priceProductShopping(): Float

    @Query("SELECT COUNT(*) FROM ItemShopping WHERE idProductFK = :productId")
    fun consultItemShoppingCount(productId: Int): Int

    @Query("""
        SELECT * FROM ItemShopping 
        WHERE idProductFK = :productId
        LIMIT 1
    """)
    fun consultItemShopping(productId: Int): ItemShopping?

    @Query("""
        SELECT S.idItem, P.idProduct, P.description, P.imgProduct, P.brandProduct,
        C.idCategory, C.nameCategory, P.ean, P.price, S.quantity, S.selected
        FROM ItemShopping S INNER JOIN Product P 
        ON S.idProductFK = P.idProduct INNER JOIN Category C
        ON P.idCategoryFK = C.idCategory
    """)
    fun getListProductOnItemShopping(): LiveData<List<ProductOnItemShopping>?>

    @Query("""
        SELECT SUM(P.price * S.quantity)
        FROM ItemShopping S INNER JOIN Product P 
        ON S.idProductFK = P.idProduct INNER JOIN Category C
        ON P.idCategoryFK = C.idCategory WHERE selected == 1
    """)
    fun getTotalProductOnItemShopping(): LiveData<Float>
}