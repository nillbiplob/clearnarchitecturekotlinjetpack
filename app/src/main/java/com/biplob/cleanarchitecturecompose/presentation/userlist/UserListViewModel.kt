package com.biplob.cleanarchitecturecompose.presentation.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.biplob.cleanarchitecturecompose.application.usecase.GetUsersUseCase
import com.biplob.cleanarchitecturecompose.domain.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserListViewModel(private val getUsersUseCase: GetUsersUseCase): ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadUsers() {
        _loading.value = true
        viewModelScope.launch {
            try {
                val loadedUsers = getUsersUseCase()
                _users.value = loadedUsers
                _error.value = null
            } catch (e: Exception) {
                _error.value = e.message
            }
            _loading.value = false
        }
    }
}
