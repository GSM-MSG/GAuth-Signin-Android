package com.msg.gauthsignin.remote

import com.msg.gauthsignin.dto.request.AccountInfoDTO
import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.CodeInfoDTO
import com.msg.gauthsignin.dto.response.TokenInfoDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface GAuthAPI {
    @POST("oauth/token")
    fun getGAuthToken(
        @Body body: ServiceInfoDTO
    ): Call<TokenInfoDTO>

    @PATCH("oauth/token")
    fun tokenRefresh(
        @Header("refreshToken") refreshToken: String
    ): Call<TokenInfoDTO>

    @POST("oauth/code")
    fun getCode(
        @Body body: AccountInfoDTO
    ): Call<CodeInfoDTO>
}