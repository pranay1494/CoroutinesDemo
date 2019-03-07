package com.example.coroutinesdemo.di.component

import com.example.coroutinesdemo.CoroutinesApp
import com.example.coroutinesdemo.di.module.BaseModule
import com.example.coroutinesdemo.di.module.NetworkModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(AndroidSupportInjectionModule::class,BaseModule::class,NetworkModule::class))
interface AppComponent {
    fun inject(app : CoroutinesApp)
}