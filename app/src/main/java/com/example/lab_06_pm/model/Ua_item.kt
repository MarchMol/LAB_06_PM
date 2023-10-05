package com.example.lab_06_pm.model

import com.google.gson.annotations.SerializedName

data class Ua_item(
    @SerializedName("href" ) var href : String? = null,
    @SerializedName("name" ) var name : String? = null
)
