package com.yudi.test3.app.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yudi.test3.R
import com.yudi.test3.app.base.BaseFragment
import com.yudi.test3.app.common.navigateTo
import com.yudi.test3.databinding.MenuTest2FragmentBinding


/**
 * Created by Yudi Rahmat
 */
class MenuTest2Fragment: BaseFragment() {
    private lateinit var binding: MenuTest2FragmentBinding
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<MenuTest2FragmentBinding>(inflater, R.layout.menu_test2_fragment, container, false).apply {}

        initToolbar()
        buttonListener()

        return binding.root
    }

    private fun initToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Test JDS"
    }

    private fun buttonListener() {
        binding.btnCheck.setOnClickListener() {
            navigateTo(this, R.id.action_to_test1)
        }

        binding.btnCheck2.setOnClickListener() {
            navigateTo(this, R.id.action_to_test2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }
}