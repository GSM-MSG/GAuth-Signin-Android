package com.msg.gauthsignin.dto

data class GetTokenResponse(
    val accessToken: String?,
    val refreshToken: String?,
    val status: Int
)
