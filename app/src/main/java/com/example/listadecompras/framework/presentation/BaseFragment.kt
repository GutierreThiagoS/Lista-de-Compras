package com.example.listadecompras.framework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.listadecompras.MenuActivity
import com.example.listadecompras.R
import com.example.listadecompras.databinding.FragmentBaseBinding
import com.example.listadecompras.event.ItemShoppingEvent
import com.example.listadecompras.framework.dialog.showDialog

open class BaseFragment: Fragment() {

    private val binding by lazy { FragmentBaseBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    fun showDialog(message: String){
        requireContext().showDialog(message)
    }

    fun showDialog(message: String, position: (() -> Unit) ){
        requireContext().showDialog( message, position = position)
    }

    fun setTitle(title: String) {
        requireActivity().title = title
    }

    fun enableMenu(enable: Boolean){
        ItemShoppingEvent.notifyDrawerVisible(enable)
    }

}