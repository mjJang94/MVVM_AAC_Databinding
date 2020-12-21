package com.mj.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mj.databinding.api.RetrofitConnection
import com.mj.databinding.viewModel.MainViewModel
import com.mj.databinding.viewModel.MainViewModelFactory
import com.mj.databinding.vo.DogInfo
import com.mj.databinding.databinding.ActivityMainBinding
import com.mj.databinding.fragment.ListFragment
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDogInfoActivity : AppCompatActivity() {


    private val connectService: RetrofitConnection by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        subscribeUI(binding)
    }

    private fun subscribeUI(binding: ActivityMainBinding) {


        val factory = MainViewModelFactory()
        val viewModel: MainViewModel = ViewModelProvider(this, factory).get(
            MainViewModel::class.java)



        viewModel.btnClickConverter.observe(this, Observer {

            val dogInfo = DogInfo()

            dogInfo.name = binding.editName.text.toString()
            dogInfo.age = binding.editAge.text.toString()
            dogInfo.breed = binding.editBreed.text.toString()
            dogInfo.color = binding.editColor.text.toString()
            dogInfo.character = binding.editCharacter.text.toString()

            connectService.addDogInfo(dogInfo).enqueue(object : Callback<DogInfo>{
                override fun onFailure(call: Call<DogInfo>, t: Throwable) {
                    Toast.makeText(this@AddDogInfoActivity, "등록 실패", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<DogInfo>, response: Response<DogInfo>) {
                   if (response.isSuccessful){
                       startActivity(Intent(this@AddDogInfoActivity, InquiryDogInfoActivity::class.java))
                   }else{
                       Toast.makeText(this@AddDogInfoActivity, "등록 실패", Toast.LENGTH_SHORT).show()
                   }
                }
            })



        })

//        binding.user = User("장민종", "27")
        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this


        supportFragmentManager.beginTransaction().replace( binding.flFragment.id, ListFragment.newInstance()).commitAllowingStateLoss()
    }

    data class User(var name: String = "", var age: String = "", var breed: String = "", var color: String = "", var character: String = "")
}