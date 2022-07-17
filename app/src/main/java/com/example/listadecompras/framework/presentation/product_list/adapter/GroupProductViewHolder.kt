package com.example.listadecompras.framework.presentation.product_list.adapter

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.databinding.GroupProductRecyclerBinding
import com.example.listadecompras.domain.model.Category
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.handler.OnItemClickProductHandler
import com.example.listadecompras.R
import android.graphics.drawable.TransitionDrawable
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

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

            val backgrounds = arrayOfNulls<Drawable>(2)
            backgrounds[0] = ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_keyboard_arrow_down)
            backgrounds[1] = ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_keyboard_arrow_up)

            val crossfader = TransitionDrawable(backgrounds)

            binding.iconGroup.setImageDrawable(crossfader)

            crossfader.startTransition(3000)


            binding.linearTitle.setOnClickListener {
                visibility = if (visibility == View.VISIBLE) {
                    binding.iconGroup.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_keyboard_arrow_down))
                    View.GONE
                }
                else {
                    binding.iconGroup.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_baseline_keyboard_arrow_up))
                    View.VISIBLE
                }

            }
        }
    }
}