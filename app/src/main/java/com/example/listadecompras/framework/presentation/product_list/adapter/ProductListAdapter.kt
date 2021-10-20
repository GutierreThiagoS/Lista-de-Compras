package com.example.listadecompras.framework.presentation.product_list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ItemProductListRecyclerBinding
import com.example.listadecompras.framework.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ProductListAdapter(
    private val handler: OnItemClickProductHandler
    ): RecyclerView.Adapter<ProductListViewHolder>() {

    private var productList = ArrayList<ProductOnItemShopping>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListViewHolder {
        val itemBinding = ItemProductListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(itemBinding, handler = handler)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product = product)
    }

    override fun getItemCount() = productList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: List<ProductOnItemShopping>){
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }
}