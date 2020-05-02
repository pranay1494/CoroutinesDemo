package com.example.coroutinesdemo.repository

import com.example.coroutinesdemo.api.model.User

interface UserRepository {
    suspend fun fetchRepository(params: String) : User
}