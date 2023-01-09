package com.msg.gauthsignin.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GAuthNetworkBuilder {
    const val GET_TOKEN_BASE_URL = "https://server.gauth.co.kr/oauth/"
    const val GET_USET_INFO_BASE_URL = "https://open.gauth.co.kr/"

    private val tokenAPI = Retrofit.Builder()
        .baseUrl(GET_TOKEN_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val userAPI = Retrofit.Builder()
        .baseUrl(GET_USET_INFO_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _tokenApi = tokenAPI.create(GAuthTokenAPI::class.java)
    val tokenApi get() = _tokenApi

    private val _userApi = userAPI.create(GAuthUserAPI::class.java)
    val userApi get() = _userApi
}