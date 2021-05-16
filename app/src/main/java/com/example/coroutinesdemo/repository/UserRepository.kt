package com.example.coroutinesdemo.repository

import com.apollographql.apollo.api.Response
import com.example.LaunchListQuery
import com.example.coroutinesdemo.api.model.User

interface UserRepository {
    suspend fun fetchRepository(params: String) : User
    suspend fun fetchLaunchData(): Response<LaunchListQuery.Data>
}