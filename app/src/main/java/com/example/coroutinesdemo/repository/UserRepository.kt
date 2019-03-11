package com.example.coroutinesdemo.repository

import com.example.coroutinesdemo.api.model.User
import com.example.coroutinesdemo.ui.UserViewModel
import kotlinx.coroutines.Deferred

interface UserRepository {
    suspend fun fetchRepository(params: UserViewModel.UserParams) : User.DisplayData
}