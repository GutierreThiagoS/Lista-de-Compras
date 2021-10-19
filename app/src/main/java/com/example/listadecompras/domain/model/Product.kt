package com.example.listadecompras.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) var idProduct: Int,
    @ColumnInfo var description: String,
    @ColumnInfo var imgProduct: String,
    @ColumnInfo var brandProduct: String,
    @ColumnInfo var idCategoryFK: Int,
    @ColumnInfo var ean: String,
    @ColumnInfo var price: Float
)
