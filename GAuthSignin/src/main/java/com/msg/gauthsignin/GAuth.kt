package com.msg.gauthsignin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.msg.gauthsignin.dto.request.ServiceInfoDTO
import com.msg.gauthsignin.dto.response.TokenInfoDTO
import com.msg.gauthsignin.dto.response.UserInfoDTO
import com.msg.gauthsignin.remote.GAuthNetworkBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GAuth {

    private val _getGAuthTokenResponse = MutableLiveData<TokenInfoDTO>()
    val getGAuthTokenResponse: LiveData<TokenInfoDTO> get() = _getGAuthTokenResponse

    private val _tokenRefreshResponse = MutableLiveData<TokenInfoDTO>()
    val tokenRefreshResponse: LiveData<TokenInfoDTO> get() = _tokenRefreshResponse

    private val _getUserInfoResponse = MutableLiveData<UserInfoDTO>()
    val getUserInfoResponse: LiveData<UserInfoDTO> get() = _getUserInfoResponse

    private val _code = MutableLiveData<String>()
    val code: LiveData<String> get() = _code

    fun setCodeValue(code: String) {
        _code.value = code
    }

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
                _getGAuthTokenResponse.value = response.body()
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetGAuthToken", t.toString())
            }
        })
    }

    fun tokenRefreshRequest(
        refreshToken: String
    ) {
        val getTokenInfoReques = GAuthNetworkBuilder.tokenApi.tokenRefresh("Bearer $refreshToken")
        getTokenInfoReques.enqueue(object : Callback<TokenInfoDTO> {
            override fun onResponse(call: Call<TokenInfoDTO>, response: Response<TokenInfoDTO>) {
                _tokenRefreshResponse.value = response.body()
            }

            override fun onFailure(call: Call<TokenInfoDTO>, t: Throwable) {
                Log.e("GetToken", t.toString())
            }
        })
    }

    fun getUserInfoRequest(
        accessToken: String
    ) {
        val getUserInfoRequest = GAuthNetworkBuilder.userApi.getUserInfo("Bearer $accessToken")
        getUserInfoRequest.enqueue(object : Callback<UserInfoDTO> {
            override fun onResponse(call: Call<UserInfoDTO>, response: Response<UserInfoDTO>) {
                _getUserInfoResponse.value = response.body()
            }

            override fun onFailure(call: Call<UserInfoDTO>, t: Throwable) {
                Log.d("UserInfo", t.toString())
            }
        })
    }
}