package com.example.listadecompras.framework.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.listadecompras.R
import com.example.listadecompras.databinding.DialogEditPriceBinding
import java.lang.Exception

class EditPriceDialog(
    private val price: Float,
    val confirm: (price: Float) -> Unit
): DialogFragment() {

    private val binding by lazy { DialogEditPriceBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editPriceValue.hint = "Editar o valor R$ $price"

        binding.cancelNewPrice.setOnClickListener {
            dismiss()
        }

        binding.saveNewPrice.setOnClickListener {
            try {
                confirm(binding.editPriceValue.text.toString().toFloat())
                dismiss()
            }catch (e: Exception){
                binding.editPriceValue.error = "preço invalido!"
            }
        }
    }

    override fun getTheme() = R.style.RoundedCornersDialog
}