package com.msg.gauthsignin.remote

import com.msg.gauthsignin.dto.response.UserInfoDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface GAuthUserAPI {
    @GET("user")
    fun getUserInfo(
        @Header("Authorization") accessToken: String
    ): Call<UserInfoDTO>
}