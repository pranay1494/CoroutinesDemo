package com.example.coroutinesdemo.repository

import com.example.coroutinesdemo.api.UserApi
import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.ui.UserViewModel
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userApi: UserApi): UserRepository{
    override suspend fun fetchRepository(params: UserViewModel.UserParams): User.DisplayData {
        val user = userApi.getUserDetails(params.name).await()
        return User.DisplayData(user.name?:"",user.avatarUrl?:"")
    }

}