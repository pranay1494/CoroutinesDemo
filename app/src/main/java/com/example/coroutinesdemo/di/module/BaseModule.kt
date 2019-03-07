package com.example.coroutinesdemo.di.module

import android.app.Application
import android.content.Context
import com.example.coroutinesdemo.CoroutinesApp
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BaseModule(val app : CoroutinesApp) {

    @Singleton
    @Provides
    internal fun provideApp() : Application = app

    @Singleton
    @Provides
    internal fun provideAppContext() : Context = app

    @Singleton
    @Provides
    internal fun provideGson() : Gson = Gson()

}