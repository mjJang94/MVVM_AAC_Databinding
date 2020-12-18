package com.mj.databinding.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.mj.databinding.MainActivity
import com.mj.databinding.R
import com.mj.databinding.databinding.FragmentListBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {
    private lateinit var binding: FragmentListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)
        binding.viewmodel = MainActivity.User("asd","24")
        binding.lifecycleOwner = activity
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListFragment()
    }
}