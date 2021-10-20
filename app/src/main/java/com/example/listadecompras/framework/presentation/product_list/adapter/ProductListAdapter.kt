package com.example.listadecompras.framework.presentation.product_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ItemProductListRecyclerBinding
import com.example.listadecompras.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ProductListAdapter(private val handler: OnItemClickProductHandler): RecyclerView.Adapter<ProductListViewHolder>() {

    private var productList = ArrayList<ProductOnItemShopping>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductListViewHolder {
        val itemBinding = ItemProductListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ProductListViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product = product, handler = handler)
    }

    override fun getItemCount() = productList.size

    fun addAll(list: List<ProductOnItemShopping>){
        clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    fun clear(){
        productList.clear()
        notifyDataSetChanged()
    }
}