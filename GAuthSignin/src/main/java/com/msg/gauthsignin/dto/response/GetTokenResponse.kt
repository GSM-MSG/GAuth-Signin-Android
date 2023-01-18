package com.msg.gauthsignin.dto.response

data class GetTokenResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val status: Int
)
