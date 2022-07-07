package com.example.listadecompras.framework.presentation.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.GroupProductRecyclerBinding
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.handler.OnItemClickProductHandler

class GroupProductAdapter(
    private val handler: OnItemClickProductHandler
): RecyclerView.Adapter<GroupProductViewHolder>() {

    private val categoryList = mutableListOf<Category>()
    private val productList = mutableListOf<ProductOnItemShopping>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupProductViewHolder {
        val binding = GroupProductRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return GroupProductViewHolder(binding, handler)
    }

    override fun onBindViewHolder(holder: GroupProductViewHolder, position: Int) {
        val category = categoryList[position]
        holder.bind(category, productList.filter { it.idCategory == category.idCategory })
    }

    override fun getItemCount() = categoryList.size

    fun addAll(list: List<Category>, listProduct: List<ProductOnItemShopping>) {
        categoryList.clear()
        productList.clear()
        categoryList.addAll(list)
        productList.addAll(listProduct)
        notifyDataSetChanged()
    }
}