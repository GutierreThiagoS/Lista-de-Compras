package com.example.listadecompras.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) val idCategory: Int,
    @ColumnInfo val nameCategory: String
)
