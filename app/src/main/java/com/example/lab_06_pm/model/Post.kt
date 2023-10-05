package com.example.lab_06_pm.model

import com.google.gson.annotations.SerializedName
data class Post (
    @SerializedName("_links" ) var Links : Links? = Links(),
    @SerializedName("count"  ) var count : Int?   = null
) {
}


