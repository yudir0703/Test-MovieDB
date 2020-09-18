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
import com.yudi.test3.databinding.PseudocodeFragmentBinding


/**
 * Created by Yudi Rahmat
 */
class PseudocodeFragment: BaseFragment() {
    private lateinit var binding: PseudocodeFragmentBinding
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate<PseudocodeFragmentBinding>(inflater, R.layout.pseudocode_fragment, container, false).apply {}

        initToolbar()
        reverseChar()
        checkChar()

        return binding.root
    }

    private fun initToolbar() {
        (activity as? AppCompatActivity)?.supportActionBar?.show()
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Test JDS"
    }

    private fun reverseChar() {
        binding.btnCheck.setOnClickListener() {
            val textValue   = binding.etData1.text.toString()
            val result  = textValue.toCharArray().reversed()
            val data        = result.joinToString(separator = "", truncated = "...!")

            binding.tvOutput1.text = "Output : \n$data"
        }
    }

    private fun checkChar() {
        binding.btnCheck2.setOnClickListener() {
            var textValue   = binding.etData2.text.toString()
            textValue              = textValue?.replace(" ", "")
            val result   = textValue.toCharArray()
            val vowels   = charArrayOf('a', 'e', 'i', 'o', 'u')
            val sentence = textValue.replace("[^a-zA-Z]", "").toLowerCase().toCharArray()

            var vokal = 0
            for (letter in sentence) {
                for (vowel in vowels) {
                    if (letter == vowel) {
                        vokal++
                    }
                }
            }

            val konsonan    = sentence.size - vokal
            val data     = java.lang.String.format(
                mContext.resources.getString(R.string.tv_label_sample_2),
                result.size,
                vokal,
                konsonan
            )
            binding.tvOutput2.text = data
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }
}