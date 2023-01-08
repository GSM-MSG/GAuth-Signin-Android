package com.msg.gauthsignin

import android.util.Log
import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.TokenInfoDTO
import com.msg.gauthsignin.remote.GAuthNetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GAuth {
    fun getGAuthTokenInfo(code: String) {
        val getTokenInfoRequest = GAuthNetworkBuilder.tokenApi.getGAuthToken(
            ServiceInfoDTO(
                code = code,
                clientId = "00ce71cc5f774d4191db789d4e6aea40260080b4498947de98f3c7bd7d5ec78d",
                clientSecret = "d4e5963dc9984f058ea868c915690442f6b1ccabd22041b3878284ac2f916901",
                redirectUri = "https://www.google.com"
            )
        )
        getTokenInfoRequest.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                if (response.isSuccessful) {
                    Log.d("GetToken", response.body().toString())
                } else {
                    Log.d("GetToken", response.code().toString())
                }
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetToken", t.toString())
            }
        })
    }
}