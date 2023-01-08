package com.msg.gauthsignin.remote

import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.TokenInfoDTO
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface GAuthAPI {
    @POST("token/")
    fun getGAuthToken(
        @Body body: ServiceInfoDTO
    ): Call<TokenInfoDTO>
}