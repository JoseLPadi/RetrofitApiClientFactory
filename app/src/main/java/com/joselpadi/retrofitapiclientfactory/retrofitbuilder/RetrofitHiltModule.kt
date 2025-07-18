package com.joselpadi.retrofitapiclientfactory.retrofitbuilder

import com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config.ApiClients
import com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config.RetrofitConfigProvider
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitHiltModule {


    @Provides
    @Named(ApiClients.CORE_CLIENT)
    fun provideCoreRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
        @Named(ApiClients.CORE_CLIENT) signatureInterceptor: Interceptor
    ): Retrofit {
        val config = RetrofitConfigProvider.getConfig(ApiClients.CORE)
        return RetrofitBuilder.build(
            config,
            moshi = moshi,
            okHttpClient = okHttpClient,
            signatureInterceptor = signatureInterceptor
        )
    }

    @Provides
    @Named(ApiClients.MAPS_CLIENT)
    fun provideMapsRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
        @Named(ApiClients.MAPS_CLIENT) signatureInterceptor: Interceptor
    ): Retrofit {
        val config = RetrofitConfigProvider.getConfig(ApiClients.MAPS)
        return RetrofitBuilder.build(
            config,
            moshi = moshi,
            okHttpClient = okHttpClient,
            signatureInterceptor = signatureInterceptor
        )
    }

    @Provides
    @Named(ApiClients.ANOTHER_CLIENT)
    fun provideAnotherRetrofit(
        moshi: Moshi,
        okHttpClient: OkHttpClient,
        @Named(ApiClients.ANOTHER_CLIENT) signatureInterceptor: Interceptor
    ): Retrofit {
        val config = RetrofitConfigProvider.getConfig(ApiClients.ANOTHER)
        return RetrofitBuilder.build(
            config,
            moshi = moshi,
            okHttpClient = okHttpClient,
            signatureInterceptor = signatureInterceptor
        )
    }


    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    @Named(ApiClients.CORE_CLIENT)
    fun provideCoreSignatureInterceptor(): Interceptor {
        val config = RetrofitConfigProvider.getConfig(ApiClients.CORE)
        return SignatureInterceptor(config)
    }

    @Provides
    @Singleton
    @Named(ApiClients.MAPS_CLIENT)
    fun provideMapsSignatureInterceptor(): Interceptor {
        val config = RetrofitConfigProvider.getConfig(ApiClients.MAPS)
        return SignatureInterceptor(config)
    }

    @Provides
    @Singleton
    @Named(ApiClients.ANOTHER_CLIENT)
    fun provideAnotherSignatureInterceptor(): Interceptor {
        val config = RetrofitConfigProvider.getConfig(ApiClients.ANOTHER)
        return SignatureInterceptor(config)
    }


}