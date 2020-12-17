package com.mj.databinding

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    var TAG = this.javaClass.simpleName

    var clickConverter = MutableLiveData<Unit>()

    fun onClickHandler() {
        Log.d(TAG, "클릭 발생")
        clickConverter.value = Unit
    }
}
