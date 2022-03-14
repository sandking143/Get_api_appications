package com.example.getapiappications

import retrofit2.Call
import retrofit2.http.GET


interface JsonPlaceHolderApi {
    @get:GET("posts")
    val posts: Call<List<Post?>?>?
}
