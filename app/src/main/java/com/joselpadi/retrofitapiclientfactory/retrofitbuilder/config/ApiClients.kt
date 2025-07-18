package com.joselpadi.retrofitapiclientfactory.retrofitbuilder.config

/**
 * Api clients
 *
 * @constructor List of different APIs
 */
enum class ApiClients {
    CORE, MAPS, ANOTHER;
    companion object{
        const val CORE_CLIENT = "CORE"
        const val MAPS_CLIENT = "MAPS"
        const val ANOTHER_CLIENT = "ANOTHER"
    }
}

