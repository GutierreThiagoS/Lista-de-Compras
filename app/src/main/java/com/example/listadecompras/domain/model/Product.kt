package com.example.listadecompras.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) var idProduct: Int = 0,
    var description: String,
    var imgProduct: String,
    var brandProduct: String,
    var idCategoryFK: Int,
    var ean: String,
    var price: Float
)
