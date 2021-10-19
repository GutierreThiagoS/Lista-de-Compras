package com.example.listadecompras.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShopping(
    @PrimaryKey(autoGenerate = true) val idItem: Int,
    @ColumnInfo val idProductFK: Int,
    @ColumnInfo val quantity: Int,
    @ColumnInfo val selected: Boolean
)
