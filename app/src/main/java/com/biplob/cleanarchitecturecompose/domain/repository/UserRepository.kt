package com.biplob.cleanarchitecturecompose.domain.repository

import com.biplob.cleanarchitecturecompose.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}
