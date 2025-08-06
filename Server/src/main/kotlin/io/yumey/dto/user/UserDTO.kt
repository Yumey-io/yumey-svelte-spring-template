package io.yumey.dto.user

data class UserDTO(
    val id: String,
    val name: String,
    val email: String,
    val avatar: String? = null,
)
