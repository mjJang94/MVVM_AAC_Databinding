package com.mj.databinding.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent

class MainViewModel : ViewModel(), KoinComponent {

    var btnClickConverter = MutableLiveData<Unit>()


    fun onButtonClick() {

        btnClickConverter.value = Unit
    }
}

