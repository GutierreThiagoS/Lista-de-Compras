package com.example.listadecompras.framework.presentation.product_list.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.GroupProductRecyclerBinding
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.handler.OnItemClickProductHandler

class GroupProductViewHolder(
    private val binding: GroupProductRecyclerBinding,
    private val handler: OnItemClickProductHandler
): RecyclerView.ViewHolder(binding.root) {

    private val adapterProduct by lazy { ProductListAdapter(handler) }

    fun bind (item: Category, list: List<ProductOnItemShopping>) {
        binding.titleGroup.text = item.nameCategory

        binding.groupProductRecycler.apply {
            adapter = adapterProduct
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
            adapterProduct.addAll(list)
        }
    }
}