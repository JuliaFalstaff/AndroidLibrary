package com.example.androidlibrary.di

import com.example.androidlibrary.App
import com.example.androidlibrary.mvp.model.retrofit.RetrofitAPI
import com.example.androidlibrary.mvp.network.AndroidNetworkStatus
import com.example.androidlibrary.mvp.network.INetworkStatus
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String) : RetrofitAPI = Retrofit.Builder()
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(RetrofitAPI::class.java)

    @Singleton
    @Provides
    fun networkStatus(app: App) : INetworkStatus = AndroidNetworkStatus(app)



}

