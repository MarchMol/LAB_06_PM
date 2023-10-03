package com.example.lab_06_pm.api

import com.example.lab_06_pm.model.Post
import retrofit2.http.GET

interface SimpleAPI {
    @GET("/api/urban_areas/")
    suspend fun getPost(): Post

}