package com.cosmicstruck.linkspehere.common.utils

sealed class Resource<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T) : Resource<T>(data = data)
    class Failure<T>(data: T? = null, message: String) : Resource<T>(data = data, message = message)
    class Loading<T>() : Resource<T>(data = null, message = null)
}