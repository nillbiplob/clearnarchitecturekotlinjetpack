package com.biplob.cleanarchitecturecompose.presentation.userdetail

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.biplob.cleanarchitecturecompose.domain.model.User

@Composable
fun UserDetailScreen(user: User, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text(user.name, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            Text("@${user.username}", color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(user.email, color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f))
        }
    }
}
