package com.example.listadecompras.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemShopping(
    @PrimaryKey(autoGenerate = true) val idItem: Int = 0,
    val idProductFK: Int,
    val quantity: Int,
    val selected: Boolean
)
