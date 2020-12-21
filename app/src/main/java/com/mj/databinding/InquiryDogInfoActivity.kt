package com.mj.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mj.databinding.api.RetrofitConnection
import com.mj.databinding.viewModel.ResultViewModel
import com.mj.databinding.viewModel.ResultViewModelFactory
import com.mj.databinding.vo.DogInfo
import com.mj.databinding.databinding.ActivityDogInfoBinding
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InquiryDogInfoActivity : AppCompatActivity() {

    private val connectService: RetrofitConnection by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityDogInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_dog_info)
        subScribeUI(binding)
    }

    private fun subScribeUI(binding: ActivityDogInfoBinding) {
        val factory = ResultViewModelFactory()
        val viewModel: ResultViewModel = ViewModelProvider(this, factory).get(ResultViewModel::class.java)


        viewModel.searchBtnClickInverter.observe(this, Observer {

            val dogInfo = DogInfo()

            dogInfo.name = binding.editSearchNm.text.toString()

            connectService.getDogInfo(dogInfo).enqueue(object: Callback<List<DogInfo>>{


                override fun onFailure(call: Call<List<DogInfo>>, t: Throwable) {
                    Toast.makeText(this@InquiryDogInfoActivity, "불러오기 실패 ${t.message.toString()}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<List<DogInfo>>,
                    response: Response<List<DogInfo>>
                ) {
                    if (response.isSuccessful){
                        viewModel.name = response.body()?.get(0)?.name ?: "저장된 데이터 없음"
                        viewModel.age = response.body()?.get(0)?.age ?: "저장된 데이터 없음"
                        viewModel.breed = response.body()?.get(0)?.breed ?: "저장된 데이터 없음"
                        viewModel.color = response.body()?.get(0)?.color ?: "저장된 데이터 없음"
                        viewModel.character = response.body()?.get(0)?.character ?: "저장된 데이터 없음"

                        binding.infoViewModel = viewModel

                    }else{
                        Toast.makeText(this@InquiryDogInfoActivity, "불러오기 실패", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        })


        viewModel.goAddClickInverter.observe(this, Observer {
            startActivity(Intent(this@InquiryDogInfoActivity, AddDogInfoActivity::class.java))
        })

        binding.infoViewModel = viewModel
        binding.lifecycleOwner = this
    }
}