package com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config


/**
 * Retrofit config
 *
 * @property baseUrl
 * @property headerParams
 * @property bodyParams
 * @property timeoutSeconds
 * @constructor Create empty Retrofit config
 */
data class RetrofitConfig(
    val baseUrl: String,
    val headerParams: Map<String, String> = emptyMap(),
    val bodyParams: Map<String, String> = emptyMap(),
    val timeoutSeconds: Long = 30,
    val signRequest: Boolean = false
)