package com.example.listadecompras.framework.presentation.product_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ItemProductListRecyclerBinding
import com.example.listadecompras.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ProductListViewHolder(private val binding: ItemProductListRecyclerBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(product: ProductOnItemShopping, handler: OnItemClickProductHandler){
        binding.apply {
            descriptionProduct.text = product.description
            priceProduct.text = "R$: ${product.price}"
            quantityProduct.text = product.quantity.toString()

            addProductBtn.setOnClickListener {
                val quantity = quantityProduct.text.toString().toInt() + 1
                product.quantity = quantity
                handler.onItemClick(product)
            }
            removeProductBtn.setOnClickListener {
                val bindingQuantity = quantityProduct.text.toString().toInt()
                val quantity = if (bindingQuantity > 0)bindingQuantity - 1 else 0
                product.quantity = quantity
                handler.onItemClick(product)
            }
        }
    }
}