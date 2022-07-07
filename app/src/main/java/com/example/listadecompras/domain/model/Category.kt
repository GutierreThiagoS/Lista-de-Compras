package com.example.listadecompras.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val idCategory: Int = 0,
    val nameCategory: String
)
