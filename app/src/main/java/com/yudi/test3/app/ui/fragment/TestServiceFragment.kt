package com.yudi.test3.app.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.yudi.test3.R
import com.yudi.test3.app.base.BaseFragment
import com.yudi.test3.app.common.Common
import com.yudi.test3.databinding.TestServiceFragmentBinding
import com.yudi.test3.service.eventbus.EventbusLocation
import com.yudi.test3.service.worker.TestService
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by Yudi Rahmat
 */
class TestServiceFragment: BaseFragment() {
    private lateinit var binding: TestServiceFragmentBinding
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<TestServiceFragmentBinding>(inflater, R.layout.test_service_fragment, container, false).apply {}

        initToolbar()
        buttonListener()

        return binding.root
    }

    private fun initToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Test JDS"
    }

    private fun buttonListener() {
        binding.btnStart.setOnClickListener() {
            mContext.startService(Intent(mContext, TestService::class.java))
        }

        binding.btnStop.setOnClickListener() {
            mContext.stopService(Intent(mContext, TestService::class.java))
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventbusLocation?) {
        binding.tvOutput.append("\n" + event?.latitude)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}