package com.example.listadecompras.framework.presentation.shopping

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.App
import com.example.listadecompras.R
import com.example.listadecompras.databinding.ItemShoppingListRecyclerBinding
import com.example.listadecompras.handler.OnItemClickShppingHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ShoppingCartViewHolder(private val binding: ItemShoppingListRecyclerBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(productOnItemShopping: ProductOnItemShopping, handler: OnItemClickShppingHandler){
        binding.apply {
            descriptionShoppingProduct.text = productOnItemShopping.description
            priceShoppingProduct.text =
                App.context.resources.getQuantityString(
                    R.plurals.price_product_plural,
                    productOnItemShopping.quantity,
                    productOnItemShopping.price,
                    productOnItemShopping.quantity,
                    ( productOnItemShopping.price  * productOnItemShopping.quantity )
                )
            quantityProduct.text = productOnItemShopping.quantity.toString()
            checkedShoppingCart.isChecked = productOnItemShopping.selected

            itemView.setOnClickListener {
                handler.onItemClickShopping(productOnItemShopping)
            }

            addProductBtn.setOnClickListener {
                val quantity = quantityProduct.text.toString().toInt() + 1
                productOnItemShopping.quantity = quantity
                handler.onItemClickShopping(productOnItemShopping)
            }
            removeProductBtn.setOnClickListener {
                val bindingQuantity = quantityProduct.text.toString().toInt()
                val quantity = if (bindingQuantity > 0) bindingQuantity - 1 else 0
                productOnItemShopping.quantity = quantity
                handler.onItemClickShopping(productOnItemShopping)
            }

            binding.checkedShoppingCart.setOnCheckedChangeListener { buttonView, isChecked ->
                Log.e("Checkerd", "button $buttonView, isChecker $isChecked")
                productOnItemShopping.selected = isChecked
                handler.onItemClickShopping(productOnItemShopping)
            }
        }
    }
}

