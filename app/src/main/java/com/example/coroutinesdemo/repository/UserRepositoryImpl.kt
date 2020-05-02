package com.example.coroutinesdemo.repository

import com.example.coroutinesdemo.api.UserApi
import com.example.coroutinesdemo.api.model.User
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userApi: UserApi): UserRepository{

    override suspend fun fetchRepository(params: String): User {
        return userApi.getUserDetails(params)
    }

}