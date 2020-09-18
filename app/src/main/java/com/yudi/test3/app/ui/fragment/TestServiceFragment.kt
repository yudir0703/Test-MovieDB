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
import com.yudi.test3.app.common.GpsUtils
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

    private var isPermissionGPS: Boolean = false
    private var isGPS: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<TestServiceFragmentBinding>(inflater, R.layout.test_service_fragment, container, false).apply {}

        initToolbar()
        buttonListener()
        permissionCheck()

        return binding.root
    }

    private fun initToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Test JDS"
    }

    private fun permissionCheck() {
        activity?.let { isPermissionGPS = Common.checkLocationPermission(it) }
    }

    private fun buttonListener() {
        binding.btnStart.setOnClickListener() {
            startService()
        }

        binding.btnStop.setOnClickListener() {
            binding.btnStart.isEnabled = true
            mContext.stopService(Intent(mContext, TestService::class.java))
        }
    }

    private fun startService() {
        permissionCheck()
        turnOnGPS()
        mContext.startService(Intent(mContext, TestService::class.java))

//        if(isPermissionGPS) {
//            if(isGPS) mContext.startService(Intent(mContext, TestService::class.java))
//            else turnOnGPS()
//        } else {
//            permissionCheck()
//        }
    }

    private fun turnOnGPS() {
        GpsUtils(mContext).turnGPSOn { isGPSEnable -> // turn on GPS
            isGPS = isGPSEnable
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: EventbusLocation?) {
        binding.btnStart.isEnabled = false
        binding.tvOutput.append("\nLat : " + event?.latitude + ", Long : " + event?.longitude)
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