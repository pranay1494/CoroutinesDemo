package com.example.coroutinesdemo.di.module

import com.example.coroutinesdemo.repository.UserRepository
import com.example.coroutinesdemo.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {

    @Binds
    abstract fun providesUserRepository(userRepositoryImpl: UserRepositoryImpl) : UserRepository
}