package com.example.listadecompras.framework.presentation.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.FragmentListShoppingBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ItemShoppingEvent
import com.example.listadecompras.framework.handler.OnItemClickShppingHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.dialog.EditPriceDialog
import com.example.listadecompras.framework.presentation.BaseFragment
import com.example.listadecompras.framework.presentation.shopping.adapter.ShoppingListCartAdapter
import com.example.listadecompras.framework.utils.toReal
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject
import java.util.*

class ShoppingListFragment: BaseFragment(), Observer, OnItemClickShppingHandler {

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
        })

        ItemShoppingEvent.addObserver(this)
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null && arg is Array<*>) {
            when (o) {
                is ItemShoppingEvent ->{
                    when(arg[0]){
                        Events.UPDATE_SHOPPING ->{
//                            Toast.makeText(requireContext(), " ${arg[1]}", Toast.LENGTH_SHORT).show()
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
        Log.e("onItemClickShopping", "$productOnItemShopping")
        viewModel.checkedShopping(productOnItemShopping)
    }

    override fun onItemLongClickPrice(productOnItemShopping: ProductOnItemShopping, position: Int): Boolean {
        val dialog = EditPriceDialog(productOnItemShopping.price) { newPrice ->
            Log.e("EditPriceDialog", "newPrice $newPrice")
            lifecycleScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, _ ->  }) {
                productOnItemShopping.price = newPrice
                val isUpdate = viewModel.editPriceProduct(productOnItemShopping)
                withContext(Dispatchers.Main) {
                    if (isUpdate != 0){
                        adapterShopping.addPosition(productOnItemShopping, position)
                    } else Toast.makeText(requireContext(), "Preço não Editado!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        dialog.show(childFragmentManager, "dialog")
        return true
    }
}