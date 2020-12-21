package com.mj.databinding.api

import com.mj.databinding.vo.DogInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RetrofitConnection {

    /**
     * 강아지 정보 등록
     */
    @POST("dog/info/add")
    fun addDogInfo(@Body dogInfo: DogInfo): Call<DogInfo>

    /**
     * 강아지 정보 이름으로 조회
     */
    @POST("dog/info/get")
    fun getDogInfo(@Body dogInfo: DogInfo): Call<List<DogInfo>>
}