package com.biplob.cleanarchitecturecompose.data.model

data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
) {
    fun toUser() = com.biplob.cleanarchitecturecompose.domain.model.User(
        id = id,
        name = name,
        username = username,
        email = email
    )

    companion object {
        fun fromUser(user: com.biplob.cleanarchitecturecompose.domain.model.User) = UserDto(
            id = user.id,
            name = user.name,
            username = user.username,
            email = user.email
        )
    }
}
