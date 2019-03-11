package com.example.coroutinesdemo.di.module

import com.example.coroutinesdemo.ui.UserActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuilderModule {
    @ContributesAndroidInjector(modules = [UserFragmentBuilder::class])
    abstract fun bindsUserActivity() : UserActivity
}