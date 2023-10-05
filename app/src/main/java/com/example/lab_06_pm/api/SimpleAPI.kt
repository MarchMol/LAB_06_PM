package com.example.lab_06_pm.api

import com.example.lab_06_pm.model.Post
import com.example.lab_06_pm.model.post2pck.Post2
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SimpleAPI {
    @GET("urban_areas/")
    suspend fun getPost(): Response<Post>

    @GET("urban_areas/{uaLocator}images/")
    suspend fun getHref(
        @Path("uaLocator")locator:String
    ): Response<Post2>
}