package com.msg.gauthsignin.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GAuthNetworkBuilder {
    const val BASE_URL = "https://server.gauth.co.kr/oauth/"
    const val GET_USET_INFO_BASE_URL = "https://open.gauth.co.kr/"

    private val gAuthAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val userAPI = Retrofit.Builder()
        .baseUrl(GET_USET_INFO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _gAuthApi = gAuthAPI.create(GAuthAPI::class.java)
    val gAuthApi get() = _gAuthApi

    private val _userApi = userAPI.create(GAuthUserAPI::class.java)
    val userApi get() = _userApi
}