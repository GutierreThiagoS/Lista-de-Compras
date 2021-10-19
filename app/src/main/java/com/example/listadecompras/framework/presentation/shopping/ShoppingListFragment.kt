package com.example.listadecompras.framework.presentation.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.FragmentListShoppingBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ItemShoppingEvent
import com.example.listadecompras.handler.OnItemClickShppingHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.viewmodel.factory.ShoppingListViewModelFactory
import java.util.*

class ShoppingListFragment: Fragment(),Observer, OnItemClickShppingHandler {

    private var binding: FragmentListShoppingBinding? = null
    private val adapterShopping = ShoppingListCartAdapter(this)

    private val viewModel: ShoppingViewModel by lazy {
        ViewModelProvider(
            this,
            ShoppingListViewModelFactory()
        )[ShoppingViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = FragmentListShoppingBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.viewModel = viewModel

        binding!!.shoppingRecyclerView.apply {
            adapter = adapterShopping
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
        }

        viewModel.productOnShoppingLive.observe(viewLifecycleOwner, {
            binding!!.notItemListText.visibility = if (it.isNullOrEmpty()) View.VISIBLE
            else View.GONE
            if (it != null) {
                var total = 0f
                it.forEach { productOnShopping ->
                    if (productOnShopping.selected) total += productOnShopping.price * productOnShopping.quantity
                }
                binding!!.totalPriceText.text = "   R$: $total"
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