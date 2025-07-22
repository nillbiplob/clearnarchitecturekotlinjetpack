package com.biplob.cleanarchitecturecompose.data.source

import com.biplob.cleanarchitecturecompose.data.model.UserDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.Request

class UserRemoteDataSource {
    private val client = OkHttpClient()
    private val gson = Gson()
    private val apiUrl = "https://jsonplaceholder.typicode.com/users"

    suspend fun fetchUsers(): List<UserDto> {
        val request = Request.Builder().url(apiUrl).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw Exception("Failed to fetch users: ${response.code}")
            val body = response.body?.string() ?: throw Exception("Empty response body")
            val type = object : TypeToken<List<UserDto>>() {}.type
            return gson.fromJson(body, type)
        }
    }
}
