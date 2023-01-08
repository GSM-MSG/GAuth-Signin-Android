package com.msg.gauthsignin.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GAuthNetworkBuilder {
    const val GET_TOKEN_BASE_URL = "https://server.gauth.co.kr/oauth/"

    private val tokenAPI = Retrofit.Builder()
        .baseUrl(GET_TOKEN_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _tokenApi = tokenAPI.create(GAuthAPI::class.java)
    val tokenApi get() = _tokenApi
}