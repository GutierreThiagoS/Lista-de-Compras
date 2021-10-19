package com.example.listadecompras.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.listadecompras.domain.model.Category

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category): Long

    @Query("SELECT * FROM Category WHERE nameCategory = :name Limit 1")
    fun consultCategory(name: String): Category?

    @Query("SELECT * FROM Category")
    fun categoryList(): LiveData<List<Category>>
}