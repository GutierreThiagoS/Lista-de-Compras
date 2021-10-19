package com.example.listadecompras.framework.presentation.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.databinding.FragmentListProductBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ProductEvent
import com.example.listadecompras.handler.OnItemClickProductHandler
import com.example.listadecompras.domain.model.ProductOnItemShopping
import com.example.listadecompras.viewmodel.factory.ListProductViewModelFactory
import java.util.*

class ProductListFragment: Fragment(), Observer, OnItemClickProductHandler {

    private var binding : FragmentListProductBinding? = null
    private val adapterProduct = ProductListAdapter(this)

    private val viewModel: ListProductViewModel by lazy{
        ViewModelProvider(
            this,
            ListProductViewModelFactory()
        )[ListProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListProductBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.lifecycleOwner = viewLifecycleOwner
        binding!!.viewModel = viewModel

        ProductEvent.addObserver(this)

        binding!!.productListRecycler.apply {
            adapter = adapterProduct
            layoutManager = LinearLayoutManager(context)
            hasFixedSize()
        }

        viewModel.productListLive.observe(viewLifecycleOwner, {
            binding!!.notProduct.visibility = if(it == null || it.isEmpty())  View.VISIBLE
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

}