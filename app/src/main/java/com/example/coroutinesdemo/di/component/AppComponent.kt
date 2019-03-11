package com.example.coroutinesdemo.di.component

import com.example.coroutinesdemo.CoroutinesApp
import com.example.coroutinesdemo.di.module.BaseModule
import com.example.coroutinesdemo.di.module.BuilderModule
import com.example.coroutinesdemo.di.module.NetworkModule
import com.example.coroutinesdemo.di.module.RepoModule
import com.example.coroutinesdemo.di.module.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class,BaseModule::class,NetworkModule::class,BuilderModule::class,ViewModelModule::class,RepoModule::class))
interface AppComponent {
    fun inject(app : CoroutinesApp)
}