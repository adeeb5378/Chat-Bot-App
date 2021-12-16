package com.example.chatbot

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Url

interface RetrofitApi {

    @GET
    fun getMessage(@Url url: String): Call<MsgModel>
}