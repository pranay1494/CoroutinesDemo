package com.example.coroutinesdemo.di.module

import com.example.coroutinesdemo.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient,callAdapter: CallAdapter.Factory,converterFactory: Converter.Factory, baseUrl : String ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(callAdapter)
            .addConverterFactory(converterFactory)
            .baseUrl(baseUrl)
            .build()
    }


    @Singleton
    @Provides
    internal fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Singleton
    @Provides
    internal fun provideCoroutinesAdapter() : CallAdapter.Factory = CoroutineCallAdapterFactory()

    @Singleton
    @Provides
    internal fun provideConvertorFactory() : Converter.Factory = GsonConverterFactory.create()

    @Named("baseurl")
    @Singleton
    @Provides
    internal fun provideBaseUrl() = BuildConfig.BASE_URL



}