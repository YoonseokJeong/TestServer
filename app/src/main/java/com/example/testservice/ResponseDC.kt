package com.example.testservice

import retrofit2.Call
import retrofit2.http.*

data class ResponseDC(var result:String? = null)
//data class ResponseDTO(): ResponseDC

interface APIInterface {
    @GET("/")
    fun getRequest(@Query("name") name: String): Call<ResponseDC>

    @FormUrlEncoded
    @POST("/")
    fun postRequest(@Field("id")id: String,
                    @Field("password")password: String):Call<ResponseDC>

    @FormUrlEncoded
    @PUT("/{id}")
    fun putRequest(@Path("id")id: String,
                   @Field("content")content: String): Call<ResponseDC>

    @DELETE("/{id}")
    fun deleteRequest(@Path("id")id: String): Call<ResponseDC>
}