package com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config

class RetrofitConfigProvider {

    companion object {
        /**
         * Get config return a config for a type selected
         *
         * @param type indicate that should return
         * @return a configuration for the client http
         */
        fun getConfig(type: ApiClients): RetrofitConfig = when (type) {
            ApiClients.CORE -> RetrofitConfig(
                baseUrl = "https://users.api.com/",
                headerParams = mapOf("authorization" to "Bearer apikey"),
                bodyParams = mapOf("apikey" to "Bearer ApiKey")
            )
            ApiClients.MAPS -> RetrofitConfig(
                baseUrl = "https://products.api.com/",
                timeoutSeconds = 50
            )
            ApiClients.ANOTHER -> RetrofitConfig(baseUrl = "https://anotherAPI.com/")
        }

    }
}