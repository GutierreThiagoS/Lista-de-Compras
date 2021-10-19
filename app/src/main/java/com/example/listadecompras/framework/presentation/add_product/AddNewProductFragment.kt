package com.example.listadecompras.framework.presentation.add_product

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.listadecompras.App
import com.example.listadecompras.databinding.FragmentAddNewProductBinding
import com.example.listadecompras.domain.model.Product
import com.example.listadecompras.viewmodel.factory.AddNewProductViewModelFactory

class AddNewProductFragment: Fragment() {

    private var binding : FragmentAddNewProductBinding? = null
    private val viewModel: AddNewProductViewModel by lazy {
        ViewModelProvider(
            this, AddNewProductViewModelFactory()
        )[AddNewProductViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNewProductBinding.inflate(layoutInflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categoryList.observe(viewLifecycleOwner, { list ->
            Log.e("CATEGORY", "$list")
            if (list != null){
                val adapter = ArrayAdapter(App.context, R.layout.simple_dropdown_item_1line, list.map { it.nameCategory })
                binding?.categoryEditText?.setAdapter(adapter)
            }
        })

        addProduct()
        binding?.viewModelAdd = viewModel

        viewModel.record.observe(viewLifecycleOwner, {
            clearEdit()
            if (it.isNotBlank())Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.listProduct.observe(viewLifecycleOwner, {
            Log.e("list", " $it")
        })
    }

    private fun addProduct(){
        binding?.apply {
            addProductBtn.setOnClickListener {
                Log.e("addProductBtn", " $")
                viewModel.consultCategory(categoryEditText.text.toString())
            }
            clearBtn.setOnClickListener {
               clearEdit()
            }
            viewModel.categoryLive.observe(viewLifecycleOwner, {
                Log.e("categoryLive", " $it")
                val product = Product(
                    0,
                    nameProductEditText.text.toString(),
                    "",
                    "",
                    it.idCategory,
                    "",
                    priceEditText.text.toString().toFloat()
                )
                viewModel.insertProduct(product)
            })


        }
    }
    private fun clearEdit(){
        binding?.apply {
            nameProductEditText.setText("")
            priceEditText.setText("")
            categoryEditText.setText("")
        }
    }
}