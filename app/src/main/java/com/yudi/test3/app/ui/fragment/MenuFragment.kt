package com.yudi.test3.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import com.yudi.test3.R
import com.yudi.test3.app.base.BaseFragment
import com.yudi.test3.app.common.Common
import com.yudi.test3.databinding.MainMenuFragmentBinding


/**
 * @author Yudi Rahmat
 */


class MenuFragment : BaseFragment(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: MainMenuFragmentBinding
    private lateinit var mContext: Context

    private lateinit var manager: FragmentManager

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<MainMenuFragmentBinding>( inflater, R.layout.main_menu_fragment, container, false).apply {}

        initview()

        return binding.root
    }

    private fun initview() {
        manager = (activity as? AppCompatActivity)?.supportFragmentManager!!

        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(mContext.getDrawable(R.drawable.ic_baseline_menu_24))
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = mContext.resources.getString(R.string.nav_label_menu_1)

        binding.navView.setNavigationItemSelectedListener(this)

        displayScreen(-1)
    }

    private fun updateTitle(title: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = title
    }

    private fun displayScreen(id: Int){
        binding.drawerLayout.closeDrawer(GravityCompat.START)

        val bundle          = Bundle()
        bundle.putInt("menu", id)

        val fragment        = MoviesFragment()
        fragment.arguments  = bundle

        when (id){
            R.id.nav_home -> {
                updateTitle(mContext.resources.getString(R.string.nav_label_menu_1))
                manager?.beginTransaction()?.replace(R.id.relativelayout, fragment)?.commit()
            }

            R.id.nav_nowplaying -> {
                updateTitle(mContext.resources.getString(R.string.nav_label_menu_2))
                manager?.beginTransaction()?.replace(R.id.relativelayout, fragment)?.commit()
            }

            R.id.nav_upcoming -> {
                updateTitle(mContext.resources.getString(R.string.nav_label_menu_3))
                manager?.beginTransaction()?.replace(R.id.relativelayout, fragment)?.commit()
            }

            R.id.nav_popular -> {
                updateTitle(mContext.resources.getString(R.string.nav_label_menu_4))
                manager?.beginTransaction()?.replace(R.id.relativelayout, fragment)?.commit()
            }

            R.id.nav_settings -> {
                Common.showToast(mContext, "Clicked Settings")
            }

            R.id.nav_aboutUs -> {
                Common.showToast(mContext, "Clicked About")
            }

            R.id.nav_privacyPolicy -> {
                Common.showToast(mContext, "Clicked Privacy Policy")
            } else -> {
                manager?.beginTransaction()?.replace(R.id.relativelayout, MoviesFragment())?.commit()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            else
                binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        displayScreen(item.itemId)

        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

}
