package com.msg.gauthsignin

import android.util.Log
import com.msg.gauthsignin.dto.request.AccountInfoDTO
import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.*
import com.msg.gauthsignin.remote.GAuthNetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GAuth {
    fun getGAuthTokenRequest(
        code: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        callBack: (GetTokenResponse) -> Unit
    ) {
        val getGAuthTokenInfoReques = GAuthNetworkBuilder.gAuthApi.getGAuthToken(
            ServiceInfoDTO(
                code = code,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUri = redirectUri
            )
        )
        getGAuthTokenInfoReques.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                callBack(
                    GetTokenResponse(
                        accessToken = response.body()?.accessToken,
                        refreshToken = response.body()?.refreshToken,
                        status = response.code()
                    )
                )
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetGAuthToken", t.toString())
            }
        })
    }

    fun tokenRefreshRequest(
        refreshToken: String,
        callBack: (GetTokenResponse) -> Unit
    ) {
        val getTokenInfoReques = GAuthNetworkBuilder.gAuthApi.tokenRefresh("Bearer $refreshToken")
        getTokenInfoReques.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                callBack(
                    GetTokenResponse(
                        accessToken = response.body()?.accessToken,
                        refreshToken = response.body()?.refreshToken,
                        status = response.code()
                    )
                )
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetToken", t.toString())
            }
        })
    }

    fun getUserInfoRequest(
        accessToken: String,
        callBack: (GetUserInfoResponse) -> Unit
    ) {
        val getUserInfoRequest = GAuthNetworkBuilder.userApi.getUserInfo("Bearer $accessToken")
        getUserInfoRequest.enqueue(object : Callback<UserInfoDTO> {
            override fun onResponse(call: Call<UserInfoDTO>, response: Response<UserInfoDTO>) {
                callBack(
                    GetUserInfoResponse(
                        email = response.body()?.email,
                        name = response.body()?.name,
                        grade = response.body()?.grade,
                        classNum = response.body()?.grade,
                        num = response.body()?.num,
                        gender = response.body()?.gender,
                        profileUrl = response.body()?.profileUrl,
                        role = response.body()?.role, status = response.code()
                    )
                )
            }

            override fun onFailure(call: Call<UserInfoDTO>, t: Throwable) {
                Log.d("UserInfo", t.toString())
            }
        })
    }

    fun getCodeInfoRequest(
        email: String, password: String, callBack: (GetCodeResponse) -> Unit
    ) {
        val getCodeInfoRequest = GAuthNetworkBuilder.gAuthApi.getCode(
            AccountInfoDTO(
                email = email, password = password
            )
        )
        getCodeInfoRequest.enqueue(object : Callback<CodeInfoDTO> {
            override fun onResponse(call: Call<CodeInfoDTO>, response: Response<CodeInfoDTO>) {
                callBack(
                    GetCodeResponse(
                        response.body()?.code, response.code()
                    )
                )
            }

            override fun onFailure(call: Call<CodeInfoDTO>, t: Throwable) {

            }
        })
    }
}