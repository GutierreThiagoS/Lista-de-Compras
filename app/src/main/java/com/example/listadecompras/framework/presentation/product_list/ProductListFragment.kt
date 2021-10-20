package com.example.listadecompras.framework.presentation.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.FragmentListProductBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ProductEvent
import com.example.listadecompras.framework.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.framework.presentation.BaseFragment
import com.example.listadecompras.framework.presentation.product_list.adapter.ProductListAdapter
import org.koin.android.ext.android.inject
import java.util.*

class ProductListFragment: BaseFragment(), Observer, OnItemClickProductHandler {

    private val binding by lazy { FragmentListProductBinding.inflate(layoutInflater) }
    private val adapterProduct = ProductListAdapter(this)
    private val viewModel by inject<ListProductViewModel>()

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

        ProductEvent.addObserver(this)

        binding.productListRecycler.apply {
            adapter = adapterProduct
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
        }

        viewModel.productListLive.observe(viewLifecycleOwner, {
            binding.notProduct.visibility = if(it == null || it.isEmpty())  View.VISIBLE
            else View.GONE
        })
    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null && arg is Array<*>){
            when(o){
                is ProductEvent -> {
                    when(arg[0]){
                        Events.INSERT_PRODUCT_SHOPPING -> {
                            Toast.makeText(requireContext(), "${arg[1]} ", Toast.LENGTH_SHORT).show()
                        }

                        Events.ERROR_INSERT -> {
                            Toast.makeText(requireContext(), "${arg[1]} ", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemClick(product: ProductOnItemShopping) {
        viewModel.insertProductInShoppingList(product)
    }

    override fun onItemEditClick(product: ProductOnItemShopping) {

    }

    override fun onItemRemoveClick(product: ProductOnItemShopping) {
        showDialog("Deseja Realmente Remover esse Item?") {
            viewModel.removeProduct(product) { message ->
                showDialog(message)
            }
        }
    }

}