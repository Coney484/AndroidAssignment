package com.example.androidassignment.data

import com.example.androidassignment.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(".")
    fun getUsers(@Query("results") results: Int): Call<UserResponse>
}