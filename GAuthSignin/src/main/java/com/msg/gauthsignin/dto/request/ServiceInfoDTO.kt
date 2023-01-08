package com.msg.gauthsignin.dto.request

data class ServiceInfoDTO(
    val code: String,
    val clientId: String,
    val clientSecret: String,
    val redirectUri: String
)
