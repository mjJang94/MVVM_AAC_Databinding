package com.mj.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mj.databinding.databinding.ActivityMainBinding
import com.mj.databinding.fragment.ListFragment
import java.util.EnumSet.of
import java.util.Optional.of

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscribeUI(binding)
    }




    private fun subscribeUI(binding: ActivityMainBinding) {


        val factory = MainViewModelFactory()
        val viewModel: MainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        viewModel.clickConverter.observe(this, Observer {
            Toast.makeText(this, "${binding.user?.name}, ${binding.user?.age}", Toast.LENGTH_SHORT).show()
        })

        viewModel.btnClickConverter.observe(this, Observer {
            binding.user = User("데이터 변경 : 장민종", "데이터 변경 : 28", R.drawable.android)
        })

        binding.user = User("장민종", "27", R.drawable.android)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        supportFragmentManager.beginTransaction().replace( binding.flFragment.id, ListFragment.newInstance()).commitAllowingStateLoss()
    }

    data class User(var name: String = "", var age: String = "", var profileURL: Int = -1)
}