package com.biplob.cleanarchitecturecompose.application.usecase

import com.biplob.cleanarchitecturecompose.domain.model.User
import com.biplob.cleanarchitecturecompose.domain.repository.UserRepository

class GetUsersUseCase(private val userRepository: UserRepository) {
    suspend operator fun invoke(): List<User> {
        return userRepository.getUsers()
    }
}
