package com.mj.databinding.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultViewModel(): ViewModel() {
    var name: String = ""
    var age: String = ""
    var breed: String = ""
    var color: String = ""
    var character: String = ""

    val searchBtnClickInverter = MutableLiveData<() -> Unit>()
    val goAddClickInverter = MutableLiveData<Unit>()

    fun searchBtnClick(){
        searchBtnClickInverter.value = {



        }
    }

    fun addBtnClick(){
        goAddClickInverter.value = Unit
    }
}