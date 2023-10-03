package com.example.lab_06_pm.model

import com.google.gson.annotations.SerializedName
data class Post (
    @SerializedName("item")
    val name:String,
    val href:String
)


