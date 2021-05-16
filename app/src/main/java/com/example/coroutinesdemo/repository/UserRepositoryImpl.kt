package com.example.coroutinesdemo.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toFlow
import com.example.LaunchListQuery
import com.example.coroutinesdemo.api.UserApi
import com.example.coroutinesdemo.api.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(val userApi: UserApi,val apolloClient: ApolloClient): UserRepository{

    override suspend fun fetchRepository(params: String): User {
        return userApi.getUserDetails(params)
    }

    override suspend fun fetchLaunchData(): Response<LaunchListQuery.Data> {
        return apolloClient.query(LaunchListQuery()).await()
    }

}