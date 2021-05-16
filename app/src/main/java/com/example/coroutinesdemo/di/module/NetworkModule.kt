package com.example.coroutinesdemo.di.module

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.subscription.WebSocketSubscriptionTransport
import com.example.coroutinesdemo.BuildConfig
import com.example.coroutinesdemo.api.UserApi
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
import com.example.coroutinesdemo.utils.DispatchersUtil

@Module
class NetworkModule {

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient,callAdapter: CallAdapter.Factory,converterFactory: Converter.Factory, @Named("baseurl")baseUrl : String ): Retrofit {
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

    @Provides
    @Singleton
    fun provideApolloClient(okHttpClient: OkHttpClient, @Named("baseurl") baseUrl: String): ApolloClient =
        ApolloClient.builder()
            .serverUrl(baseUrl)
            .okHttpClient(okHttpClient)
            .build()

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

    @Singleton
    @Provides
    internal fun provideDispatchers() = DispatchersUtil()

    @Provides
    internal fun provideUserApi(retrofit: Retrofit) = retrofit.create(UserApi::class.java)
}