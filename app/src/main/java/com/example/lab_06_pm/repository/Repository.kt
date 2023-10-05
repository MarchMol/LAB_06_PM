package com.example.lab_06_pm.repository

import com.example.lab_06_pm.api.RetrofitInstance
import com.example.lab_06_pm.model.Post
import com.example.lab_06_pm.model.post2pck.Post2
import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }

    suspend fun getHref(uaLocator:String): Response<Post2> {
        return RetrofitInstance.api.getHref(uaLocator)
    }
}
