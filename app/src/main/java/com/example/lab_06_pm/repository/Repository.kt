package com.example.lab_06_pm.repository

import com.example.lab_06_pm.api.RetrofitInstance
import com.example.lab_06_pm.model.Post

class Repository {
    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }
}
