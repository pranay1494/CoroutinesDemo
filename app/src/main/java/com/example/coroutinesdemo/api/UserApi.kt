package com.example.coroutinesdemo.api

import com.example.coroutinesdemo.api.model.User
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("users/{username}")
    fun getUserDetails(@Path("username") userName: String) : Deferred<User>
}