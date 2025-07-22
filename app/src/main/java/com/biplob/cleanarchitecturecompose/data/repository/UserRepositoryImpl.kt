package com.biplob.cleanarchitecturecompose.data.repository

import com.biplob.cleanarchitecturecompose.domain.model.User
import com.biplob.cleanarchitecturecompose.domain.repository.UserRepository
import com.biplob.cleanarchitecturecompose.data.model.UserDto
import com.biplob.cleanarchitecturecompose.data.source.UserLocalDataSource
import com.biplob.cleanarchitecturecompose.data.source.UserRemoteDataSource

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return try {
            val dtos = remoteDataSource.fetchUsers()
            localDataSource.saveUsers(dtos)
            dtos.map { it.toUser() }
        } catch (e: Exception) {
            val cached = localDataSource.getCachedUsers()
            if (cached.isEmpty()) throw Exception("No cached data available")
            cached.map { it.toUser() }
        }
    }
}
