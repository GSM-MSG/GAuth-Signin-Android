package com.msg.gauthsignin.dto.response

data class UserInfoDTO(
    val email: String,
    val name: String?,
    val grade: Int?,
    val classNum: Int?,
    val num: Int?,
    val gender: String?,
    val profileUrl: String?,
    val role: String
)
