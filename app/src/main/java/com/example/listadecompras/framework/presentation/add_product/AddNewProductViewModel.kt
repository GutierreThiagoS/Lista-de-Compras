package com.example.listadecompras.framework.presentation.add_product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.data.repository.ProductRepositoryImp
import kotlinx.coroutines.*

class AddNewProductViewModel(private val repository: ProductRepositoryImp): ViewModel() {

    val record = MutableLiveData("")
    val categoryList: LiveData<List<Category>> = repository.consultCategoryList()
    private val _categoryLive = MutableLiveData<Category>()
    val categoryLive : LiveData<Category> = _categoryLive

    val listProduct = repository.productList()

    fun insertProduct(product: Product){
        viewModelScope.launch(Dispatchers.IO +  CoroutineExceptionHandler { _, _ -> }) {
            val status = repository.insertProduct(product)
            withContext(Dispatchers.Main){
                if (status > 0) record.value = "Gravado com Sucesso"
            }
        }
    }

    fun consultCategory(name: String){
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }){
            Log.e("consultCategory", " $name")
            val status = repository.consultCategory(name)
            withContext(Dispatchers.Main){
                Log.e("consultCategory", " $status")
                if (status == null) insertCategory(category = Category(0, name))
                else _categoryLive.value = status
            }
        }
    }

    private fun insertCategory(category: Category){
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }){
            Log.e("insertCategory", " $category")
            val status = repository.insertCategory(category)
            withContext(Dispatchers.Main){
                Log.e("insertCategory2", "status $status")
                if (status > 0) consultCategory(category.nameCategory)
            }
        }
    }
}