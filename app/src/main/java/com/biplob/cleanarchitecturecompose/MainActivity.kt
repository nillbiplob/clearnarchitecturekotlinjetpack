package com.biplob.cleanarchitecturecompose

import SplashScreen
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.biplob.cleanarchitecturecompose.application.usecase.GetUsersUseCase
import com.biplob.cleanarchitecturecompose.data.repository.UserRepositoryImpl
import com.biplob.cleanarchitecturecompose.data.source.UserLocalDataSource
import com.biplob.cleanarchitecturecompose.data.source.UserRemoteDataSource
import com.biplob.cleanarchitecturecompose.domain.model.User
import com.biplob.cleanarchitecturecompose.presentation.userdetail.UserDetailScreen
import com.biplob.cleanarchitecturecompose.presentation.userlist.UserListScreen
import com.biplob.cleanarchitecturecompose.presentation.userlist.UserListViewModel

class MainActivity : ComponentActivity() {
    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setup all dependencies manually (for demo)
        val remoteDS = UserRemoteDataSource()
        val localDS = UserLocalDataSource(this)
        val repo = UserRepositoryImpl(remoteDS, localDS)
        val useCase = GetUsersUseCase(repo)

        setContent {
            var currentScreen by remember { mutableStateOf("splash") }
            var selectedUser by remember { mutableStateOf<User?>(null) }


            val userListVM: UserListViewModel =
                viewModel(factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        @Suppress("UNCHECKED_CAST")
                        return UserListViewModel(useCase) as T
                    }
                })

            when (currentScreen) {
                "splash" -> {
                    val loading = userListVM.loading.collectAsState().value
                    val error = userListVM.error.collectAsState().value


                    LaunchedEffect(loading, error, userListVM.users.value) {
                        if (!loading && error == null && userListVM.users.value.isNotEmpty()) {
                            currentScreen = "list"
                        }
                    }
                    Log.d("SplashScreen", "Composed! loading=$loading, error=$error")

                    SplashScreen(
                        loading = loading,
                        error = error,
                        onRetry = { userListVM.loadUsers() }
                    )
                }
                "list" -> UserListScreen(
                    viewModel = userListVM,
                    onUserClick = { user ->
                        selectedUser = user
                        currentScreen = "detail"
                    },
                    onRefresh = { userListVM.loadUsers() }
                )
                "detail" -> selectedUser?.let { user ->
                    UserDetailScreen(
                        user = user,
                        onBack = { currentScreen = "list" }
                    )
                }
            }
        }
    }
}