package com.biplob.cleanarchitecturecompose.data.source

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.biplob.cleanarchitecturecompose.data.model.UserDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.first

class UserLocalDataSource(context: Context) {
    private val gson = Gson()
    private val dataStore = context.dataStore
    private val key = stringPreferencesKey("user_list_cache")

    suspend fun saveUsers(users: List<UserDto>) {
        val json = gson.toJson(users)
        dataStore.edit { prefs ->
            prefs[key] = json
        }
    }

    suspend fun getCachedUsers(): List<UserDto> {
        val prefs = dataStore.data.first()
        val json = prefs[key] ?: return emptyList()
        val type = object : TypeToken<List<UserDto>>() {}.type
        return gson.fromJson(json, type)
    }

    companion object {
        private val Context.dataStore by preferencesDataStore("user_cache_datastore")
    }
}
