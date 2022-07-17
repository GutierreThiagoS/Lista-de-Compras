package com.example.listadecompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.listadecompras.databinding.ActivityMenuBinding
import com.example.listadecompras.event.Events
import com.example.listadecompras.event.ItemShoppingEvent
import com.google.android.material.navigation.NavigationView
import java.util.*

class MenuActivity : Observer, AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var toggle: ActionBarDrawerToggle? = null

    private val binding by lazy { ActivityMenuBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val bottomNavigationView = binding.bottomToolbar
        navController = findNavController(R.id.fragment_container)

        bottomNavigationView.setupWithNavController(navController)

        // For Navigation UP
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        NavigationUI.setupActionBarWithNavController(this, navController, binding.drawerLayout)

        binding.navigationView.setNavigationItemSelectedListener(this)
//        val toggle = ActionBarDrawerToggle(
//            this,
//            binding.drawerLayout,
//            binding.toolbar,
//            R.string.open_drawer,
//            R.string.close_drawer
//        )
//
//        binding.drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        toggle = getActionBarDrawerToggle().apply {
            setToolbarNavigationClickListener {
                // Back to home fragment for any hit to the back button
                navController.navigate(R.id.navigation_view)
            }
            // Intialize the icon at the app start
            enableHomeBackIcon(false)
        }

        NavigationUI.setupWithNavController(binding.navigationView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        //return navController.navigateUp()
        return NavigationUI.navigateUp(navController, appBarConfiguration)

    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg != null && arg is Array<*>) {
            when (o) {
                is ItemShoppingEvent -> {
                    when(arg[0]){
                        Events.DRAWER_VISIBLE -> {
                            Toast.makeText(this, "DRAWER_VISIBLE  ${arg[1]}", Toast.LENGTH_SHORT).show()
                            if (arg[1] == true)
                                enableHomeBackIcon(true)
                        }
                    }
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.shoppingListFragment -> {
                Toast.makeText(this, "shoppingListFragment", Toast.LENGTH_SHORT).show()
            }
            R.id.addNewProductFragment -> {
                Toast.makeText(this, "addNewProductFragment", Toast.LENGTH_SHORT).show()

            }
            R.id.listProductsFragment -> {
                Toast.makeText(this, "listProductsFragment", Toast.LENGTH_SHORT).show()

            }
            R.id.settingFragment -> {
                Toast.makeText(this, "settingFragment", Toast.LENGTH_SHORT).show()

            }
            else -> {  Toast.makeText(this, "ELSE", Toast.LENGTH_SHORT).show()
            }
        }
//        supportActionBar?.setHomeAsUpIndicator(
////            androidx.appcompat.R.drawable.abc_ic_ab_back_material
//            R.drawable.burger
//        )
        binding.drawerLayout.openDrawer(GravityCompat.START)
        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

        return true
    }

    fun enableHomeBackIcon(enabled: Boolean) {
        // Enable/Disable opening the drawer from the start side
        toggle?.isDrawerIndicatorEnabled = !enabled

        // Change the default burger icon
        supportActionBar?.setHomeAsUpIndicator(
            if (enabled) R.drawable.back_arrow
            else R.drawable.burger
        )
    }

    private fun getActionBarDrawerToggle(): ActionBarDrawerToggle {
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerLayout.addDrawerListener(toggle!!)
        toggle?.syncState()
        return toggle as ActionBarDrawerToggle
    }

}