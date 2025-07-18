package com.joselpadi.retrofitapiclientfactory.retrofitbuilder

import com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config.RetrofitConfig
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.security.MessageDigest


class SignatureInterceptor(
    private val config: RetrofitConfig
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val newBuilder = original.newBuilder()

        // adding headers params
        config.headerParams.forEach { (key, value) ->
            newBuilder.addHeader(key, value)
        }

        // Adding body params
        val modifiedBody = if (
            original.method == "POST" &&
            original.body is FormBody &&
            config.bodyParams.isNotEmpty()
        ) {
            val originalBody = original.body as FormBody
            val newBodyBuilder = FormBody.Builder()

            // Copiar body original
            for (i in 0 until originalBody.size) {
                newBodyBuilder.add(originalBody.name(i), originalBody.value(i))
            }

            // Agregar params extra
            config.bodyParams.forEach { (key, value) ->
                newBodyBuilder.add(key, value)
            }
            newBodyBuilder.build()
        } else {
            original.body
        }

        //Sign request
        if (config.signRequest) {
            val canonical = (config.headerParams + config.bodyParams)
                .toSortedMap()
                .entries.joinToString("&") { "${it.key}=${it.value}" }
            val hash = sha256(canonical + "miClavePrivada")
            newBuilder.addHeader("X-Signature", hash)
        }

        return chain.proceed(
            newBuilder
                .method(original.method, modifiedBody)
                .build()
        )
    }

    private fun sha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return digest.joinToString("") { "%02x".format(it) }
    }

}