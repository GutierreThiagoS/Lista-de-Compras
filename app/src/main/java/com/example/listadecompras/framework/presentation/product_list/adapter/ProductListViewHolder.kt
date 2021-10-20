package com.example.listadecompras.framework.presentation.product_list.adapter

import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ItemProductListRecyclerBinding
import com.example.listadecompras.framework.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ProductListViewHolder(
    private val binding: ItemProductListRecyclerBinding,
    private val handler: OnItemClickProductHandler
):RecyclerView.ViewHolder(binding.root){

    fun bind(product: ProductOnItemShopping){
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
        binding.menu.setOnClickListener {
            showPopupMenu(product)
        }
    }

    private fun showPopupMenu(product: ProductOnItemShopping){
        val popupMenu = PopupMenu( binding.menu.context, binding.menu)
        popupMenu.menuInflater.inflate(R.menu.menu_item_product, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_edit -> handler.onItemEditClick(product)
                    R.id.action_remove -> handler.onItemRemoveClick(product)
                }
                return@setOnMenuItemClickListener true
            }
        popupMenu.show()
    }
}