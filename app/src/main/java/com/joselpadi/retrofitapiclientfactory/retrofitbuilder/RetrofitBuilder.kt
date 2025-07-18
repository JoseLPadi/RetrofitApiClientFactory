package com.joselpadi.retrofitapiclientfactory.retrofitbuilder

import com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config.RetrofitConfig
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitBuilder {
    fun build(
        config: RetrofitConfig,
        moshi: Moshi,
        okHttpClient: OkHttpClient,
        signatureInterceptor: Interceptor

    ): Retrofit {
        val okHttpClientWithInterceptor = okHttpClient.newBuilder()
            .addInterceptor(signatureInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(config.baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClientWithInterceptor)
            .build()
    }

}