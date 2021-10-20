package com.example.listadecompras.framework.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.FragmentListShoppingBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ItemShoppingEvent
import com.example.listadecompras.handler.OnItemClickShppingHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.presentation.shopping.adapter.ShoppingListCartAdapter
import org.koin.android.ext.android.inject
import java.util.*

class ShoppingListFragment: Fragment(),Observer, OnItemClickShppingHandler {

    private val binding by lazy { FragmentListShoppingBinding.inflate(layoutInflater) }
    private val adapterShopping = ShoppingListCartAdapter(this)
    private val viewModel by inject<ShoppingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.shoppingRecyclerView.apply {
            adapter = adapterShopping
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
        }

        viewModel.productOnShoppingLive.observe(viewLifecycleOwner, {
            binding.notItemListText.visibility = if (it.isNullOrEmpty()) View.VISIBLE
            else View.GONE
            if (it != null) {
                var total = 0f
                it.forEach { productOnShopping ->
                    if (productOnShopping.selected) total += productOnShopping.price * productOnShopping.quantity
                }
                binding.totalPriceText.text = "   R$: $total"
            }
        })

        ItemShoppingEvent.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null && arg is Array<*>) {
            when (o) {
                is ItemShoppingEvent ->{
                    when(arg[0]){
                        Events.UPDATE_SHOPPING ->{
                            Toast.makeText(requireContext(), " ${arg[1]}", Toast.LENGTH_SHORT).show()
                        }
                        Events.ERRO ->{
                            Toast.makeText(requireContext(), " ${arg[1]}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemClickShopping(productOnItemShopping: ProductOnItemShopping) {
        Toast.makeText(requireContext(), "$productOnItemShopping", Toast.LENGTH_SHORT).show()
        viewModel.checkedShopping(productOnItemShopping)
    }
}