package com.example.listadecompras.framework.presentation.shopping.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.ItemShoppingListRecyclerBinding
import com.example.listadecompras.framework.handler.OnItemClickShppingHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping

class ShoppingListCartAdapter(private val handler: OnItemClickShppingHandler):RecyclerView.Adapter<ShoppingCartViewHolder>() {

    private var shoppingList = ArrayList<ProductOnItemShopping>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingCartViewHolder {
        val itemBinding = ItemShoppingListRecyclerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ShoppingCartViewHolder(itemBinding, handler)
    }

    override fun onBindViewHolder(holder: ShoppingCartViewHolder, position: Int) {
        val shoppingItem = shoppingList[position]
        holder.bind(shoppingItem)
    }

    override fun getItemCount() = shoppingList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(list: List<ProductOnItemShopping>){
        shoppingList.clear()
        shoppingList.addAll(list)
        notifyDataSetChanged()
    }

    fun addPosition(productOnItemShopping: ProductOnItemShopping, position: Int){
        shoppingList[position] = productOnItemShopping
        notifyItemChanged(position)
    }
}