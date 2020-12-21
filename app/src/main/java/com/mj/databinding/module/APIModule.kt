package com.mj.databinding.module

import com.google.gson.GsonBuilder
import com.mj.databinding.api.RetrofitConnection
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun apiConnectionModule() = module {
    single {
        GsonBuilder().setLenient().create()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .baseUrl("http://193.122.123.237:8080/")
            .build()
            .create(RetrofitConnection::class.java)
    }
}