package com.msg.gauthsignin.dto

data class GetUserInfoResponse(
    val email: String?,
    val name: String?,
    val grade: Int?,
    val classNum: Int?,
    val num: Int?,
    val gender: String?,
    val profileUrl: String?,
    val role: String?,
    val status: Int
)
