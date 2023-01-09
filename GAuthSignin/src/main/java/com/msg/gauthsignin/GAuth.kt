package com.msg.gauthsignin

import android.util.Log
import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.TokenInfoDTO
import com.msg.gauthsignin.remote.GAuthNetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GAuth {
    fun getGAuthTokenRequest(
        code: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ) {
        val getGAuthTokenInfoReques = GAuthNetworkBuilder.tokenApi.getGAuthToken(
            ServiceInfoDTO(
                code = code,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = redirectUri
            )
        )
        getGAuthTokenInfoReques.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                if (response.isSuccessful) {
                    Log.d("GetGAuthToken", response.body().toString())
                    tokenRefreshRequest("Bearer " + response.body()!!.refreshToken)
                } else {
                    Log.d("GetGAuthToken", response.body().toString())
                }
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetGAuthToken", t.toString())
            }
        })
    }

    fun tokenRefreshRequest(
        refreshToken: String
    ) {
        val getTokenInfoReques = GAuthNetworkBuilder.tokenApi.tokenRefresh(refreshToken)
        getTokenInfoReques.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                if (response.isSuccessful) {
                    Log.d("GetToken", response.body().toString())
                } else {
                    Log.d("GetToken", response.toString())
                }
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetToken", t.toString())
            }
        })
    }
}