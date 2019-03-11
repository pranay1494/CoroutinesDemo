package com.example.coroutinesdemo.di.module

import com.example.coroutinesdemo.ui.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserFragmentBuilder {

    @ContributesAndroidInjector
    abstract fun providesUserFragment() : UserFragment
}