package com.example.listadecompras.framework.presentation

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.App
import com.example.listadecompras.R
import com.example.listadecompras.framework.presentation.product_list.adapter.ProductListAdapter
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.presentation.shopping.adapter.ShoppingListCartAdapter
import com.example.listadecompras.framework.utils.toReal
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.play.core.internal.t


object BindingAdapter {

    @JvmStatic
    @BindingAdapter("productRecycler")
    fun bindProductRecycler(recyclerView: RecyclerView, list: List<ProductOnItemShopping>?){
        if (list != null) {
            val adapter = recyclerView.adapter as ProductListAdapter
            adapter.addAll(list as ArrayList<ProductOnItemShopping>)
        }
    }

    @JvmStatic
    @BindingAdapter("shoppingRecycler")
    fun bindShoppingRecycler(recyclerView: RecyclerView, list: List<ProductOnItemShopping>?){
        if (list != null){
            val adapter = recyclerView.adapter as ShoppingListCartAdapter
            adapter.addAll(list as ArrayList<ProductOnItemShopping>)
        }
    }

    @JvmStatic
    @BindingAdapter("autoCompleteItems")
    fun bindAutoCompleteItems(view: MaterialAutoCompleteTextView, list: List<Category>?){
        Log.e("bindAutoCompleteItems", "$list, $view")
        if (list != null) {
            val adapter = ArrayAdapter(App.context, android.R.layout.simple_dropdown_item_1line, list.map { it.nameCategory })
            view.setAdapter(adapter)
        }
    }

    @JvmStatic
    @BindingAdapter("totalShopping")
    fun TextView.bindTotalShopping(total: Float){
        this.text = App.context.getString(R.string.total_rs, total.toReal())
    }
}