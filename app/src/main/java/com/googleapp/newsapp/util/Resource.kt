package com.googleapp.newsapp.util

sealed class Resource<T>( //look like abstract class but can define which class can be allow to inherit from this class
    val data: T? = null,
    val message : String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String,data: T? = null) : Resource<T>(data,message)
    class Loading<T> : Resource<T>()
}